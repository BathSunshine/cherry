/**
 * 工程名:cherry-http-client
 * 文件名:CrmFacadeImpl.java
 * 包名:com.github.cherry.http.client.test
 * 创建日期:2017年9月12日下午6:44:52
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.test;

import java.net.URI;
import java.net.URISyntaxException;

import com.github.cherry.http.client.ClientConfiguration;
import com.github.cherry.http.client.common.client.DefaultServiceClient;
import com.github.cherry.http.client.common.client.ServiceClient;
import com.github.cherry.http.client.common.client.TimeoutServiceClient;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class CrmFacadeImpl implements CrmFacade {
    private ServiceClient serviceClient;

    private URI endpoint;

    private DealListOperation dealListOperation;

    public CrmFacadeImpl(String endpoint, ClientConfiguration config) {
        config = config == null ? new ClientConfiguration() : config;
        if (config.isRequestTimeoutEnabled()) {
            this.serviceClient = new TimeoutServiceClient(config);
        } else {
            this.serviceClient = new DefaultServiceClient(config);
        }
        initOperations();
        setEndpoint(endpoint);
    }

    /**
     * 〈一句话功能简述〉 〈功能详细描述〉
     * 
     * @param endPoint
     */
    private void setEndpoint(String endpoint) {
        URI uri = toURI(endpoint);
        this.endpoint = uri;
        dealListOperation.setEndpoint(this.endpoint);

    }

    /**
     * 〈一句话功能简述〉 〈功能详细描述〉
     * 
     * @param endpoint2
     * @return
     */
    private URI toURI(String endpoint) {
        try {
            return new URI(endpoint);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 〈一句话功能简述〉 〈功能详细描述〉
     */
    private void initOperations() {
        this.dealListOperation = new DealListOperation(serviceClient);
    }

    /**
     * @see com.github.cherry.http.client.test.CrmFacade#getDealList(com.github.cherry.http.client.test.DealListRequest)
     */
    @Override
    public DealListResult getDealList(DealListRequest request) {
        return this.dealListOperation.getDealList(request);
    }

    
  
}
