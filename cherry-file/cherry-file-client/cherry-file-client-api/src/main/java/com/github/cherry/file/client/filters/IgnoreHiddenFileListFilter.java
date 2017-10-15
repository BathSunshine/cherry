package com.github.cherry.file.client.filters;

import java.io.File;

/**
 * {@link FileListFilter} implementation that ignores any hidden files. Uses {@link File#isHidden()} to make that
 * determination.
 */
public class IgnoreHiddenFileListFilter extends AbstractFileListFilter<File> {

    /**
     * @return Returns {@code true} for any non-hidden files.
     */
    @Override
    protected boolean accept(File file) {
        return !file.isHidden();
    }

}
