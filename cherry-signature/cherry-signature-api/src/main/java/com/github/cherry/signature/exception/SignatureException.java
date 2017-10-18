package com.github.cherry.signature.exception;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

@SuppressWarnings("serial")
public class SignatureException extends NestedRuntimeException {

    public SignatureException(String msg) {
        super(msg);
    }

    public SignatureException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
