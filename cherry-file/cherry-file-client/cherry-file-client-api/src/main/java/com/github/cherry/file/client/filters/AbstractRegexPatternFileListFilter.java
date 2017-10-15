package com.github.cherry.file.client.filters;

import java.util.regex.Pattern;

/**
 * Filters a listing of files by qualifying their 'name'
 * against a regular expression (an instance of {@link java.util.regex.Pattern})
 *
 * @param <F> the type of file entry
 */
public abstract class AbstractRegexPatternFileListFilter<F> extends AbstractFileListFilter<F> {

    private volatile Pattern pattern;

    public AbstractRegexPatternFileListFilter(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public AbstractRegexPatternFileListFilter(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    @Override
    public boolean accept(F file) {
        return (file != null) && this.pattern.matcher(this.getFilename(file)).matches();
    }

    /**
     * Subclasses must implement this method to extract the file's name.
     *
     * @param file The file.
     * @return The file name.
     */
    protected abstract String getFilename(F file);

}
