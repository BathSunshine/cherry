package com.github.cherry.http.client.common.comm;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;

import com.github.cherry.http.client.common.client.ServiceClient;

public class ResponseMessage extends HttpMesssage {

    private static final int HTTP_SUCCESS_STATUS_CODE = 200;

    private String uri;

    private int statusCode;

    private ServiceClient.Request request;

    private CloseableHttpResponse httpResponse;

    // For convenience of logging invalid response
    private String errorResponseAsString;

    public ResponseMessage(ServiceClient.Request request) {
        this.request = request;
    }

    public String getUri() {
        return uri;
    }

    public void setUrl(String uri) {
        this.uri = uri;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ServiceClient.Request getRequest() {
        return request;
    }

    public String getRequestId() {
        return "";
    }

    public boolean isSuccessful() {
        return statusCode / 100 == HTTP_SUCCESS_STATUS_CODE / 100;
    }

    public String getErrorResponseAsString() {
        return errorResponseAsString;
    }

    public void setErrorResponseAsString(String errorResponseAsString) {
        this.errorResponseAsString = errorResponseAsString;
    }

    public void abort() throws IOException {
        if (httpResponse != null) {
            httpResponse.close();
        }
    }

    public CloseableHttpResponse getHttpResponse() {
        return httpResponse;
    }

    public void setHttpResponse(CloseableHttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }
}
