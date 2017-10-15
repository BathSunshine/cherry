package com.github.cherry.file.client.sftp;

import com.github.cherry.file.client.AbstractFileInfo;
import com.github.cherry.framework.common.utils.Assert;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpATTRS;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpFileInfo extends AbstractFileInfo<LsEntry> {

    private final LsEntry lsEntry;

    private final SftpATTRS attrs;

    public SftpFileInfo(LsEntry lsEntry) {
        Assert.notNull(lsEntry, "'lsEntry' must not be null");
        this.lsEntry = lsEntry;
        this.attrs = lsEntry.getAttrs();
    }

    /**
     * @see com.jcraft.jsch.SftpATTRS#isDir()
     */
    public boolean isDirectory() {
        return this.attrs.isDir();
    }

    /**
     * @see com.jcraft.jsch.SftpATTRS#isLink()
     */
    public boolean isLink() {
        return this.attrs.isLink();
    }

    /**
     * @see com.jcraft.jsch.SftpATTRS#getSize()
     */
    public long getSize() {
        return this.attrs.getSize();
    }

    /**
     * @see com.jcraft.jsch.SftpATTRS#getMTime()
     */
    public long getModified() {
        return ((long) this.attrs.getMTime()) * 1000;
    }

    /**
     * @see com.jcraft.jsch.ChannelSftp.LsEntry#getFilename()
     */
    public String getFilename() {
        return this.lsEntry.getFilename();
    }

    public String getPermissions() {
        return this.attrs.getPermissionsString();
    }

    public LsEntry getFileInfo() {
        return this.lsEntry;
    }

}
