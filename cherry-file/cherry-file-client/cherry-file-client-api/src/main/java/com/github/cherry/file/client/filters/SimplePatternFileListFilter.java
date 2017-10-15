package com.github.cherry.file.client.filters;

import java.io.File;

/**
 * Filter that supports ant style path expressions, which are less powerful but more readable than regular expressions.
 * This filter only filters on the name of the file, the rest of the path is ignored.
 *
 * @author Scott
 * @since 1.0
 */
public class SimplePatternFileListFilter extends AbstractSimplePatternFileListFilter<File> {

    public SimplePatternFileListFilter(String path) {
        super(path);
    }


    @Override
    protected String getFilename(File file) {
        return file.getName();
    }

}

