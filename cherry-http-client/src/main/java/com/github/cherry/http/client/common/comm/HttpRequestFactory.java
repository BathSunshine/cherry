package com.github.cherry.http.client.common.comm;

import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

import com.github.cherry.http.client.ClientException;
import com.github.cherry.http.client.HttpHeaders;
import com.github.cherry.http.client.HttpMethod;
import com.github.cherry.http.client.common.client.ServiceClient;
import com.github.cherry.http.client.common.io.ChunkedInputStreamEntity;

public class HttpRequestFactory {

    public HttpRequestBase createHttpRequest(ServiceClient.Request request, ExecutionContext context) {

        String uri = request.getUri();

        HttpRequestBase httpRequest;
        HttpMethod method = request.getMethod();
        if (method == HttpMethod.POST) {
            HttpPost postMethod = new HttpPost(uri);

            if (request.getContent() != null) {
                postMethod.setEntity(new RepeatableInputStreamEntity(request));
            }

            httpRequest = postMethod;
        } else if (method == HttpMethod.PUT) {
            HttpPut putMethod = new HttpPut(uri);

            if (request.getContent() != null) {
                if (request.isUseChunkEncoding()) {
                    putMethod.setEntity(buildChunkedInputStreamEntity(request));
                } else {
                    putMethod.setEntity(new RepeatableInputStreamEntity(request));
                }
            }
            httpRequest = putMethod;
        } else if (method == HttpMethod.GET) {
            httpRequest = new HttpGet(uri);
        } else if (method == HttpMethod.DELETE) {
            httpRequest = new HttpDelete(uri);
        } else if (method == HttpMethod.HEAD) {
            httpRequest = new HttpHead(uri);
        } else if (method == HttpMethod.OPTIONS) {
            httpRequest = new HttpOptions(uri);
        } else {
            throw new ClientException("Unknown HTTP method name: " + method.toString());
        }

        configureRequestHeaders(request, context, httpRequest);

        return httpRequest;
    }

    private HttpEntity buildChunkedInputStreamEntity(ServiceClient.Request request) {
        return new ChunkedInputStreamEntity(request);
    }

    private void configureRequestHeaders(ServiceClient.Request request, ExecutionContext context,
            HttpRequestBase httpRequest) {

        for (Entry<String, String> entry : request.getHeaders().entrySet()) {
            if (entry.getKey().equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)
                    || entry.getKey().equalsIgnoreCase(HttpHeaders.HOST)) {
                continue;
            }
            httpRequest.addHeader(entry.getKey(), entry.getValue());
        }
    }
}
