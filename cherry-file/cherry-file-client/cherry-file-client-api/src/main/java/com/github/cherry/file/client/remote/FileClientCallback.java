package com.github.cherry.file.client.remote;

import com.github.cherry.file.client.FileClient;

/**
 * Callback invoked by {@code RemoteFileOperations.execute()} - allows multiple operations
 * on a client.
 *
 * @author Scott
 * @since 1.0
 */
public interface FileClientCallback<F, T> {

    /**
     * Called within the context of a FileClient.
     * Perform some operation(s) on the FileClient. The caller will take
     * care of closing the FileClient after this method exits.
     *
     * @param client The Client.
     * @return The result of type T.
     */
    T doInClient(FileClient<F> client);
}
