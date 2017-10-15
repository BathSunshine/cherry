package com.github.cherry.http.client.event;

import java.io.InputStream;

class RequestProgressInputStream extends ProgressInputStream {
    public RequestProgressInputStream(InputStream is, ProgressListener listener) {
        super(is, listener);
    }

    @Override
    protected void onEOF() {
        onNotifyBytesRead();
    }

    @Override
    protected void onNotifyBytesRead() {
        ProgressPublisher.publishRequestBytesTransferred(getListener(), getUnnotifiedByteCount());
    }
}
