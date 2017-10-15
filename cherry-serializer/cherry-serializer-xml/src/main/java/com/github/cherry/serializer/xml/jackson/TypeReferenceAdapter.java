package com.github.cherry.serializer.xml.jackson;

import java.lang.reflect.Type;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.cherry.serializer.SerializerTypeReference;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @param <T>
 * @since 1.0
 */
public class TypeReferenceAdapter<T> extends TypeReference<T> {
    private SerializerTypeReference<T> serializerTypeReference;

    public TypeReferenceAdapter(SerializerTypeReference<T> serializerTypeReference) {
        this.serializerTypeReference = serializerTypeReference;
    }

    public Type getType() {
        return serializerTypeReference.getType();
    }
}
