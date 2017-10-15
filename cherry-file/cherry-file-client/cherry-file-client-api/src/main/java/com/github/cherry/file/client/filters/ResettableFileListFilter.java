package com.github.cherry.file.client.filters;

/**
 * A {@link FileListFilter} that can be reset by removing a specific file from its
 * state.
 *
 */
public interface ResettableFileListFilter<F> extends FileListFilter<F> {

    /**
     * Remove the specified file from the filter so it will pass on the next attempt.
     * 
     * @param f the element to remove.
     * @return true if the file was removed as a result of this call.
     */
    boolean remove(F f);

}
