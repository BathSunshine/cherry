package com.github.cherry.http.client.common.retry;

import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.comm.ResponseMessage;

/**
 * No retry strategy that does nothing when HTTP request fails.
 */
public class NoRetryStrategy extends RetryStrategy {

    @Override
    public boolean shouldRetry(Exception ex, RequestMessage request, ResponseMessage response, int retries) {
        return false;
    }

}
