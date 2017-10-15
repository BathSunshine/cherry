/**
 * 工程名:cherry-http-client
 * 文件名:DealListResult.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午6:32:11
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealListResult {
    private Integer status;

    private String errorInfo;

    /**
     * status.
     *
     * @return the status
     * @since 1.0
     */
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * errorInfo.
     *
     * @return the errorInfo
     * @since 1.0
     */
    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
