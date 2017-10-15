package com.github.cherry.http.client.common.io;

import java.io.IOException;
import java.io.InputStream;

public class FixedLengthInputStream extends InputStream {

    private InputStream wrappedInputStream = null;
    private long length = 0;

    public FixedLengthInputStream(InputStream instream, long length) {
        if (instream == null || length < 0) {
            throw new IllegalArgumentException("Illegal input stream or length");
        }

        this.wrappedInputStream = instream;
        this.length = length;
    }

    public void reset() throws IOException {
        wrappedInputStream.reset();
    }

    public boolean markSupported() {
        return wrappedInputStream.markSupported();
    }

    public synchronized void mark(int readlimit) {
        wrappedInputStream.mark(readlimit);
    }

    public int available() throws IOException {
        return wrappedInputStream.available();
    }

    @Override
    public long skip(long n) throws IOException {
        return wrappedInputStream.skip(n);
    }

    public InputStream getWrappedInputStream() {
        return wrappedInputStream;
    }

    public void setWrappedInputStream(InputStream instream) {
        this.wrappedInputStream = instream;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public int read() throws IOException {
        return wrappedInputStream.read();
    }
}
