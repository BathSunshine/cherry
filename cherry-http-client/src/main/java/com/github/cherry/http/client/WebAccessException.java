/**
 * 工程名:cherry-http-client
 * 文件名:WebAccessException.java
 * 包名:com.github.cherry.http.client
 * 创建日期:2017年9月22日上午8:48:55
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client;

import com.github.cherry.framework.common.exception.NestedRuntimeException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public abstract class WebAccessException extends NestedRuntimeException {

    /**
     * Constructor for WebAccessException.
     * 
     * @param msg the detail message
     */
    public WebAccessException(String msg) {
        super(msg);
    }

    /**
     * Constructor for WebAccessException.
     * 
     * @param msg the detail message
     * @param cause the root cause (usually from using a underlying
     * web access API)
     */
    public WebAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
