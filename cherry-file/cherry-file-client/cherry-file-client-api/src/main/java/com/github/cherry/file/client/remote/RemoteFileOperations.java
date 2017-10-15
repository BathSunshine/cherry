package com.github.cherry.file.client.remote;

import java.io.InputStream;

/**
 * Strategy for performing operations on remote files.
 *
 * @author Scott
 * @since 1.0
 */
public interface RemoteFileOperations<F> {
    
    /**
     * Send a file to a remote server, based on information in a inputStream.
     *
     * @param inputStream The inputStream.
     * @param mode See {@link FileExistsMode} (optional; default REPLACE). A
     * vararg is used to make the argument optional; only the first will be
     * used if more than one is provided.
     * @param remotepath The remote path to the file.
     * 
     * @return The remote path, or null if no local file was found.
     */
    String send(InputStream inputStream, String remotePath, FileExistsMode mode);

    /**
     * Retrieve a remote file as an InputStream.
     *
     * @param remotePath The remote path to the file.
     * @param callback the callback.
     * @return true if the operation was successful.
     */
    boolean get(String remotePath, InputStreamCallback callback);

    /**
     * Check if a file exists on the remote server.
     *
     * @param path The full path to the file.
     * @return true when the file exists.
     */
    boolean exists(String path);

    /**
     * Remove a remote file.
     *
     * @param path The full path to the file.
     * @return true when successful.
     */
    boolean remove(String path);

    /**
     * Rename a remote file, creating directories if needed.
     *
     * @param fromPath The current path.
     * @param toPath The new path.
     */
    void rename(String fromPath, String toPath);

    /**
     * List the files at the remote path.
     * 
     * @param path the path.
     * @return the list.
     */
    F[] list(String path);

    /**
     * Execute the callback's doInclient method after obtaining a client.
     * Reliably closes the client when the method exits.
     *
     * @param callback the SessionCallback.
     * @param <T> The type returned by {@link FileClientCallback#doInClient(com.github.cherry.file.client.FileClient)}.
     * @return The result of the callback method.
     */
    <T> T execute(FileClientCallback<F, T> callback);
}
