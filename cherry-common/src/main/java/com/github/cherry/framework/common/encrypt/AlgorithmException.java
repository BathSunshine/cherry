package com.github.cherry.framework.common.encrypt;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class AlgorithmException extends NestedRuntimeException {

    /**
     * serialVersionUID:(用一句话描述这个变量表示什么).
     */
    private static final long serialVersionUID = -5377565343649497042L;

    /**
     * Creates a new instance of AlgorithmException.
     *
     * @param msg
     * @param cause
     */

    public AlgorithmException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
