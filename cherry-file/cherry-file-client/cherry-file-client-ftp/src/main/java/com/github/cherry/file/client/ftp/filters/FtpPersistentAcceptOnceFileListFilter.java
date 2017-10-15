package com.github.cherry.file.client.ftp.filters;

import org.apache.commons.net.ftp.FTPFile;

import com.github.cherry.file.client.filters.AbstractPersistentAcceptOnceFileListFilter;
import com.github.cherry.file.client.metadata.ConcurrentMetadataStore;

/**
 * Persistent file list filter using the server's file timestamp to detect if we've already
 * 'seen' this file.
 *
 *
 */
public class FtpPersistentAcceptOnceFileListFilter extends AbstractPersistentAcceptOnceFileListFilter<FTPFile> {

    public FtpPersistentAcceptOnceFileListFilter(ConcurrentMetadataStore store, String prefix) {
        super(store, prefix);
    }

    @Override
    protected long modified(FTPFile file) {
        return file.getTimestamp().getTimeInMillis();
    }

    @Override
    protected String fileName(FTPFile file) {
        return file.getName();
    }

}
