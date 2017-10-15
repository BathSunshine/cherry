package com.github.cherry.file.client;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FileAccessException extends NestedRuntimeException {

    /**
     * Creates a new instance of FileAccessException.
     *
     */

    public FileAccessException(String msg) {
        super(msg);
    }

    /**
     * Creates a new instance of FileAccessException.
     *
     * @param msg
     * @param cause
     */

    public FileAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
