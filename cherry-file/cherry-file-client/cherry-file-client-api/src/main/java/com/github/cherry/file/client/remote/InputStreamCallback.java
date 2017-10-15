package com.github.cherry.file.client.remote;

import java.io.IOException;
import java.io.InputStream;

/**
 * Callback for stream-based file retrieval using a RemoteFileOperations.
 *
 * 
 *
 */
public interface InputStreamCallback {

    /**
     * Called with the InputStream for the remote file. The caller will
     * take care of closing the stream and finalizing the file retrieval operation after
     * this method exits.
     *
     * @param stream The InputStream.
     * @throws IOException Any IOException.
     */
    void doWithInputStream(InputStream stream) throws IOException;

}
