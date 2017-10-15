package com.github.cherry.http.client.common.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

import com.github.cherry.http.client.ClientException;
import com.github.cherry.http.client.common.utils.LogUtils;

public class RepeatableBoundedFileInputStream extends InputStream {

    private BoundedInputStream bis = null;

    private FileChannel fileChannel = null;

    private long markPos = 0;

    public RepeatableBoundedFileInputStream(BoundedInputStream bis) throws IOException {
        FileInputStream fin = (FileInputStream) bis.getWrappedInputStream();
        this.bis = bis;
        this.fileChannel = fin.getChannel();
        this.markPos = fileChannel.position();
    }

    public void reset() throws IOException {
        bis.backoff(fileChannel.position() - markPos);
        fileChannel.position(markPos);
        LogUtils.getLog().trace("Reset to position " + markPos);
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int readlimit) {
        try {
            markPos = fileChannel.position();
        } catch (IOException e) {
            throw new ClientException("Failed to mark file position", e);
        }
        LogUtils.getLog().trace("File input stream marked at position " + markPos);
    }

    public int available() throws IOException {
        return bis.available();
    }

    public void close() throws IOException {
        bis.close();
    }

    public int read() throws IOException {
        return bis.read();
    }

    @Override
    public long skip(long n) throws IOException {
        return bis.skip(n);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        return bis.read(b, off, len);
    }

    public InputStream getWrappedInputStream() {
        return this.bis;
    }

}
