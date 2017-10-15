package com.github.cherry.serializer;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class MappingException extends NestedRuntimeException {
    /**
     * Creates a new instance of MappingException.
     *
     * @param msg
     */

    public MappingException(String msg) {
        super(msg);
    }

    public MappingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
