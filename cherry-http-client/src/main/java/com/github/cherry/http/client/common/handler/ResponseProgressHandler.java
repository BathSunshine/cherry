package com.github.cherry.http.client.common.handler;

import java.io.InputStream;
import java.util.Map;

import com.github.cherry.http.client.HttpHeaders;
import com.github.cherry.http.client.common.comm.ResponseMessage;
import com.github.cherry.http.client.common.utils.LogUtils;
import com.github.cherry.http.client.event.ProgressInputStream;
import com.github.cherry.http.client.event.ProgressListener;
import com.github.cherry.http.client.event.ProgressPublisher;
import com.github.cherry.http.client.model.WebServiceRequest;

public class ResponseProgressHandler implements ResponseHandler {

    private final WebServiceRequest originalRequest;

    public ResponseProgressHandler(WebServiceRequest originalRequest) {
        this.originalRequest = originalRequest;
    }

    @Override
    public void handle(ResponseMessage response) {

        final ProgressListener listener = this.originalRequest.getProgressListener();
        Map<String, String> headers = response.getHeaders();
        String s = headers.get(HttpHeaders.CONTENT_LENGTH);
        if (s != null) {
            try {
                long contentLength = Long.parseLong(s);
                ProgressPublisher.publishResponseContentLength(listener, contentLength);
            } catch (NumberFormatException e) {
                LogUtils.logException("Cannot parse the Content-Length header of the response: ", e);
            }
        }

        InputStream content = response.getContent();
        if (content != null && listener != ProgressListener.NOOP) {
            InputStream progressInputStream = ProgressInputStream.inputStreamForResponse(content, originalRequest);
            response.setContent(progressInputStream);
        }
    }

}
