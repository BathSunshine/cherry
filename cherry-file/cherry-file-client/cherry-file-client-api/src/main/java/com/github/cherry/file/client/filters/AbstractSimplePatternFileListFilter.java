package com.github.cherry.file.client.filters;

import com.github.cherry.framework.common.utils.AntPathMatcher;

/**
 * Base class for filters that support ant style path expressions, which are less powerful
 * but more readable than regular expressions. This filter only filters on the name of the
 * file, the rest of the path is ignored.
 *
 */
public abstract class AbstractSimplePatternFileListFilter<F> extends AbstractFileListFilter<F> {

    private final AntPathMatcher matcher = new AntPathMatcher();

    private final String path;


    public AbstractSimplePatternFileListFilter(String path) {
        this.path = path;
    }


    /**
     * Accepts the given file if its name matches the pattern.
     */
    @Override
    public final boolean accept(F file) {
        return this.matcher.match(this.path, this.getFilename(file));
    }

    /**
     * Subclasses must implement this method to extract the file's name.
     *
     * @param file The file.
     * @return The file name.
     */
    protected abstract String getFilename(F file);

}
