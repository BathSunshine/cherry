/**
 * 工程名:cherry-http-client
 * 文件名:TestFacade.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午7:00:18
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.github.cherry.http.client.ClientConfiguration;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class TestFacade {
    @Test
    public void testDealList() {
        ClientConfiguration config = new ClientConfiguration();
        Map<String, String> defaultHeaders = new HashMap<String, String>();
        defaultHeaders.put("Content-Type", "application/json");
        config.setDefaultHeaders(defaultHeaders);
        CrmFacade crmFacade = new CrmFacadeImpl("", config);
        DealListRequest request = new DealListRequest();
        request.setBeginDate("2017-09-12 00:00:00");
        request.setPageNo(1);
        request.setPageSize(1000);

        DealListResult result = crmFacade.getDealList(request);
        System.out.println(result.getErrorInfo());
        System.out.println(result.getStatus());
    }
}
