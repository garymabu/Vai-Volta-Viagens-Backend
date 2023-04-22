package br.com.vvv.Helpers;

import java.util.UUID;

public class DataHelpers {
    
    public static UUID generatedUuid() {
        UUID uuid = UUID.randomUUID();
        return uuid;
      }
}