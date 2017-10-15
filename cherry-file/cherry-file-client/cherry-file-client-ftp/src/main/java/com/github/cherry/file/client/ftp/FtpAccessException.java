package com.github.cherry.file.client.ftp;

import com.github.cherry.file.client.FileAccessException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FtpAccessException extends FileAccessException {

    /**
     * Creates a new instance of FtpAccessException.
     *
     * @param msg
     */

    public FtpAccessException(String msg) {
        super(msg);
    }

    /**
     * Creates a new instance of FtpAccessException.
     *
     * @param msg
     * @param cause
     */

    public FtpAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
