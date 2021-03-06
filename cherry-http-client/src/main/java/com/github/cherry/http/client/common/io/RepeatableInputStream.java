package com.github.cherry.http.client.common.io;

import java.io.IOException;
import java.io.InputStream;

import com.github.cherry.http.client.common.utils.LogUtils;

public class RepeatableInputStream extends InputStream {

    private InputStream is = null;

    private int bufferSize = 0;

    private int bufferOffset = 0;

    private long bytesReadFromMark = 0;

    private byte[] buffer = null;

    public RepeatableInputStream(InputStream inputStream, int bufferSize) {
        if (inputStream == null) {
            throw new IllegalArgumentException("inputStream should not be null");
        }

        this.is = inputStream;
        this.bufferSize = bufferSize;
        this.buffer = new byte[this.bufferSize];
    }

    public void reset() throws IOException {
        if (bytesReadFromMark <= bufferSize) {
            LogUtils.getLog().debug("Reset after reading " + bytesReadFromMark + " bytes.");
            bufferOffset = 0;
        } else {
            throw new IOException("Input stream cannot be reset as " + this.bytesReadFromMark
                    + " bytes have been written, exceeding the available buffer size of " + this.bufferSize);
        }
    }

    public boolean markSupported() {
        return true;
    }

    public synchronized void mark(int readlimit) {
        if (bytesReadFromMark <= bufferSize && buffer != null) {
            byte[] newBuffer = new byte[this.bufferSize];
            System.arraycopy(buffer, bufferOffset, newBuffer, 0, (int) (bytesReadFromMark - bufferOffset));
            this.buffer = newBuffer;
            this.bytesReadFromMark -= bufferOffset;
            this.bufferOffset = 0;
        } else {
            this.bufferOffset = 0;
            this.bytesReadFromMark = 0;
            this.buffer = new byte[this.bufferSize];
        }
    }

    public int available() throws IOException {
        return is.available();
    }

    public void close() throws IOException {
        is.close();
    }

    public int read(byte[] out, int outOffset, int outLength) throws IOException {
        byte[] tmp = new byte[outLength];

        if (bufferOffset < bytesReadFromMark && buffer != null) {
            int bytesFromBuffer = tmp.length;
            if (bufferOffset + bytesFromBuffer > bytesReadFromMark) {
                bytesFromBuffer = (int) bytesReadFromMark - bufferOffset;
            }

            System.arraycopy(buffer, bufferOffset, out, outOffset, bytesFromBuffer);
            bufferOffset += bytesFromBuffer;
            return bytesFromBuffer;
        }

        int count = is.read(tmp);

        if (count <= 0) {
            return count;
        }

        if (bytesReadFromMark + count <= bufferSize) {
            System.arraycopy(tmp, 0, buffer, (int) bytesReadFromMark, count);
            bufferOffset += count;
        } else if (buffer != null) {
            LogUtils.getLog().debug(
                    "Buffer size " + bufferSize + " has been exceeded and the input stream "
                            + "will not be repeatable until the next mark. Freeing buffer memory");
            buffer = null;
        }

        System.arraycopy(tmp, 0, out, outOffset, count);
        bytesReadFromMark += count;

        return count;
    }

    public int read() throws IOException {
        byte[] tmp = new byte[1];
        int count = read(tmp);
        if (count != -1) {
            return tmp[0];
        } else {
            return count;
        }
    }

    public InputStream getWrappedInputStream() {
        return is;
    }

}
