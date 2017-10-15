package com.github.cherry.http.client.common.parser;

/**
 * The exception from parsing service response.
 */
public class ResponseParseException extends Exception {
    private static final long serialVersionUID = -6660159156997037589L;

    public ResponseParseException() {
        super();
    }

    public ResponseParseException(String message) {
        super(message);
    }

    public ResponseParseException(Throwable cause) {
        super(cause);
    }

    public ResponseParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
