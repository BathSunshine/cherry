package com.github.cherry.file.client.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import com.github.cherry.file.client.FileClient;
import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.ObjectUtils;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class FtpClient implements FileClient<FTPFile> {

    private FTPClient ftpClient;

    private final AtomicBoolean readingRaw = new AtomicBoolean();

    public FtpClient(FTPClient ftpClient) {
        Assert.notNull(ftpClient, "client must not be null");
        this.ftpClient = ftpClient;
    }

    private void checkOpen() {
        if (!isAvailable()) {
            throw new FtpAccessException("ftpClient is not available");
        }
    }

    private boolean isAvailable() {
        return this.ftpClient != null && this.ftpClient.isAvailable();
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#remove(java.lang.String)
     */
    @Override
    public boolean remove(String path) {
        Assert.hasText(path, "path must not be null");
        try {
            checkOpen();
            if (!this.ftpClient.deleteFile(path)) {
                throw new IOException("Failed to delete '" + path + "'. Server replied with: "
                        + this.ftpClient.getReplyString());
            } else {
                return true;
            }
        } catch (IOException e) {
            throw new FtpAccessException("Failed to delete", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#list(java.lang.String)
     */
    @Override
    public FTPFile[] list(String path) {
        try {
            checkOpen();
            return this.ftpClient.listFiles(path);
        } catch (IOException e) {
            throw new FtpAccessException("Failed to list", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#listNames(java.lang.String)
     */
    @Override
    public String[] listNames(String path) {
        try {
            checkOpen();
            return this.ftpClient.listNames(path);
        } catch (IOException e) {
            throw new FtpAccessException("Failed to listNames", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#read(java.lang.String, java.io.OutputStream)
     */
    @Override
    public void read(String path, OutputStream fos) {
        Assert.hasText(path, "path must not be null");
        Assert.notNull(fos, "outputStream must not be null");
        try {
            checkOpen();
            boolean completed = this.ftpClient.retrieveFile(path, fos);
            if (!completed) {
                throw new IOException("Failed to copy '" + path + "'. Server replied with: "
                        + this.ftpClient.getReplyString());
            }
        } catch (IOException e) {
            throw new FtpAccessException("Failed to read", e);
        }

    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#readRaw(java.lang.String)
     */
    @Override
    public InputStream readRaw(String source) {
        try {
            if (!this.readingRaw.compareAndSet(false, true)) {
                throw new IOException("Previous raw read was not finalized");
            }
            checkOpen();
            InputStream inputStream = this.ftpClient.retrieveFileStream(source);
            if (inputStream == null) {
                throw new IOException("Failed to obtain InputStream for remote file " + source + ": "
                        + this.ftpClient.getReplyCode());
            }
            return inputStream;
        } catch (IOException e) {
            throw new FtpAccessException("Failed to readRaw", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#finalizeRaw()
     */
    @Override
    public boolean finalizeRaw() {
        try {
            if (!this.readingRaw.compareAndSet(true, false)) {
                throw new IOException("Raw read is not in process");
            }
            checkOpen();
            if (this.ftpClient.completePendingCommand()) {
                int replyCode = this.ftpClient.getReplyCode();
                return FTPReply.isPositiveCompletion(replyCode);
            }
            throw new IOException("completePendingCommandFailed");
        } catch (IOException e) {
            throw new FtpAccessException("Failed to finalizeRaw", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#write(java.io.InputStream, java.lang.String)
     */
    @Override
    public void write(InputStream inputStream, String path) {
        Assert.notNull(inputStream, "inputStream must not be null");
        Assert.hasText(path, "path must not be null or empty");
        try {
            checkOpen();
            boolean completed = this.ftpClient.storeFile(path, inputStream);
            if (!completed) {
                throw new IOException("Failed to write to '" + path + "'. Server replied with: "
                        + this.ftpClient.getReplyString());
            }
        } catch (IOException e) {
            throw new FtpAccessException("Failed to write", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#append(java.io.InputStream, java.lang.String)
     */
    @Override
    public void append(InputStream inputStream, String path) {
        Assert.notNull(inputStream, "inputStream must not be null");
        Assert.hasText(path, "path must not be null or empty");
        try {
            checkOpen();
            boolean completed = this.ftpClient.appendFile(path, inputStream);
            if (!completed) {
                throw new IOException("Failed to append to '" + path + "'. Server replied with: "
                        + this.ftpClient.getReplyString());
            }
        } catch (IOException e) {
            throw new FtpAccessException("Failed to append", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#mkdir(java.lang.String)
     */
    @Override
    public boolean mkdir(String directory) {
        try {
            checkOpen();
            return ftpClient.makeDirectory(directory);
        } catch (IOException e) {
            throw new FtpAccessException("makeDirectory failed", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#rmdir(java.lang.String)
     */
    @Override
    public boolean rmdir(String directory) {
        try {
            checkOpen();
            return ftpClient.removeDirectory(directory);
        } catch (IOException e) {
            throw new FtpAccessException("removeDirectory failed", e);
        }
    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#rename(java.lang.String, java.lang.String)
     */
    @Override
    public void rename(String pathFrom, String pathTo) {
        try {
            checkOpen();
            this.ftpClient.deleteFile(pathTo);
            boolean completed = this.ftpClient.rename(pathFrom, pathTo);
            if (!completed) {
                throw new IOException("Failed to rename '" + pathFrom + "' to " + pathTo + "'. Server replied with: "
                        + this.ftpClient.getReplyString());
            }
        } catch (IOException e) {
            throw new FtpAccessException("rename failed", e);
        }

    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#close()
     */
    @Override
    public void close() {
        try {
            if (this.readingRaw.get()) {
                finalizeRaw();
            }
            this.ftpClient.disconnect();
        } catch (Exception e) {
            // Ignored
        }

    }

    /**
     * @see com.github.cherry.file.client.comm.FileClient#isOpen()
     */
    @Override
    public boolean isOpen() {
        try {
            checkOpen();
            this.ftpClient.noop();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
  
    
    
    /**
     * @see com.github.cherry.file.client.comm.FileClient#exists(java.lang.String)
     * NOTE : 不同的ftp server 可能支持命令不同
     * 因此可能需要使用不同的命令来判断是否支持
     * <p>
     * case STAT:
     *     return client.getStatus(path) != null;
     *  case NLST:
     *      String[] names = client.listNames(path);
     *      return !ObjectUtils.isEmpty(names);   
     * </p>
     * 
     */
    @Override
    public boolean exists(String path) {
        Assert.hasText(path, "'path' must not be empty");
        try {
            checkOpen();
            String[] names = this.ftpClient.listNames(path);
            boolean exists = !ObjectUtils.isEmpty(names);
            if (!exists) {
                String currentWorkingPath = this.ftpClient.printWorkingDirectory();
                Assert.state(currentWorkingPath != null,
                        "working directory cannot be determined; exists check can not be completed");
                try {
                    exists = this.ftpClient.changeWorkingDirectory(path);
                } finally {
                    this.ftpClient.changeWorkingDirectory(currentWorkingPath);
                }

            }
            return exists;
        } catch (IOException e) {
            throw new FtpAccessException("check file exists failed", e);
        }
    }

}
