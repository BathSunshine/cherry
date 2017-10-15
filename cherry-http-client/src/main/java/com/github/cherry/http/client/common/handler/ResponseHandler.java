package com.github.cherry.http.client.common.handler;

import com.github.cherry.http.client.common.comm.ResponseMessage;

public interface ResponseHandler {
    public void handle(ResponseMessage response);
}
