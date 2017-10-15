/**
 * 工程名:cherry-serializer-api
 * 文件名:DeserializerException.java
 * 包名:com.github.cherry.serializer
 * 创建日期:2017年9月22日上午10:14:35
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.serializer;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@SuppressWarnings("serial")
public class DeserializerException extends MappingException {

    /**
     * Creates a new instance of DeserializerException.
     *
     * @param msg
     */

    public DeserializerException(String msg) {
        super(msg);
    }

    public DeserializerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
