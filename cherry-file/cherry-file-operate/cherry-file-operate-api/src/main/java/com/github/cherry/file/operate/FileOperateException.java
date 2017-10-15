package com.github.cherry.file.operate;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FileOperateException extends NestedRuntimeException {

    /**
     * Creates a new instance of FileOperateException.
     *
     */
    public FileOperateException(String msg) {
        super(msg);
    }

    /**
     * Creates a new instance of FileOperateException.
     *
     * @param msg
     * @param cause
     */

    public FileOperateException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
