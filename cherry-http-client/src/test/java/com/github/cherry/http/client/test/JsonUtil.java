package com.github.cherry.http.client.test;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author Scott
 * @since 1.0
 */
public final class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    private JsonUtil() {

    }

    public static byte[] toStream(Object object) {
        try {
            return mapper.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T parseJson(String jsonString, TypeReference<T> reference) {
        try {
            return mapper.readValue(jsonString, reference);
        } catch (IOException e) {
            return null;
        }

    }

    public static <T> T parseJson(String jsonString, Class<T> clz) {
        try {
            return mapper.readValue(jsonString, clz);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T parseJson(InputStream stream, Class<T> clz) {
        try {
            return mapper.readValue(stream, clz);
        } catch (IOException e) {
            return null;
        }
    }

}
