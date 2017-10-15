package com.github.cherry.http.client.common.utils;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.NonRepeatableRequestException;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

import com.github.cherry.http.client.ClientErrorCode;
import com.github.cherry.http.client.ClientException;
import com.github.cherry.http.client.ServiceErrorCode;
import com.github.cherry.http.client.ServiceException;
import com.github.cherry.http.client.internal.CommonResourceHolder;

/**
 * A simple factory used for creating instances of <code>ClientException</code>
 * 
 */
public class ExceptionFactory {

    public static ClientException createNetworkException(IOException ex) {
        String requestId = "Unknown";
        String errorCode = ClientErrorCode.UNKNOWN;

        if (ex instanceof SocketTimeoutException) {
            errorCode = ClientErrorCode.SOCKET_TIMEOUT;
        } else if (ex instanceof SocketException) {
            errorCode = ClientErrorCode.SOCKET_EXCEPTION;
        } else if (ex instanceof ConnectTimeoutException) {
            errorCode = ClientErrorCode.CONNECTION_TIMEOUT;
        } else if (ex instanceof UnknownHostException) {
            errorCode = ClientErrorCode.UNKNOWN_HOST;
        } else if (ex instanceof HttpHostConnectException) {
            errorCode = ClientErrorCode.CONNECTION_REFUSED;
        } else if (ex instanceof NoHttpResponseException) {
            errorCode = ClientErrorCode.CONNECTION_TIMEOUT;
        } else if (ex instanceof ClientProtocolException) {
            Throwable cause = ex.getCause();
            if (cause instanceof NonRepeatableRequestException) {
                errorCode = ClientErrorCode.NONREPEATABLE_REQUEST;
                return new ClientException(cause.getMessage(), errorCode, requestId, cause);
            }
        }
        return new ClientException(ex.getMessage(), errorCode, requestId, ex);
    }

    public static ServiceException createInvalidResponseException(String requestId, Throwable cause) {
        return createInvalidResponseException(requestId,
                CommonResourceHolder.getInstance().getFormattedString("FailedToParseResponse", cause.getMessage()));
    }

    public static ServiceException createInvalidResponseException(String requestId, String rawResponseError,
            Throwable cause) {
        return createInvalidResponseException(requestId,
                CommonResourceHolder.getInstance().getFormattedString("FailedToParseResponse", cause.getMessage()),
                rawResponseError);
    }

    public static ServiceException createInvalidResponseException(String requestId, String message) {
        return createServiceException(requestId, ServiceErrorCode.INVALID_RESPONSE, message);
    }

    public static ServiceException createInvalidResponseException(String requestId, String message,
            String rawResponseError) {
        return createServiceException(requestId, ServiceErrorCode.INVALID_RESPONSE, message, rawResponseError);
    }

    public static ServiceException createServiceException(String requestId, String errorCode, String message) {
        return new ServiceException(message, errorCode, requestId, null, null, null, null, null, null);
    }

    public static ServiceException createServiceException(String requestId, String errorCode, String message,
            String rawResponseError) {
        return new ServiceException(message, errorCode, requestId, null, rawResponseError, null, null, null, null);
    }

    public static ServiceException createUnknownServiceException(String requestId, int statusCode) {
        String message = "No body in response, http status code " + Integer.toString(statusCode);
        return new ServiceException(message, ClientErrorCode.UNKNOWN, requestId, null, null, null, null, null, null);
    }
}
