package com.github.cherry.serializer;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public interface Serializer {
    public SerializerStrategy strategy();

    public String serializeAsString(Object obj);

    public byte[] serializeAsByte(Object obj);

    public <T> T deserialize(byte[] bytes, Class<T> clazz);

    public <T> T deserialize(byte[] bytes, SerializerTypeReference<T> reference);

    public <T> T deserialize(String str, Class<T> clazz);

    public <T> T deserialize(String str, SerializerTypeReference<T> reference);

}
