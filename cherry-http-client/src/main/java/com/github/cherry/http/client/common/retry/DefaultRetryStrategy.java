package com.github.cherry.http.client.common.retry;

import org.apache.http.HttpStatus;

import com.github.cherry.http.client.ClientErrorCode;
import com.github.cherry.http.client.ClientException;
import com.github.cherry.http.client.ServiceErrorCode;
import com.github.cherry.http.client.ServiceException;
import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.comm.ResponseMessage;

public class DefaultRetryStrategy extends RetryStrategy {
    @Override
    public boolean shouldRetry(Exception ex, RequestMessage request, ResponseMessage response, int retries) {
        if (ex instanceof ClientException) {
            // TODO 幂等考虑
            String errorCode = ((ClientException) ex).getErrorCode();
            if (errorCode.equals(ClientErrorCode.CONNECTION_TIMEOUT)
                    || errorCode.equals(ClientErrorCode.SOCKET_TIMEOUT)
                    || errorCode.equals(ClientErrorCode.CONNECTION_REFUSED)
                    || errorCode.equals(ClientErrorCode.UNKNOWN_HOST)
                    || errorCode.equals(ClientErrorCode.SOCKET_EXCEPTION)) {
                return true;
            }

            // Don't retry when request input stream is non-repeatable
            if (errorCode.equals(ClientErrorCode.NONREPEATABLE_REQUEST)) {
                return false;
            }
        }

        if (ex instanceof ServiceException) {
            String errorCode = ((ServiceException) ex).getErrorCode();
            // No need retry for invalid responses
            if (errorCode.equals(ServiceErrorCode.INVALID_RESPONSE)) {
                return false;
            }
        }

        if (response != null) {
            int statusCode = response.getStatusCode();
            if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR || statusCode == HttpStatus.SC_SERVICE_UNAVAILABLE) {
                return true;
            }
        }

        return false;
    }
}
