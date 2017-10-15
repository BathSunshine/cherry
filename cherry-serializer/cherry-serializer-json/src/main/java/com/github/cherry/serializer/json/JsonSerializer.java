package com.github.cherry.serializer.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.SerializerException;
import com.github.cherry.serializer.SerializerStrategy;
import com.github.cherry.serializer.SerializerTypeReference;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class JsonSerializer implements Serializer {

    //Thread Safe
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @see com.github.cherry.serializer.Serializer#strategy()
     */
    @Override
    public SerializerStrategy strategy() {
        return SerializerStrategy.JSON;
    }

    /**
     * @see com.github.cherry.serializer.Serializer#serializeAsString(java.lang.Object)
     */
    @Override
    public String serializeAsString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new SerializerException("can not serializer obj to string", e);
        }
    }

    /**
     * @see com.github.cherry.serializer.Serializer#serializeAsByte(java.lang.Object)
     */
    @Override
    public byte[] serializeAsByte(Object obj) {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new SerializerException("can not serializer obj to byte[]", e);
        }
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(byte[], java.lang.Class)
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try {
            return objectMapper.readValue(bytes, clazz);
        } catch (Exception e) {
            throw new SerializerException("can not deserializer byte[] to obj", e);
        }
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(byte[],
     * com.github.cherry.serializer.SerializerTypeReference)
     */
    @Override
    public <T> T deserialize(byte[] bytes, SerializerTypeReference<T> reference) {
        try {
            return objectMapper.readValue(bytes, new TypeReferenceAdapter<T>(reference));
        } catch (Exception e) {
            throw new SerializerException("can not deserializer string to obj", e);
        }
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(java.lang.String, java.lang.Class)
     */
    @Override
    public <T> T deserialize(String str, Class<T> clazz) {
        try {
            return objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            throw new SerializerException("can not deserializer string to obj", e);
        }
    }

    /**
     * @see com.github.cherry.serializer.Serializer#deserialize(java.lang.String,
     * com.github.cherry.serializer.SerializerTypeReference)
     */
    @Override
    public <T> T deserialize(String str, SerializerTypeReference<T> reference) {
        try {
            return objectMapper.readValue(str, new TypeReferenceAdapter<T>(reference));
        } catch (Exception e) {
            throw new SerializerException("can not deserializer string to obj", e);
        }
    }

}
