package com.github.cherry.file.client.locking;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.github.cherry.file.client.FileAccessException;

/**
 * File locking strategy that uses java.nio. The locks taken by FileChannel are shared with all the threads in a single
 * JVM, so this locking strategy <b>does not</b> prevent files being picked up multiple times within the same JVM.
 * <p>
 * This implementation will acquire or create a {@link FileLock} for the given file. Caching locks might be expensive,
 * so this locking strategy is not recommended for scenarios where many files are accessed in parallel.
 *
 */
public class NioFileLocker extends AbstractFileLockerFilter {

    private final ConcurrentMap<File, FileLock> lockCache = new ConcurrentHashMap<File, FileLock>();

    /**
     * {@inheritDoc}
     */
    public boolean lock(File fileToLock) {
        FileLock lock = this.lockCache.get(fileToLock);
        if (lock == null) {
            FileLock newLock = null;
            try {
                newLock = FileChannelCache.tryLockFor(fileToLock);
            } catch (IOException e) {
                throw new FileAccessException("Failed to lock file: " + fileToLock, e);
            }
            if (newLock != null) {
                FileLock original = this.lockCache.putIfAbsent(fileToLock, newLock);
                lock = original != null ? original : newLock;
            }
        }
        return lock != null;
    }

    public boolean isLockable(File file) {
        return this.lockCache.containsKey(file) || !FileChannelCache.isLocked(file);
    }

    public void unlock(File fileToUnlock) {
        FileLock fileLock = this.lockCache.get(fileToUnlock);
        try {
            if (fileLock != null) {
                fileLock.release();
            }
            FileChannelCache.closeChannelFor(fileToUnlock);
        } catch (IOException e) {
            throw new FileAccessException("Failed to unlock file: " + fileToUnlock, e);
        }
    }
}
