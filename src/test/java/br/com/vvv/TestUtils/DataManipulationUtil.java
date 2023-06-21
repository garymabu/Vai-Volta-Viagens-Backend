package br.com.vvv.TestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
public class DataManipulationUtil {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
