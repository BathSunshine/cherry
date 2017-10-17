package com.github.cherry.signature.exception;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

public class SignatureException extends NestedRuntimeException {
    private static final long serialVersionUID = -1562927635550374987L;

    public SignatureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
