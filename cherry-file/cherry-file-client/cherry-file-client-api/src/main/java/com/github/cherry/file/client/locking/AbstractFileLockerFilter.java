package com.github.cherry.file.client.locking;

import java.io.File;

import com.github.cherry.file.client.FileLocker;
import com.github.cherry.file.client.filters.AbstractFileListFilter;

/**
 * Convenience base class for implementing FileLockers that check a lock before accepting a file. This is needed
 * when used in combination with a FileReadingMessageSource through a DirectoryScanner.
 *
 *
 */
public abstract class AbstractFileLockerFilter extends AbstractFileListFilter<File> implements FileLocker {

    @Override
    public boolean accept(File file) {
        return this.isLockable(file);
    }

}
