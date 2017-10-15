package com.github.cherry.file.client.remote;

import com.github.cherry.file.client.FileClient;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class FileClientCallbackWithoutResult<F> implements FileClientCallback<F, Object> {

    @Override
    public Object doInClient(FileClient<F> client) {
        doInClientWithoutResult(client);
        return null;
    }

    /**
     * Called within the context of a FileClient.
     * Perform some operation(s) on the FileClient. The caller will take
     * care of closing the session after this method exits.
     *
     * @param client The FileClient.
     */
    protected abstract void doInClientWithoutResult(FileClient<F> client);

}
