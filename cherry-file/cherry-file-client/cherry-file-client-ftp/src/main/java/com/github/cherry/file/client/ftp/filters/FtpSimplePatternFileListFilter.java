package com.github.cherry.file.client.ftp.filters;

import org.apache.commons.net.ftp.FTPFile;

import com.github.cherry.file.client.filters.AbstractSimplePatternFileListFilter;

/**
 * Implementation of {@link AbstractSimplePatternFileListFilter} for FTP.
 *
 */
public class FtpSimplePatternFileListFilter extends AbstractSimplePatternFileListFilter<FTPFile> {

    public FtpSimplePatternFileListFilter(String pattern) {
        super(pattern);
    }


    @Override
    protected String getFilename(FTPFile file) {
        return (file != null) ? file.getName() : null;
    }

}

