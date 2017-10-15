package com.github.cherry.file.client.filters;

import java.io.File;
import java.util.regex.Pattern;

/**
 * Implementation of AbstractRegexPatternMatchingFileListFilter for java.io.File instances.
 *
 */
public class RegexPatternFileListFilter extends AbstractRegexPatternFileListFilter<File> {

    public RegexPatternFileListFilter(String pattern) {
        super(pattern);
    }

    public RegexPatternFileListFilter(Pattern pattern) {
        super(pattern);
    }


    @Override
    protected String getFilename(File file) {
        return (file != null) ? file.getName() : null;
    }

}