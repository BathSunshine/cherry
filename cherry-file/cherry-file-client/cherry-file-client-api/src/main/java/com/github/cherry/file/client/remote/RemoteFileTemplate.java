package com.github.cherry.file.client.remote;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.cherry.file.client.FileAccessException;
import com.github.cherry.file.client.FileClient;
import com.github.cherry.file.client.pool.AbstractPool;
import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.IOUtils;
import com.github.cherry.framework.common.utils.StringUtils;

/**
 * A general abstraction for dealing with remote files.
 *
 * @author Scott
 * @since 1.0
 */
public class RemoteFileTemplate<F> implements RemoteFileOperations<F> {

    private final Log logger = LogFactory.getLog(this.getClass());

    private volatile String remoteFileSeparator = "/";

    private volatile boolean autoCreateDirectory = false;

    private final AbstractPool<? extends FileClient<F>> clientFactory;

    public RemoteFileTemplate(AbstractPool<? extends FileClient<F>> clientFactory) {
        Assert.notNull(clientFactory, "clientFactory must not be null");
        this.clientFactory = clientFactory;
    }

    
    /**
     * Set the file separator when dealing with remote files; default '/'.
     * 
     * @param remoteFileSeparator the separator.
     */
    public void setRemoteFileSeparator(String remoteFileSeparator) {
        Assert.notNull(remoteFileSeparator, "'remoteFileSeparator' must not be null");
        this.remoteFileSeparator = remoteFileSeparator;
    }

    /**
     * @return the remote file separator.
     */
    public final String getRemoteFileSeparator() {
        return this.remoteFileSeparator;
    }

    /**
     * Determine whether the remote directory should automatically be created when
     * sending files to the remote system.
     * 
     * @param autoCreateDirectory true to create the directory.
     */
    public void setAutoCreateDirectory(boolean autoCreateDirectory) {
        this.autoCreateDirectory = autoCreateDirectory;
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#send(java.io.InputStream, java.lang.String,
     * com.github.cherry.file.client.remote.FileExistsMode)
     */
    @Override
    public String send(final InputStream inputStream, final String remotePath, final FileExistsMode mode) {
        Assert.hasText(StringUtils.getFilenameExtension(remotePath), "'remotePath' must be current ");
        if (inputStream != null) {
            try {
                return this.execute(new FileClientCallback<F, String>() {

                    @Override
                    public String doInClient(FileClient<F> client) {
                        String diretory = parseDiretoryPath(remotePath);
                        if (autoCreateDirectory) {
                            try {
                                RemoteFileUtils.makeDirectories(diretory, client, remoteFileSeparator, logger);
                            } catch (IllegalStateException e) {
                                client.mkdir(diretory);
                            }
                        }

                        if (FileExistsMode.REPLACE.equals(mode)) {
                            client.write(inputStream, remotePath);
                        } else if (FileExistsMode.APPEND.equals(mode)) {
                            client.append(inputStream, remotePath);
                        } else {
                            if (exists(remotePath)) {
                                if (FileExistsMode.FAIL.equals(mode)) {
                                    throw new FileAccessException("The destination file already exists at '"
                                            + remotePath + "'.");
                                }
                            } else {
                                client.write(inputStream, remotePath);
                            }
                        }

                        return remotePath;
                    }
                });

            } finally {
                IOUtils.closeQuietly(inputStream);
            }
        } else {
            return null;
        }
    }

    private String parseDiretoryPath(String remotePath) {
        String directoryPath = null;
        int path = remotePath.lastIndexOf(this.remoteFileSeparator);
        if (path > -1) {
            directoryPath = remotePath.substring(0, path);
        }

        if (!StringUtils.hasText(directoryPath)) {
            directoryPath = this.remoteFileSeparator;
        } else if (!directoryPath.endsWith(this.remoteFileSeparator)) {
            directoryPath += this.remoteFileSeparator;
        }
        return directoryPath;

    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#get(java.lang.String,
     * com.github.cherry.file.client.remote.InputStreamCallback)
     */
    @Override
    public boolean get(final String remotePath, final InputStreamCallback callback) {
        Assert.notNull(remotePath, "'remotePath' cannot be null");
        return this.execute(new FileClientCallback<F, Boolean>() {

            @Override
            public Boolean doInClient(FileClient<F> client) {
                try {
                    InputStream inputStream = client.readRaw(remotePath);
                    callback.doWithInputStream(inputStream);
                    inputStream.close();
                    return client.finalizeRaw();
                } catch (IOException e) {
                    throw new FileAccessException("file access failed", e);
                }

            }
        });
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#exists(java.lang.String)
     */
    @Override
    public boolean exists(final String path) {
        return this.execute(new FileClientCallback<F, Boolean>() {
            @Override
            public Boolean doInClient(FileClient<F> client) {
                return client.exists(path);
            }
        });
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#remove(java.lang.String)
     */
    @Override
    public boolean remove(final String path) {
        return this.execute(new FileClientCallback<F, Boolean>() {
            @Override
            public Boolean doInClient(FileClient<F> client) {
                return client.remove(path);
            }
        });
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#rename(java.lang.String, java.lang.String)
     */
    @Override
    public void rename(final String fromPath, final String toPath) {
        Assert.hasText(fromPath, "Old filename cannot be null or empty");
        Assert.hasText(toPath, "New filename cannot be null or empty");
        this.execute(new FileClientCallbackWithoutResult<F>() {
            @Override
            protected void doInClientWithoutResult(FileClient<F> client) {
                int lastSeparator = toPath.lastIndexOf(RemoteFileTemplate.this.remoteFileSeparator);
                if (lastSeparator > 0) {
                    String remoteFileDirectory = toPath.substring(0, lastSeparator + 1);
                    RemoteFileUtils.makeDirectories(remoteFileDirectory, client,
                            RemoteFileTemplate.this.remoteFileSeparator, RemoteFileTemplate.this.logger);
                }
                client.rename(fromPath, toPath);
            }
        });
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#list(java.lang.String)
     */
    @Override
    public F[] list(final String path) {
        return this.execute(new FileClientCallback<F, F[]>() {

            @Override
            public F[] doInClient(FileClient<F> client) {
                return client.list(path);
            }
        });
    }

    /**
     * @see com.github.cherry.file.client.remote.RemoteFileOperations#execute(com.github.cherry.file.client.remote.FileClientCallback)
     */
    @Override
    public <T> T execute(FileClientCallback<F, T> callback) {
        FileClient<F> client = null;
        try {
            client = this.clientFactory.getResource();
            Assert.notNull(client, "failed to acquire a Session");
            return callback.doInClient(client);
        } catch (Exception e) {
            throw new FileAccessException("Failed to execute on client", e);
        } finally {
            IOUtils.closeQuietly(client);
        }
    }

}
