package com.yairalon.api.application.service;

import com.yairalon.api.application.util.CognitoUtils;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import static com.yairalon.api.application.util.CognitoUtils.calculateSecretHash;

@ApplicationScoped
public class CognitoService {

    private static final Logger LOG = Logger.getLogger(CognitoService.class);

    private CognitoIdentityProviderClient cognitoClient;

    @ConfigProperty(name = "aws.access-key-id")
    String accessKey;

    @ConfigProperty(name = "aws.secret-access-key")
    String secretKey;

    @ConfigProperty(name = "aws.region", defaultValue = "us-east-2")
    String region;

    @ConfigProperty(name = "aws.cognito.user-pool-id")
    String userPoolId;

    @ConfigProperty(name = "aws.cognito.client-id")
    String clientId;

    @ConfigProperty(name = "aws.cognito.client-secret")
    String clientSecret;

    @PostConstruct
    void init() {
        try {
            cognitoClient = CognitoIdentityProviderClient.builder()
                    .region(Region.of(region))
                    .credentialsProvider(
                            StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                    .build();
            LOG.info("✅ Cognito client initialized");
        } catch (Exception e) {
            LOG.error("❌ Failed to initialize Cognito client", e);
        }
    }

    public Uni<String> registerUser(String email, String password, String name) {
        return Uni.createFrom().emitter(emitter -> {
            try {
                LOG.info("➡️ Registering user: " + email);

                AdminCreateUserResponse response = cognitoClient.adminCreateUser(AdminCreateUserRequest.builder()
                        .userPoolId(userPoolId)
                        .username(email)
                        .userAttributes(
                                AttributeType.builder().name("email").value(email).build(),
                                AttributeType.builder().name("name").value(name).build(),
                                AttributeType.builder().name("email_verified").value("true").build()
                        )
                        .messageAction(MessageActionType.SUPPRESS)
                        .build());

                cognitoClient.adminSetUserPassword(AdminSetUserPasswordRequest.builder()
                        .userPoolId(userPoolId)
                        .username(email)
                        .password(password)
                        .permanent(true)
                        .build());

                String sub = response.user().attributes().stream()
                        .filter(attr -> "sub".equals(attr.name()))
                        .map(AttributeType::value)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Missing Cognito 'sub' attribute"));

                LOG.info("✅ User registered with sub: " + sub);
                emitter.complete(sub);
            } catch (Exception e) {
                LOG.error("❌ Error registering user: " + e.getMessage(), e);
                emitter.fail(e);
            }
        });
    }

    public Uni<String> forgotPassword(String email) {
        return Uni.createFrom().emitter(emitter -> {
            try {
                LOG.info("➡️ Initiating forgot password for: " + email);

                String secretHash = calculateSecretHash(clientId, clientSecret, email);

                ForgotPasswordRequest request = ForgotPasswordRequest.builder()
                        .username(email)
                        .clientId(clientId)
                        .secretHash(secretHash)
                        .build();

                cognitoClient.forgotPassword(request);

                LOG.info("✅ Forgot password request sent.");
                emitter.complete("Code sent to email for password reset.");
            } catch (Exception e) {
                LOG.error("❌ Error in forgot password: " + e.getMessage(), e);
                emitter.fail(e);
            }
        });
    }


    public Uni<String> confirmForgotPassword(String email, String newPassword, String confirmationCode) {
        return Uni.createFrom().emitter(emitter -> {
            try {
                LOG.info("➡️ Confirming password reset for: {a}");

                String secretHash = calculateSecretHash(clientId, clientSecret, email);

                ConfirmForgotPasswordRequest request = ConfirmForgotPasswordRequest.builder()
                        .clientId(clientId)
                        .username(email)
                        .confirmationCode(confirmationCode)
                        .password(newPassword)
                        .secretHash(secretHash)
                        .build();

                cognitoClient.confirmForgotPassword(request);

                LOG.info("✅ Password reset confirmed.");
                emitter.complete("Password has been reset successfully.");
            } catch (Exception e) {
                LOG.error("❌ Error confirming password reset: {}", e.getMessage(), e);
                emitter.fail(e);
            }
        });
    }

}
