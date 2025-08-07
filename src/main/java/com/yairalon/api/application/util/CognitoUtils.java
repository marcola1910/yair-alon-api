package com.yairalon.api.application.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class CognitoUtils {
    public static String calculateSecretHash(String clientId, String clientSecret, String username) {
        try {
            final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(new SecretKeySpec(clientSecret.getBytes(), HMAC_SHA256_ALGORITHM));
            byte[] rawHmac = mac.doFinal((username + clientId).getBytes());
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating secret hash", e);
        }
    }
}
