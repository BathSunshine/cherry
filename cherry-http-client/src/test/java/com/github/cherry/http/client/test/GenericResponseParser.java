/**
 * 工程名:cherry-http-client
 * 文件名:GenericResponseParser.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月13日上午10:41:09
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.parser.ResponseParseException;
import com.github.cherry.http.client.common.parser.ResponseParser;

public abstract class GenericResponseParser<T> implements ResponseParser<T> {
    private Class<T> entityClass;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public GenericResponseParser() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];
    }

    /**
     * @see com.github.cherry.http.client.common.parser.ResponseParser#parse(com.github.cherry.http.client.common.comm.ResponseMessage)
     */
    @Override
    public T parse(ResponseMessage response) throws ResponseParseException {
        return JsonUtil.parseJson(response.getContent(), entityClass);
    }

}
