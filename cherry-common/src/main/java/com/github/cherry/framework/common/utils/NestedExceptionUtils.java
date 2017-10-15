/**
 * 工程名:cherry-common
 * 文件名:NestedExceptionUtils.java
 * 包名:com.github.cherry.framework.common.utils
 * 创建日期:2017年9月21日下午6:06:49
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.framework.common.utils;

/**
 *〈一句话功能简述〉
 *〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class NestedExceptionUtils {

    /**
     * Build a message for the given base message and root cause.
     * @param message the base message
     * @param cause the root cause
     * @return the full exception message
     */
    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            StringBuilder sb = new StringBuilder();
            if (message != null) {
                sb.append(message).append("; ");
            }
            sb.append("nested exception is ").append(cause);
            return sb.toString();
        }
        else {
            return message;
        }
    }

}
