package com.github.cherry.file.client.filters;

/**
 * Simple implementation of {@link FileListFilter} that always returns true.
 * Suitable as a default.
 *
 *
 * @param <F> The type that will be filtered.
 */
public class AcceptAllFileListFilter<F> extends AbstractFileListFilter<F> {

    @Override
    public boolean accept(F file) {
        return true;
    }

}
