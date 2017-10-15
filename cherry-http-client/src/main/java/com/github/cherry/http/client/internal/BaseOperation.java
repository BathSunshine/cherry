package com.github.cherry.http.client.internal;

import java.net.URI;
import java.util.List;

import com.github.cherry.http.client.ClientUtil;
import com.github.cherry.http.client.HttpMethod;
import com.github.cherry.http.client.common.client.ServiceClient;
import com.github.cherry.http.client.common.comm.ExecutionContext;
import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.handler.RequestChecksumHanlder;
import com.github.cherry.http.client.common.handler.RequestHandler;
import com.github.cherry.http.client.common.handler.RequestProgressHanlder;
import com.github.cherry.http.client.common.handler.ResponseChecksumHandler;
import com.github.cherry.http.client.common.handler.ResponseHandler;
import com.github.cherry.http.client.common.handler.ResponseProgressHandler;
import com.github.cherry.http.client.common.parser.EmptyResponseParser;
import com.github.cherry.http.client.common.parser.ResponseParseException;
import com.github.cherry.http.client.common.parser.ResponseParser;
import com.github.cherry.http.client.common.retry.NoRetryStrategy;
import com.github.cherry.http.client.common.retry.RetryStrategy;
import com.github.cherry.http.client.common.utils.ExceptionFactory;
import com.github.cherry.http.client.common.utils.LogUtils;
import com.github.cherry.http.client.model.WebServiceRequest;

/**
 * Abstract base class that provides some common functionalities operations
 * 
 */
public abstract class BaseOperation {
    protected volatile URI endpoint;

    protected String resourcePath;

    protected ServiceClient client;

    protected static ErrorResponseHandler errorResponseHandler = new ErrorResponseHandler();

    protected static EmptyResponseParser emptyResponseParser = new EmptyResponseParser();

    protected static RetryStrategy noRetryStrategy = new NoRetryStrategy();

    protected BaseOperation(ServiceClient client) {
        this.client = client;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public URI getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = URI.create(endpoint.toString());
    }

    protected ServiceClient getInnerClient() {
        return this.client;
    }

    protected ResponseMessage send(RequestMessage request, ExecutionContext context) {
        return send(request, context, false);
    }

    protected ResponseMessage send(RequestMessage request, ExecutionContext context, boolean keepResponseOpen)

    {
        ResponseMessage response = null;
        try {
            response = client.sendRequest(request, context);
            return response;
        }

        finally {
            if (response != null && !keepResponseOpen) {
                ClientUtil.safeCloseResponse(response);
            }
        }
    }

    protected <T> T doOperation(RequestMessage request, ResponseParser<T> parser) {
        return doOperation(request, parser, false);
    }

    protected <T> T doOperation(RequestMessage request, ResponseParser<T> parser, boolean keepResponseOpen) {
        return doOperation(request, parser, keepResponseOpen, null, null);
    }

    protected <T> T doOperation(RequestMessage request, ResponseParser<T> parser, boolean keepResponseOpen,
            List<RequestHandler> requestHandlers, List<ResponseHandler> reponseHandlers) {

        final WebServiceRequest originalRequest = request.getOriginalRequest();
        request.getHeaders().putAll(client.getClientConfiguration().getDefaultHeaders());
        request.getHeaders().putAll(originalRequest.getHeaders());
        request.getParameters().putAll(originalRequest.getParameters());

        ExecutionContext context = createDefaultContext(request.getMethod());
        context.addRequestHandler(new RequestProgressHanlder());
        if (requestHandlers != null) {
            for (RequestHandler handler : requestHandlers) {
                context.addRequestHandler(handler);
            }
        }

        if (client.getClientConfiguration().isCrcCheckEnabled()) {
            context.addRequestHandler(new RequestChecksumHanlder());
        }

        context.addResponseHandler(new ResponseProgressHandler(originalRequest));
        if (reponseHandlers != null) {
            for (ResponseHandler handler : reponseHandlers) {
                context.addResponseHandler(handler);
            }
        }

        if (client.getClientConfiguration().isCrcCheckEnabled()) {
            context.addResponseHandler(new ResponseChecksumHandler());
        }

        ResponseMessage response = send(request, context, keepResponseOpen);

        try {
            return parser.parse(response);
        } catch (ResponseParseException rpe) {
            LogUtils.logException("Unable to parse response error: ", rpe);
            throw ExceptionFactory.createInvalidResponseException(response.getRequestId(), rpe.getMessage(), rpe);
        }
    }

    protected ExecutionContext createDefaultContext(HttpMethod method) {
        ExecutionContext context = new ExecutionContext();
        context.setCharset(ClientConstants.DEFAULT_CHARSET_NAME);
        context.addResponseHandler(errorResponseHandler);
        context.setRetryStrategy(getRetryStrategy(method));

        return context;
    }

    protected RetryStrategy getRetryStrategy(HttpMethod method) {
        if (method == HttpMethod.POST) {
            return noRetryStrategy;
        }
        return null;
    }

}
