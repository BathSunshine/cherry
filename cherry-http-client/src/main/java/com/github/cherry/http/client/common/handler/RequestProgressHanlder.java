package com.github.cherry.http.client.common.handler;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Map;

import com.github.cherry.http.client.HttpHeaders;
import com.github.cherry.http.client.common.comm.RequestMessage;
import com.github.cherry.http.client.common.utils.LogUtils;
import com.github.cherry.http.client.event.ProgressInputStream;
import com.github.cherry.http.client.event.ProgressListener;
import com.github.cherry.http.client.event.ProgressPublisher;
import com.github.cherry.http.client.model.WebServiceRequest;

public class RequestProgressHanlder implements RequestHandler {

    @Override
    public void handle(RequestMessage request) {

        final WebServiceRequest originalRequest = request.getOriginalRequest();
        final ProgressListener listener = originalRequest.getProgressListener();
        Map<String, String> headers = request.getHeaders();
        String s = headers.get(HttpHeaders.CONTENT_LENGTH);
        if (s != null) {
            try {
                long contentLength = Long.parseLong(s);
                ProgressPublisher.publishRequestContentLength(listener, contentLength);
            } catch (NumberFormatException e) {
                LogUtils.logException("Cannot parse the Content-Length header of the request: ", e);
            }
        }

        InputStream content = request.getContent();
        if (content == null) {
            return;
        }
        if (!content.markSupported()) {
            content = new BufferedInputStream(content);
        }
        request.setContent(listener == ProgressListener.NOOP ? content : ProgressInputStream.inputStreamForRequest(
                content, originalRequest));
    }

}
