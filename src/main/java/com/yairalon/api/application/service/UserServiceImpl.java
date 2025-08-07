package com.yairalon.api.application.service;

import com.yairalon.api.application.dto.*;
import com.yairalon.api.application.service.factories.EphemeridesFactory;
import com.yairalon.api.model.UserData;
import com.yairalon.api.model.UserMap;
import com.yairalon.api.repository.UserDataRepository;
import com.yairalon.api.repository.UserMapRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    EmailService emailService;
    @Inject
    UserDataRepository userDataRepository;
    @Inject
    UserMapRepository userMapRepository;
    @Inject
    CognitoService cognitoService;

    @Inject
    public UserServiceImpl(UserDataRepository userDataRepository, UserMapRepository userMapRepository, CognitoService cognitoService) {

        this.userDataRepository = userDataRepository;
        this.userMapRepository = userMapRepository;
        this.cognitoService = cognitoService;
    }

    @Override
    public Uni<UserOnboardResponseDTO> userOnboard(UserOnboardRequestDTO req) {

        UserDTO newuser = new UserDTO(
                req.getUUID(),
                req.getCreateat(),
                Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                req.getLastLogin(),
                req.getName(),
                req.getEmail(),
                req.getPhone(),
                req.getCurrentcity(),
                req.getBirthDateTime(),
                req.getCurrentlocationlong(),
                req.getCurrentlocationlat(),
                req.getBirthlocationlong(),
                req.getBirthlocationlat(),
                req.getGender(),
                req.getAddress(),
                req.getCity(),
                req.getState(),
                req.getCountry(),
                req.getZipcode(),
                req.getBirthUTC()
        );

        return userDataRepository.findByEmail(req.getEmail())
                .flatMap(existingUser -> {
                    if (!existingUser.isEmpty()) {
                        return Uni.createFrom().failure(new IllegalStateException("E-mail já cadastrado, por favor faça login."));
                    }

                    return associateWithAuthenticator(new UserOnboardContext(newuser, req))
                            .flatMap(ctx1 ->
                                    addUser(new UserOnboardContext(newuser, req))
                                            .invoke(savedctx -> ctx1.user.setUUID(savedctx.user.getUUID()))
                                            .replaceWith(ctx1)
                            )
                            .flatMap(this::birthChartSaveCalculation)
                            .flatMap(ctx ->
                                    sendWelcomeEmail(ctx)
                                            .onFailure().invoke(ex -> {
                                                System.err.println(" Failed to send welcome email: " + ex.getMessage());
                                                ex.printStackTrace();
                                            })
                                            .onFailure().recoverWithItem(ctx) // <- precisa estar dentro do flatMap
                            )
                            .map(ctx2 -> new UserOnboardResponseDTO(ctx2.user().getUUID(), ctx2.user().getEmail()));
                });

    }

    private Uni<UserOnboardContext> associateWithAuthenticator(UserOnboardContext ctx) {

        UserDTO userDTO = ctx.user();

        return cognitoService.registerUser(userDTO.getEmail(), ctx.request.getPwd(), userDTO.getName())
                .invoke(ctx.user::setAuthId)
                .replaceWith(new UserOnboardContext(ctx.user, ctx.request));

    }

    private Uni<UserOnboardContext> birthChartSaveCalculation(UserOnboardContext ctx) {

        EphemerisService ephsrvc = EphemeridesFactory.createEphemeridesService();

        BirthChartRequestDTO req = new BirthChartRequestDTO();

        req.setBirthday(ctx.user.getBirthDateTime());
        req.setLatPlace(ctx.user.getBirthlocationlat());
        req.setLongPlace(ctx.user.getBirthlocationlong());
        req.setUtc(ctx.user.getUTC());
        req.setCountry(ctx.user.getCountry());
        req.setCity(ctx.user.getCity());
        req.setState(ctx.user.getState());
        req.setName(ctx.user.getName());

        return ephsrvc.getBirthChart(req)
                .flatMap(crr -> birthChartRegister(crr, ctx.user.getUUID()))
                .onItem().transform(t -> new UserOnboardContext(ctx.user, ctx.request));

    }

    private Uni<UserOnboardContext> sendWelcomeEmail(UserOnboardContext ctx) {
        return Uni.createFrom().voidItem()
                .invoke(() -> {
                    try {
                        emailService.sendEmail(
                                ctx.user.getEmail(),
                                "Welcome!",
                                "<!DOCTYPE html>\n" +
                                        "<html lang=\"pt-BR\">\n" +
                                        "<head>\n" +
                                        "  <meta charset=\"UTF-8\">\n" +
                                        "  <title>Bem-vindo ao AstroApp Yair Alon</title>\n" +
                                        "</head>\n" +
                                        "<body style=\"font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;\">\n" +
                                        "  <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">\n" +
                                        "    <tr>\n" +
                                        "      <td style=\"padding: 30px;\">\n" +
                                        "        <h2 style=\"color: #6a1b9a;\">✨ Bem-vindo(a) ao <strong>AstroApp Yair Alon</strong>!</h2>\n" +
                                        "        <p>Olá <strong>" + ctx.user.getName() + "</strong>, tudo bem?</p>\n" +
                                        "        <p>Estamos muito felizes por ter você conosco nessa jornada cósmica. 🌌</p>\n" +
                                        "        <p>Seu mapa astral já está sendo preparado com base nos dados que você nos forneceu. Em breve, você poderá explorar as posições dos planetas, aspectos, casas e muito mais — tudo personalizado especialmente para você!</p>\n" +
                                        "        <h3 style=\"color: #6a1b9a;\">✨ O que você pode fazer no app:</h3>\n" +
                                        "        <ul>\n" +
                                        "          <li>Visualizar seu <strong>mapa astral completo</strong></li>\n" +
                                        "          <li>Receber <strong>interpretações personalizadas</strong> com base nos seus trânsitos</li>\n" +
                                        "          <li>Descobrir tendências para amor, carreira, saúde e espiritualidade</li>\n" +
                                        "          <li>Acompanhar <strong>eventos astrológicos importantes</strong></li>\n" +
                                        "        </ul>\n" +
                                        "        <p><strong>Dica:</strong> mantenha seu perfil atualizado para uma experiência ainda mais precisa e reveladora.</p>\n" +
                                        "        <p>Se tiver dúvidas ou quiser saber mais sobre o universo da astrologia, estamos aqui para ajudar. 🪐</p>\n" +
                                        "        <p style=\"margin-top: 30px;\">Com carinho,<br>\n" +
                                        "        Equipe <strong>AstroApp</strong><br>\n" +
                                        "        <a href=\"https://astroapp.yairalon.com\" style=\"color: #6a1b9a; text-decoration: none;\">Nosso site</a><br>\n" +
                                        "        <a href=\"mailto:suporte@astroapp.yairalon.com\" style=\"color: #6a1b9a;\">suporte@astroapp.yairalon.com</a></p>\n" +
                                        "      </td>\n" +
                                        "    </tr>\n" +
                                        "  </table>\n" +
                                        "</body>\n" +
                                        "</html>\n"
                        );
                    } catch (Exception e) {
                        // This should rarely happen, but we catch to prevent breaking the flow
                        System.err.println("Failed to send welcome email: " + e.getMessage());
                        e.printStackTrace();
                    }
                })
                .onFailure().invoke(ex -> {
                    System.err.println("Error in email sending Uni: " + ex.getMessage());
                    ex.printStackTrace();
                })
                .replaceWith(ctx); // Always return the context regardless of failure
    }

    @Override
    public Uni<UserBirthChartRegistrationResponse> birthChartRegister(
            BirthChartResponseDTO req, String userUUID) {

        UserMap usrMap = new UserMap(userUUID, req.getPlanets(), req.getAspects(), req.getCuspides(), req.getFixedStars(), req.getInterestZone());
        return userMapRepository.persist(usrMap).onItem().transform(map -> new UserBirthChartRegistrationResponse(usrMap.getUseruuid()));
    }

    public Uni<UserOnboardContext> addUser(UserOnboardContext ctx) {

        UserData user = new UserData(
                ctx.user.getName(),
                ctx.user.getEmail(),
                ctx.user.getPhone(),
                ctx.user.getCurrentcity(),
                ctx.user.getBirthDateTime(),
                ctx.user.getCurrentlocationlong(),
                ctx.user.getCurrentlocationlat(),
                ctx.user.getBirthlocationlong(),
                ctx.user.getBirthlocationlat(),
                ctx.user.getGender(),
                ctx.user.getAddress(),
                ctx.user.getCity(),
                ctx.user.getState(),
                ctx.user.getCountry(),
                ctx.user.getZipcode(),
                ctx.user.getUTC(),
                ctx.user.getAuthId()
        );

        return userDataRepository.persist(user).onItem()
                .transform(persistedUser -> {
                    ctx.user.setUUID(persistedUser.getUserUuid() );
                    return new UserOnboardContext(ctx.user, ctx.request);
                });
    }

    @Override
    public Uni<UserDataResponseDTO> getUserData(String id) {
        Uni<UserData> userUni = userDataRepository.find("userUuid", id).firstResult();
        Uni<UserMap> mapUni = userMapRepository.find("useruuid", id).firstResult();

        return Uni.combine().all().unis(userUni, mapUni).asTuple()
                .onItem().transform(tuple -> {
                    UserData user = tuple.getItem1();
                    UserMap userMap = tuple.getItem2();

                    UserDTO userDTO = new UserDTO(
                            user.getUserUuid(),
                            user.getCreateat(),
                            LocalDateTime.ofInstant(user.getUpdateDate(), ZoneId.systemDefault()),
                            user.getLastLogin(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone(),
                            user.getCurrentcity(),
                            user.getBirthDateTime(),
                            user.getCurrentlocationlong(),
                            user.getCurrentlocationlat(),
                            user.getBirthlocationlong(),
                            user.getBirthlocationlat(),
                            user.getGender(),
                            user.getAddress(),
                            user.getCity(),
                            user.getState(),
                            user.getCountry(),
                            user.getZipcode(),
                            user.getUTC()
                    );

                    UserMapDTO userMapDTO = null;
                    if (userMap != null) {
                        userMapDTO = new UserMapDTO(
                                userMap.getUseruuid(),
                                userDTO,
                                userMap.getPlanets(),
                                userMap.getAspects(),
                                userMap.getCuspides(),
                                userMap.getFixedStars(),
                                userMap.getInterestZones()
                        );
                    }

                    return new UserDataResponseDTO(userDTO, userMapDTO);
                })
                .onFailure().invoke(ex -> {
                    System.err.println("❌ Error fetching user data or map for UUID: " + id);
                    ex.printStackTrace(); // Full stack trace in console
                });
    }

    @Override
    public Uni<UserDataResponseDTO> getUserDatabyAuthId(String authId) {
        return userDataRepository.find("authId", authId).firstResult()
                .flatMap(user -> {
                    if (user == null) {
                        return Uni.createFrom().nullItem(); // Or handle "user not found"
                    }

                    return userMapRepository.find("useruuid", user.getUserUuid()).firstResult()
                            .map(userMap -> {
                                UserDTO userDTO = new UserDTO(
                                        user.getUserUuid(),
                                        user.getCreateat(),
                                        LocalDateTime.ofInstant(user.getUpdateDate(), ZoneId.systemDefault()),
                                        user.getLastLogin(),
                                        user.getName(),
                                        user.getEmail(),
                                        user.getPhone(),
                                        user.getCurrentcity(),
                                        user.getBirthDateTime(),
                                        user.getCurrentlocationlong(),
                                        user.getCurrentlocationlat(),
                                        user.getBirthlocationlong(),
                                        user.getBirthlocationlat(),
                                        user.getGender(),
                                        user.getAddress(),
                                        user.getCity(),
                                        user.getState(),
                                        user.getCountry(),
                                        user.getZipcode(),
                                        user.getUTC()
                                );

                                UserMapDTO userMapDTO = null;
                                if (userMap != null) {
                                    userMapDTO = new UserMapDTO(
                                            userMap.getUseruuid(),
                                            userDTO,
                                            userMap.getPlanets(),
                                            userMap.getAspects(),
                                            userMap.getCuspides(),
                                            userMap.getFixedStars(),
                                            userMap.getInterestZones()
                                    );
                                }

                                return new UserDataResponseDTO(userDTO, userMapDTO);
                            });
                })
                .onFailure().invoke(ex -> {
                    System.err.println("❌ Error fetching user data or map for AuthId: " + authId);
                    ex.printStackTrace();
                });
    }


    public record UserOnboardContext(UserDTO user, UserOnboardRequestDTO request) {
    }
}
