package com.github.cherry.file.client;

import java.util.Date;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class AbstractFileInfo<F> implements FileInfo<F>, Comparable<FileInfo<F>> {

    private String remoteDirectory;

    /**
     * @param remoteDirectory the remoteDirectory to set
     */
    public void setRemoteDirectory(String remoteDirectory) {
        this.remoteDirectory = remoteDirectory;
    }

    public String getRemoteDirectory() {
        return this.remoteDirectory;
    }

    public String toString() {
        return "FileInfo [isDirectory=" + isDirectory() + ", isLink=" + isLink() + ", Size=" + getSize()
                + ", ModifiedTime=" + new Date(getModified()) + ", Filename=" + getFilename() + ", RemoteDirectory="
                + getRemoteDirectory() + ", Permissions=" + getPermissions() + "]";
    }

    public int compareTo(FileInfo<F> o) {
        return this.getFilename().compareTo(o.getFilename());
    }

}
