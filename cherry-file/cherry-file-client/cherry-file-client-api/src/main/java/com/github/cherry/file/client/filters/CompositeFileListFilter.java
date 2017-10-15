package com.github.cherry.file.client.filters;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.github.cherry.framework.common.utils.Assert;

/**
 * Simple {@link FileListFilter} that predicates its matches against <b>all</b> of the
 * configured {@link FileListFilter}.
 * 
 * @param <F> The type that will be filtered.
 *
 * @author Scott
 * @since 1.0
 */
public class CompositeFileListFilter<F> implements ReversibleFileListFilter<F>, ResettableFileListFilter<F>, Closeable {

    protected final Set<FileListFilter<F>> fileFilters; // NOSONAR

    public CompositeFileListFilter() {
        this.fileFilters = new LinkedHashSet<FileListFilter<F>>();
    }

    public CompositeFileListFilter(Collection<? extends FileListFilter<F>> fileFilters) {
        this.fileFilters = new LinkedHashSet<FileListFilter<F>>(fileFilters);
    }

    @Override
    public void close() throws IOException {
        for (FileListFilter<F> filter : this.fileFilters) {
            if (filter instanceof Closeable) {
                ((Closeable) filter).close();
            }
        }
    }

    public CompositeFileListFilter<F> addFilter(FileListFilter<F> filter) {
        return this.addFilters(Collections.singletonList(filter));
    }

    /**
     * @param filters one or more new filters to add
     * @return this CompositeFileFilter instance with the added filters
     * @see #addFilters(Collection)
     */
    @SuppressWarnings("unchecked")
    public CompositeFileListFilter<F> addFilters(FileListFilter<F>... filters) {
        return addFilters(Arrays.asList(filters));
    }

    /**
     * Not thread safe. Only a single thread may add filters at a time.
     * <p>
     * Add the new filters to this CompositeFileListFilter while maintaining the existing filters.
     *
     * @param filtersToAdd a list of filters to add
     * @return this CompositeFileListFilter instance with the added filters
     */
    public CompositeFileListFilter<F> addFilters(Collection<? extends FileListFilter<F>> filtersToAdd) {
        this.fileFilters.addAll(filtersToAdd);
        return this;
    }

    @Override
    public List<F> filterFiles(F[] files) {
        Assert.notNull(files, "'files' should not be null");
        List<F> results = new ArrayList<F>(Arrays.asList(files));
        for (FileListFilter<F> fileFilter : this.fileFilters) {
            List<F> currentResults = fileFilter.filterFiles(files);
            results.retainAll(currentResults);
        }
        return results;
    }

    @Override
    public void rollback(F file, List<F> files) {
        for (FileListFilter<F> fileFilter : this.fileFilters) {
            if (fileFilter instanceof ReversibleFileListFilter) {
                ((ReversibleFileListFilter<F>) fileFilter).rollback(file, files);
            }
        }
    }

    @Override
    public boolean remove(F f) {
        boolean removed = false;
        for (FileListFilter<F> fileFilter : this.fileFilters) {
            if (fileFilter instanceof ResettableFileListFilter) {
                ((ResettableFileListFilter<F>) fileFilter).remove(f);
                removed = true;
            }
        }
        return removed;
    }

}
