package com.github.cherry.http.client.event;

import java.io.InputStream;

class ResponseProgressInputStream extends ProgressInputStream {

    public ResponseProgressInputStream(InputStream is, ProgressListener listener) {
        super(is, listener);
    }

    @Override
    protected void onEOF() {
        onNotifyBytesRead();
    }

    @Override
    protected void onNotifyBytesRead() {
        ProgressPublisher.publishResponseBytesTransferred(getListener(), getUnnotifiedByteCount());
    }
}
