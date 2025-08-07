package com.yairalon.api.interfaces.rest;

import com.yairalon.api.application.dto.ConfirmForgotPasswordDTO;
import com.yairalon.api.application.dto.ForgotPasswordRequest;
import com.yairalon.api.application.service.CognitoLoginService;
import com.yairalon.api.application.service.CognitoService;
import io.smallrye.jwt.auth.principal.JWTParser;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PermitAll
@ApplicationScoped
public class AuthResource {

    @Inject
    CognitoLoginService loginService;

    @Inject
    CognitoService  cognitoService;

    @Inject
    JWTParser jwtParser;


    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class LoginResponse {
        public String token;

        public LoginResponse(String token) {
            this.token = token;
        }
    }

    @POST
    @Path("/login")
    public Response login(LoginRequest req) {
        try {
            String token = loginService.login(req.email, req.password);
            return Response.ok(new LoginResponse(token)).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("/forgot-password")
    public Uni<Response> forgotPassword(ForgotPasswordRequest request) {
        return cognitoService.forgotPassword(request.getEmail())
                .onItem().transform(msg -> Response.ok().entity("{\"message\": \"" + msg + "\"}").build())
                .onFailure().recoverWithItem(e -> {
                    e.printStackTrace();
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("{\"error\": \"" + e.getMessage() + "\"}")
                            .build();
                });
    }

    @POST
    @Path("/confirm-forgot-password")
    public Uni<Response> confirmForgotPassword(ConfirmForgotPasswordDTO request) {
        return cognitoService.confirmForgotPassword(request.email, request.newPassword, request.confirmationCode)
                .onItem().transform(msg -> Response.ok().entity("{\"message\": \"" + msg + "\"}").build())
                .onFailure().recoverWithItem(e -> {
                    e.printStackTrace();
                    return Response.status(Response.Status.BAD_REQUEST)
                            .entity("{\"error\": \"" + e.getMessage() + "\"}")
                            .build();
                });
    }

}
