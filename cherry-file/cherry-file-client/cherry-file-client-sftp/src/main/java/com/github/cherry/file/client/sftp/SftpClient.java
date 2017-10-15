package com.github.cherry.file.client.sftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.github.cherry.file.client.FileClient;
import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.FileCopyUtils;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpClient implements FileClient<LsEntry> {

    private final Session jschSession;

    private final JSchSessionWrapper wrapper;

    private volatile ChannelSftp channel;

    private volatile boolean closed;

    public SftpClient(Session jschSession) {
        Assert.notNull(jschSession, "jschSession must not be null");
        this.jschSession = jschSession;
        this.wrapper = null;
    }

    public SftpClient(JSchSessionWrapper wrapper) {
        Assert.notNull(wrapper, "wrapper must not be null");
        this.jschSession = wrapper.getSession();
        this.wrapper = wrapper;
    }

    @Override
    public boolean remove(String path) {
        Assert.state(this.channel != null, "session is not connected");
        try {
            this.channel.rm(path);
            return true;
        } catch (SftpException e) {
            throw new SftpAccessException("Failed to remove file: " + path, e);
        }
    }

    @Override
    public LsEntry[] list(String path) {
        Assert.state(this.channel != null, "session is not connected");
        try {
            Vector<?> lsEntries = this.channel.ls(path);
            if (lsEntries != null) {
                LsEntry[] entries = new LsEntry[lsEntries.size()];
                for (int i = 0; i < lsEntries.size(); i++) {
                    Object next = lsEntries.get(i);
                    Assert.state(next instanceof LsEntry, "expected only LsEntry instances from channel.ls()");
                    entries[i] = (LsEntry) next;
                }
                return entries;
            }
        } catch (SftpException e) {
            throw new SftpAccessException("Failed to list files", e);
        }
        return new LsEntry[0];
    }

    @Override
    public String[] listNames(String path) {
        LsEntry[] entries = this.list(path);
        List<String> names = new ArrayList<String>();
        for (int i = 0; i < entries.length; i++) {
            String fileName = entries[i].getFilename();
            SftpATTRS attrs = entries[i].getAttrs();
            if (!attrs.isDir() && !attrs.isLink()) {
                names.add(fileName);
            }
        }
        String[] fileNames = new String[names.size()];
        return names.toArray(fileNames);
    }

    @Override
    public void read(String source, OutputStream os) {
        Assert.state(this.channel != null, "session is not connected");
        try {
            InputStream is = this.channel.get(source);
            FileCopyUtils.copy(is, os);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to read file " + source, e);
        } catch (IOException e) {
            throw new SftpAccessException("failed to read file " + source, e);
        }
    }

    @Override
    public InputStream readRaw(String source) {
        try {
            return this.channel.get(source);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to read file " + source, e);
        }
    }

    @Override
    public boolean finalizeRaw() {
        return true;
    }

    @Override
    public void write(InputStream inputStream, String destination) {
        Assert.state(this.channel != null, "session is not connected");
        try {
            this.channel.put(inputStream, destination);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to write file", e);
        }
    }

    @Override
    public void append(InputStream inputStream, String destination) {
        Assert.state(this.channel != null, "session is not connected");
        try {
            this.channel.put(inputStream, destination, ChannelSftp.APPEND);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to write file", e);
        }
    }

    @Override
    public void close() {
        this.closed = true;
        if (this.wrapper != null) {
            if (this.channel != null) {
                this.channel.disconnect();
            }
            this.wrapper.close();
        } else {
            if (this.jschSession.isConnected()) {
                this.jschSession.disconnect();
            }
        }
    }

    @Override
    public boolean isOpen() {
        return !this.closed && this.jschSession.isConnected();
    }

    @Override
    public void rename(String pathFrom, String pathTo) {
        try {
            this.channel.rename(pathFrom, pathTo);
        } catch (SftpException sftpex) {
            this.remove(pathTo);
            try {
                // attempt to rename again
                this.channel.rename(pathFrom, pathTo);
            } catch (SftpException attempt) {
                throw new SftpAccessException("failed to rename from " + pathFrom + " to " + pathTo, attempt);
            }
        }

    }

    @Override
    public boolean mkdir(String remoteDirectory) {
        try {
            this.channel.mkdir(remoteDirectory);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to create remote directory '" + remoteDirectory + "'.", e);
        }
        return true;
    }

    @Override
    public boolean rmdir(String remoteDirectory) {
        try {
            this.channel.rmdir(remoteDirectory);
        } catch (SftpException e) {
            throw new SftpAccessException("failed to remove remote directory '" + remoteDirectory + "'.", e);
        }
        return true;
    }

    @Override
    public boolean exists(String path) {
        try {
            this.channel.lstat(path);
            return true;
        } catch (SftpException e) {
            if (ChannelSftp.SSH_FX_NO_SUCH_FILE == e.id) {
                return false;
            }
            else{
                throw new SftpAccessException("failed to check remote path '" + path + "'.", e);
            }
        }
    }

    void connect() {
        try {
            if (!this.jschSession.isConnected()) {
                this.jschSession.connect();
            }
            this.channel = (ChannelSftp) this.jschSession.openChannel("sftp");
            if (this.channel != null && !this.channel.isConnected()) {
                this.channel.connect();
            }
        } catch (JSchException e) {
            this.close();
            throw new SftpAccessException("failed to connect", e);
        }
    }

}
