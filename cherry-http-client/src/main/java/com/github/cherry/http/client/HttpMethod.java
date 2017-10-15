package com.github.cherry.http.client;

import com.github.cherry.framework.common.constants.LabeledEnum;

/**
 * 表示HTTP的请求方法
 */
public enum HttpMethod implements LabeledEnum<HttpMethod> {
    DELETE("DELETE方法"),

    GET("GET方法"),

    HEAD("HEAD方法"),

    POST("POST方法"),

    PUT("PUT方法"),

    OPTIONS("OPTION方法");

    private String label;

    private HttpMethod(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
