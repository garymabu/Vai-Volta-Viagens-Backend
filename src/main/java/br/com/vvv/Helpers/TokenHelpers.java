package br.com.vvv.Helpers;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class TokenHelpers {

    public static Key parseStringToKey(String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(secretBytes, "HmacSHA256");
    }
    
}