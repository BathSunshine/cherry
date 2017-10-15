package com.github.cherry.http.client.common.handler;

import com.github.cherry.http.client.common.comm.RequestMessage;

public interface RequestHandler {
    public void handle(RequestMessage request);
}
