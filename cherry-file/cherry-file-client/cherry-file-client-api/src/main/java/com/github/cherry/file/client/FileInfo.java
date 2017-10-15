package com.github.cherry.file.client;

/**
 *〈一句话功能简述〉
 *〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public interface FileInfo<F> {

    /**
     * @return true if the remote file is a directory
     */
    boolean isDirectory();

    /**
     * @return true if the remote file is a link
     */
    boolean isLink();

    /**
     * @return the size of the remote file
     */
    long getSize();

    /**
     * @return the modified time of the remote file
     */
    long getModified();

    /**
     * @return the name of the remote file
     */
    String getFilename();

    /**
     * @return the remote directory in which the file resides
     */
    String getRemoteDirectory();

    /**
     * @return a string representing the permissions of the remote
     * file (e.g. -rw-r--r--).
     */
    String getPermissions();

    /**
     * @return the actual implementation from the underlying
     * library,  more sophisticated access is needed.
     */
    F getFileInfo();
}
