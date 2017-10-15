package com.github.cherry.file.client;

import java.io.File;

/**
 * 
 * A FileLocker is a strategy that can ensure that files are only processed a
 * single time. Implementations are free to implement any relation between
 * locking and unlocking. This means that there are no safety guarantees in the
 * contract, defining these guarantees is up to the implementation.
 *
 * @author Scott
 * @since 1.0
 */
public interface FileLocker {

    /**
     * Tries to lock the given file and returns <code>true</code> if it was
     * successful, <code>false</code> otherwise.
     *
     * @param fileToLock the file that should be locked according to this locker
     * @return true if successful.
     */
    boolean lock(File fileToLock);

    /**
     * Checks whether the file passed in can be locked by this locker. This method never changes the locked state.
     *
     * @param file The file.
     * @return true if the file was locked by another locker than this locker
     */
    boolean isLockable(File file);

    /**
     * Unlocks the given file.
     *
     * @param fileToUnlock the file that should be unlocked according to this locker
     */
    void unlock(File fileToUnlock);

}
