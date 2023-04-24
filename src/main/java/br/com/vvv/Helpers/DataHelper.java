package br.com.vvv.Helpers;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.UUID;

public class DataHelper {
    public static UUID generatedUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid;
      }
    public static Key parseStringToKey(String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, "HmacSHA256");
    }
}