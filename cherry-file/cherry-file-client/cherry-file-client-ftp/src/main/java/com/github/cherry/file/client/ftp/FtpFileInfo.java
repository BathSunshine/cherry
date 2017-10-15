package com.github.cherry.file.client.ftp;

import org.apache.commons.net.ftp.FTPFile;

import com.github.cherry.file.client.AbstractFileInfo;
import com.github.cherry.framework.common.utils.Assert;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class FtpFileInfo extends AbstractFileInfo<FTPFile> {

    private final FTPFile ftpFile;

    public FtpFileInfo(FTPFile ftpFile) {
        Assert.notNull(ftpFile, "FTPFile must not be null");
        this.ftpFile = ftpFile;
    }

    @Override
    public boolean isDirectory() {
        return this.ftpFile.isDirectory();
    }

    @Override
    public boolean isLink() {
        return this.ftpFile.isSymbolicLink();
    }

    @Override
    public long getSize() {
        return this.ftpFile.getSize();
    }

    @Override
    public long getModified() {
        return this.ftpFile.getTimestamp().getTimeInMillis();
    }

    @Override
    public String getFilename() {
        return this.ftpFile.getName();
    }

    @Override
    public String getPermissions() {
        StringBuilder sb = new StringBuilder();
        if (this.ftpFile.isDirectory()) {
            sb.append("d");
        } else if (this.ftpFile.isSymbolicLink()) {
            sb.append("l");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.READ_PERMISSION)) {
            sb.append("r");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.WRITE_PERMISSION)) {
            sb.append("w");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.USER_ACCESS, FTPFile.EXECUTE_PERMISSION)) {
            sb.append("x");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.READ_PERMISSION)) {
            sb.append("r");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.WRITE_PERMISSION)) {
            sb.append("w");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.GROUP_ACCESS, FTPFile.EXECUTE_PERMISSION)) {
            sb.append("x");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.READ_PERMISSION)) {
            sb.append("r");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.WRITE_PERMISSION)) {
            sb.append("w");
        } else {
            sb.append("-");
        }
        if (this.ftpFile.hasPermission(FTPFile.WORLD_ACCESS, FTPFile.EXECUTE_PERMISSION)) {
            sb.append("x");
        } else {
            sb.append("-");
        }
        return sb.toString();
    }

    @Override
    public FTPFile getFileInfo() {
        return this.ftpFile;
    }
}
