package com.yairalon.api.application.service;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.*;

@ApplicationScoped
public class EmailService {

    private static final Logger LOG = Logger.getLogger(EmailService.class);

    private SesClient sesClient;

    @ConfigProperty(name = "aws.access-key-id")
    String accessKey;

    @ConfigProperty(name = "aws.secret-access-key")
    String secretKey;

    @ConfigProperty(name = "aws.region")
    String region;

    @ConfigProperty(name = "aws.ses.from-email")
    String fromEmail;

    @PostConstruct
    void init() {
        try {
            sesClient = SesClient.builder()
                    .region(Region.of(region))
                    .credentialsProvider(StaticCredentialsProvider.create(
                            AwsBasicCredentials.create(accessKey, secretKey)
                    ))
                    .build();
            LOG.info("SES client initialized successfully.");
        } catch (Exception e) {
            LOG.error("Failed to initialize SES client", e);
        }
    }

    public void sendEmail(String toAddress, String subject, String bodyText) {
        try {
            SendEmailRequest emailRequest = SendEmailRequest.builder()
                    .destination(Destination.builder()
                            .toAddresses(toAddress)
                            .build())
                    .message(Message.builder()
                            .subject(Content.builder()
                                    .data(subject)
                                    .charset("UTF-8")
                                    .build())
                            .body(Body.builder()
                                    .html(Content.builder()  // <-- use HTML content here
                                            .data(bodyText)      // your HTML string
                                            .charset("UTF-8")
                                            .build())
                                    .build())
                            .build())
                    .source(fromEmail) // must be verified
                    .build();

            SendEmailResponse response = sesClient.sendEmail(emailRequest);
            LOG.infof("Email sent to %s. MessageId: %s", toAddress, response.messageId());

        } catch (SesException e) {
            LOG.errorf("SES exception while sending email to %s: %s", toAddress, e.awsErrorDetails().errorMessage());
        } catch (Exception e) {
            LOG.errorf("Unexpected error while sending email to %s", toAddress, e);
        }
    }
}
