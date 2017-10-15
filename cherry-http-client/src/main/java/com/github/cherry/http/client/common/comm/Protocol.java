package com.github.cherry.http.client.common.comm;

import com.github.cherry.framework.common.constants.LabeledEnum;

public enum Protocol implements LabeledEnum<Protocol> {

    HTTP("http"),

    HTTPS("https");

    private final String protocol;

    private Protocol(String protocol) {
        this.protocol = protocol;
    }

    @Override
    public String toString() {
        return protocol;
    }

    @Override
    public String getLabel() {
        return protocol;
    }
}
