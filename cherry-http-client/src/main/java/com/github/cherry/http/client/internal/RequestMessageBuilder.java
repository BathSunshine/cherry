/**
 * 工程名:cherry-http-client
 * 文件名:RequestMessageBuilder.java
 * 包名:com.github.cherry.http.client.internal
 * 创建日期:2017年9月12日下午5:06:44
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.internal;

import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.cherry.http.client.HttpMethod;
import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.io.FixedLengthInputStream;
import com.github.cherry.http.client.common.utils.CodingUtils;
import com.github.cherry.http.client.model.WebServiceRequest;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class RequestMessageBuilder {
    private URI endpoint;

    private String resourcePath;

    private HttpMethod method = HttpMethod.GET;

    private Map<String, String> headers = new HashMap<String, String>();

    private Map<String, String> parameters = new LinkedHashMap<String, String>();

    private InputStream inputStream;

    private long inputSize = 0;

    // private ServiceClient innerClient;

    private boolean useChunkEncoding = false;

    private WebServiceRequest originalRequest;

    public RequestMessageBuilder() {
    }

    // public RequestMessageBuilder(ServiceClient innerClient)
    // {
    // //this.innerClient = innerClient;
    // }

    public URI getEndpoint() {
        return endpoint;
    }

    public RequestMessageBuilder setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
        return this;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public RequestMessageBuilder setMethod(HttpMethod method) {
        this.method = method;
        return this;
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public RequestMessageBuilder setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestMessageBuilder addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public RequestMessageBuilder setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }

    public RequestMessageBuilder addParameter(String key, String value) {
        parameters.put(key, value);
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public RequestMessageBuilder setInputStream(InputStream instream) {
        this.inputStream = instream;
        return this;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public RequestMessageBuilder setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
        return this;
    }

    public RequestMessageBuilder setInputStreamWithLength(FixedLengthInputStream instream) {
        CodingUtils.assertParameterInRange(inputSize, -1, ClientConstants.DEFAULT_FILE_SIZE_LIMIT);
        this.inputStream = instream;
        this.inputSize = instream.getLength();
        return this;
    }

    public long getInputSize() {
        return inputSize;
    }

    public RequestMessageBuilder setInputSize(long inputSize) {
        CodingUtils.assertParameterInRange(inputSize, -1, ClientConstants.DEFAULT_FILE_SIZE_LIMIT);
        this.inputSize = inputSize;
        return this;
    }

    public boolean isUseChunkEncoding() {
        return useChunkEncoding;
    }

    public RequestMessageBuilder setUseChunkEncoding(boolean useChunkEncoding) {
        this.useChunkEncoding = useChunkEncoding;
        return this;
    }

    public RequestMessageBuilder setOriginalRequest(WebServiceRequest originalRequest) {
        this.originalRequest = originalRequest;
        return this;
    }

    public RequestMessage build() {
        Map<String, String> sentHeaders = new HashMap<String, String>(this.headers);

        Map<String, String> sentParameters = new LinkedHashMap<String, String>(this.parameters);

        RequestMessage request = new RequestMessage(this.originalRequest);

        request.setEndpoint(this.endpoint);
        request.setResourcePath(this.resourcePath);
        request.setHeaders(sentHeaders);
        request.setParameters(sentParameters);
        request.setMethod(this.method);
        request.setContent(this.inputStream);
        request.setContentLength(this.inputSize);
        request.setUseChunkEncoding(this.inputSize == -1 ? true : this.useChunkEncoding);

        return request;
    }
}
