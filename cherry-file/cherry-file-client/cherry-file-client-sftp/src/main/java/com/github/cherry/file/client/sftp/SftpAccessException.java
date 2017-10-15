package com.github.cherry.file.client.sftp;

import com.github.cherry.file.client.FileAccessException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class SftpAccessException extends FileAccessException {
    /**
     * Creates a new instance of SftpException.
     *
     * @param msg
     */

    public SftpAccessException(String msg) {
        super(msg);
    }

    /**
     * Creates a new instance of SftpException.
     *
     * @param msg
     * @param cause
     */

    public SftpAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
