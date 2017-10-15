/**
 * 工程名:cherry-serializer-json
 * 文件名:TypeReferenceAdapter.java
 * 包名:com.github.cherry.serializer.json
 * 创建日期:2017年9月22日下午1:45:35
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer.json;

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
