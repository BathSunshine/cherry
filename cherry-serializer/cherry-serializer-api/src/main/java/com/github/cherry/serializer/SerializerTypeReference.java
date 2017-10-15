package com.github.cherry.serializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class SerializerTypeReference<T> {
    protected final Type type;

    protected SerializerTypeReference() {
        Type superClass = getClass().getGenericSuperclass();
        if (superClass instanceof Class<?>) { // sanity check, should never happen
            throw new IllegalArgumentException(
                    "Internal error: TypeReference constructed without actual type information");
        }

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
