package com.github.cherry.file.client.sftp.filters;

import com.github.cherry.file.client.filters.AbstractPersistentAcceptOnceFileListFilter;
import com.github.cherry.file.client.metadata.ConcurrentMetadataStore;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * Persistent file list filter using the server's file timestamp to detect if we've already
 * 'seen' this file.
 *
 *
 */
public class SftpPersistentAcceptOnceFileListFilter extends AbstractPersistentAcceptOnceFileListFilter<LsEntry> {

    public SftpPersistentAcceptOnceFileListFilter(ConcurrentMetadataStore store, String prefix) {
        super(store, prefix);
    }

    @Override
    protected long modified(LsEntry file) {
        return ((long) file.getAttrs().getMTime()) * 1000;
    }

    @Override
    protected String fileName(LsEntry file) {
        return file.getFilename();
    }

}
