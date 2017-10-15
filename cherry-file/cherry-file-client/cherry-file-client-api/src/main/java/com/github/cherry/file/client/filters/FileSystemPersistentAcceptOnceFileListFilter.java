package com.github.cherry.file.client.filters;

import java.io.File;

import com.github.cherry.file.client.metadata.ConcurrentMetadataStore;

/**
 *
 */
public class FileSystemPersistentAcceptOnceFileListFilter extends AbstractPersistentAcceptOnceFileListFilter<File> {

    public FileSystemPersistentAcceptOnceFileListFilter(ConcurrentMetadataStore store, String prefix) {
        super(store, prefix);
    }

    @Override
    protected long modified(File file) {
        return file.lastModified();
    }

    @Override
    protected String fileName(File file) {
        return file.getAbsolutePath();
    }

}
