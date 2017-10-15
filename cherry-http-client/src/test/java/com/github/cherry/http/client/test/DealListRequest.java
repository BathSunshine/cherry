/**
 * 工程名:cherry-http-client
 * 文件名:DealListRequest.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午6:29:27
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.cherry.http.client.model.WebServiceRequest;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class DealListRequest extends WebServiceRequest {
    @JsonProperty(value = "begin_date")
    private String beginDate;

    @JsonProperty(value = "page_no")
    private int pageNo;

    @JsonProperty(value = "page_size")
    private int pageSize;

    @JsonProperty(value = "auth_key")
    private String authKey = "c630cd8d0a8a9e9249a19abf3ea74437";

    /**
     * beginDate.
     *
     * @return the beginDate
     * @since 1.0
     */
    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * pageNo.
     *
     * @return the pageNo
     * @since 1.0
     */
    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    /**
     * pageSize.
     *
     * @return the pageSize
     * @since 1.0
     */
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * authKey.
     *
     * @return the authKey
     * @since 1.0
     */
    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

}
