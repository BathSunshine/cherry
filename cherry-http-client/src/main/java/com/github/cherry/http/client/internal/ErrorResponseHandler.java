/**
 * 工程名:cherry-http-client
 * 文件名:ErrorResponseHandler.java
 * 包名:com.github.cherry.http.client.internal
 * 创建日期:2017年9月12日下午3:35:40
 * Copyright (c) 2017 BBJR Information Technology Corporation, All rights reserved.
 * The Software is owned by BBJR and is protected by copyright laws and other national laws.
 * You agree that you have no right, title or interest in the Software.
 * This code cannot simply be copied and put under another distribution license.
 * Copyright remains BBJR's, and as such any Copyright notices in the code are not to be removed.
 */
package com.github.cherry.http.client.internal;

import org.apache.http.HttpStatus;

import com.github.cherry.http.client.ServiceErrorCode;
import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.handler.ResponseHandler;
import com.github.cherry.http.client.common.utils.ExceptionFactory;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class ErrorResponseHandler implements ResponseHandler {

    /**
     * @see com.github.cherry.http.client.common.handler.ResponseHandler#handle(com.github.cherry.http.client.common.comm.ResponseMessage)
     */
    @Override
    public void handle(ResponseMessage response) {
        if (response.isSuccessful()) {
            return;
        }

        String requestId = response.getRequestId();
        int statusCode = response.getStatusCode();
        if (response.getContent() == null) {
            /**
             * When HTTP response body is null, handle status code 404 Not
             * Found, 304 Not Modified, 412 Precondition Failed especially.
             */
            if (statusCode == HttpStatus.SC_NOT_FOUND) {
                throw ExceptionFactory.createServiceException(requestId, ServiceErrorCode.NOT_FOUND, "Not Found");
            } else if (statusCode == HttpStatus.SC_NOT_MODIFIED) {
                throw ExceptionFactory.createServiceException(requestId, ServiceErrorCode.NOT_MODIFIED, "Not Modified");
            } else if (statusCode == HttpStatus.SC_PRECONDITION_FAILED) {
                throw ExceptionFactory.createServiceException(requestId, ServiceErrorCode.PRECONDITION_FAILED,
                        "Precondition Failed");
            } else {
                throw ExceptionFactory.createUnknownServiceException(requestId, statusCode);
            }
        }

        throw ExceptionFactory.createInvalidResponseException(requestId, response.getErrorResponseAsString());

    }

}
