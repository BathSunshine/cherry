package com.github.cherry.file.client.filters;

import java.util.List;

/**
 * Strategy interface for filtering a group of files. This is a generic filter intended
 * to work with either local files or references to remote files.
 *
 * @author Scott
 * @since 1.0
 */
public interface FileListFilter<F> {

    /**
     * Filters out files and returns the files that are left in a list, or an
     * empty list when a null is passed in.
     *
     * @param files The files.
     * @return The filtered files.
     */
    List<F> filterFiles(F[] files);

}
