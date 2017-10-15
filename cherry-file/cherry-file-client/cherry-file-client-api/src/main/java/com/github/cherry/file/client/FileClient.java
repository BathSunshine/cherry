package com.github.cherry.file.client;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public interface FileClient<F> extends Closeable {

    String FILE_SEPARATOR = "/";

    /**
     * 
     * Remove file
     * 
     * @param path 绝对路径
     * @return
     */
    boolean remove(String path);

    /**
     * 
     * 获取当前路径下的文件
     * 
     * @param path 绝对路径
     * @return
     */
    F[] list(String path);

    /**
     * 读取远程文件
     * 
     * @param source 绝对路径
     * @param outputStream
     */
    void read(String source, OutputStream outputStream);

    /**
     * 
     * 写入远程文件
     * 
     * @param inputStream
     * @param destination 绝对路径
     */
    void write(InputStream inputStream, String destination);

    /**
     * Append to a file.
     * 
     * @param inputStream the stream.
     * @param destination the destination.
     *
     */
    void append(InputStream inputStream, String destination);

    /**
     * 
     * 创建目录
     * 
     * @param directory
     * @return
     */
    boolean mkdir(String directory);

    /**
     * Remove a remote directory.
     * 
     * @param directory The directory.
     * @return True if the directory was removed.
     */
    boolean rmdir(String directory);

    /**
     * 
     * 更换名称
     * 
     * @param pathFrom
     * @param pathTo
     */
    void rename(String pathFrom, String pathTo);

    /**
     * 
     * @see java.io.Closeable#close()
     */
    @Override
    void close();

    /**
     * 
     * 是否可用
     * 
     * @return
     */
    boolean isOpen();

    /**
     * Check if the remote file or directory exists.
     * 
     * @param path the remote path.
     * @return {@code true} or {@code false} if remote path exists or not.
     */
    boolean exists(String path);

    /**
     * 
     * 获取路径下的文件名
     * 
     * @param path
     * @return
     * @throws IOException
     */
    String[] listNames(String path);

    /**
     * Retrieve a remote file as a raw {@link InputStream}.
     * 
     * @param source The path of the remote file.
     * @return The raw inputStream.
     */
    InputStream readRaw(String source);

    /**
     * Invoke after closing the InputStream from {@link #readRaw(String)}.
     * Required by some session providers.
     * 
     * @return true if successful.
     */
    boolean finalizeRaw();

}
