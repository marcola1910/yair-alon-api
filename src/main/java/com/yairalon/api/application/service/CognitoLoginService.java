package com.yairalon.api.application.service;

import com.yairalon.api.application.util.CognitoUtils;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CognitoLoginService {

    private CognitoIdentityProviderClient cognitoClient;

    @Inject
    @ConfigProperty(name = "aws.cognito.client-id")
    String clientId;

    @Inject
    @ConfigProperty(name = "aws.region")
    String region;

    @Inject
    @ConfigProperty(name = "aws.access-key-id")
    String accessKey;

    @Inject
    @ConfigProperty(name = "aws.secret-access-key")
    String secretKey;

    @ConfigProperty(name = "aws.cognito.client-secret")
    String clientsec;

    @PostConstruct
    void init() {
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(accessKey, secretKey)))
                .build();
    }

    public String login(String email, String password) {
        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", password);
        authParams.put("SECRET_HASH", CognitoUtils.calculateSecretHash(clientId, clientsec, email));

        InitiateAuthRequest authRequest = InitiateAuthRequest.builder()
                .authFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .clientId(clientId)
                .authParameters(authParams)
                .build();

        try {
            InitiateAuthResponse response = cognitoClient.initiateAuth(authRequest);

            if (response.authenticationResult() != null) {
                return response.authenticationResult().idToken();
            } else if (response.challengeName() != null) {
                throw new RuntimeException("Login requires challenge: " + response.challengeNameAsString());
            } else {
                throw new RuntimeException("Login failed for unknown reason.");
            }

        } catch (NotAuthorizedException e) {
            throw new RuntimeException("Incorrect username or password.");
        } catch (UserNotFoundException e) {
            throw new RuntimeException("User not found.");
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage(), e);
        }
    }
}
