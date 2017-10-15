package com.github.cherry.http.client.common.comm;

import java.util.LinkedList;
import java.util.List;

import com.github.cherry.http.client.common.handler.RequestHandler;
import com.github.cherry.http.client.common.handler.ResponseHandler;
import com.github.cherry.http.client.common.retry.RetryStrategy;
import com.github.cherry.http.client.internal.ClientConstants;

/**
 * HTTP request context.
 */
public class ExecutionContext {

    /* The request handlers that handle request content in as a pipeline. */
    private List<RequestHandler> requestHandlers = new LinkedList<RequestHandler>();

    /* The response handlers that handle response message in as a pipeline. */
    private List<ResponseHandler> responseHandlers = new LinkedList<ResponseHandler>();

    private String charset = ClientConstants.DEFAULT_CHARSET_NAME;

    /* Retry strategy when HTTP request fails. */
    private RetryStrategy retryStrategy;

    public RetryStrategy getRetryStrategy() {
        return retryStrategy;
    }

    public void setRetryStrategy(RetryStrategy retryStrategy) {
        this.retryStrategy = retryStrategy;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String defaultEncoding) {
        this.charset = defaultEncoding;
    }

    public List<ResponseHandler> getResponseHandlers() {
        return responseHandlers;
    }

    public void addResponseHandler(ResponseHandler handler) {
        responseHandlers.add(handler);
    }

    public void insertResponseHandler(int position, ResponseHandler handler) {
        responseHandlers.add(position, handler);
    }

    public void removeResponseHandler(ResponseHandler handler) {
        responseHandlers.remove(handler);
    }

    public List<RequestHandler> getResquestHandlers() {
        return requestHandlers;
    }

    public void addRequestHandler(RequestHandler handler) {
        requestHandlers.add(handler);
    }

    public void insertRequestHandler(int position, RequestHandler handler) {
        requestHandlers.add(position, handler);
    }

    public void removeRequestHandler(RequestHandler handler) {
        requestHandlers.remove(handler);
    }

}
