/**
 * 工程名:cherry-http-client
 * 文件名:DealListOperation.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午6:20:30
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import java.io.ByteArrayInputStream;

import com.github.cherry.http.client.HttpMethod;
import com.github.cherry.http.client.common.client.ServiceClient;
import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.internal.BaseOperation;
import com.github.cherry.http.client.internal.RequestMessageBuilder;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class DealListOperation extends BaseOperation {

    /**
     * Creates a new instance of DealListOperation.
     *
     * @param client
     */

    public DealListOperation(ServiceClient client) {
        super(client);
    }

    public DealListResult getDealList(DealListRequest request) {
        byte[] requestByte = JsonUtil.toStream(request);
        RequestMessage message = new RequestMessageBuilder().setEndpoint(getEndpoint())
                .setResourcePath("/api/jyls/getDealRecordList.do").setOriginalRequest(request)
                .setMethod(HttpMethod.POST).setInputSize(requestByte.length)
                .setInputStream(new ByteArrayInputStream(requestByte)).build();
        return doOperation(message, new GenericResponseParser<DealListResult>() {
        }, true, null, null);
    }
}
