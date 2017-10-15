/**
 * 工程名:cherry-serializer-xml
 * 文件名:XmlSerializer.java
 * 包名:com.github.cherry.serializer.xml.jackson
 * 创建日期:2017年9月24日下午11:58:46
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.xml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.cherry.serializer.Serializer;
import com.github.cherry.serializer.SerializerException;
import com.github.cherry.serializer.SerializerStrategy;
import com.github.cherry.serializer.SerializerTypeReference;
import com.github.cherry.serializer.xml.jackson.TypeReferenceAdapter;

/**
 * <p>also support json annotation</p>
 * 
 * @author Scott
 * @since 1.0
 */
public class XmlSerializer implements Serializer {

    private static final ObjectMapper objectMapper = new XmlMapper();

    /**
     * @see com.github.cherry.serializer.Serializer#strategy()
     */
    @Override
    public SerializerStrategy strategy() {
        return SerializerStrategy.XML;
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
