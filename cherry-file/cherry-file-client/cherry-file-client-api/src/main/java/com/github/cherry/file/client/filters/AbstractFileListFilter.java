package com.github.cherry.file.client.filters;

import java.util.ArrayList;
import java.util.List;

/**
 * A convenience base class for any {@link FileListFilter} whose criteria can be
 * evaluated against each File in isolation. If the entire List of files is
 * required for evaluation, implement the FileListFilter interface directly.
 *
 * @author Scott
 * @since 1.0
 */

public abstract class AbstractFileListFilter<F> implements FileListFilter<F> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final List<F> filterFiles(F[] files) {
        List<F> accepted = new ArrayList<F>();
        if (files != null) {
            for (F file : files) {
                if (this.accept(file)) {
                    accepted.add(file);
                }
            }
        }
        return accepted;
    }

    /**
     * Subclasses must implement this method.
     *
     * @param file The file.
     * @return true if the file passes the filter.
     */
    protected abstract boolean accept(F file);

}
