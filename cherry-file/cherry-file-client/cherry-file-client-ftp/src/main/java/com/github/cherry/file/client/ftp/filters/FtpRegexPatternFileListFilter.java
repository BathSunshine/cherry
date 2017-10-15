package com.github.cherry.file.client.ftp.filters;

import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPFile;

import com.github.cherry.file.client.filters.AbstractRegexPatternFileListFilter;

/**
 * Implementation of {@link AbstractRegexPatternFileListFilter} for FTP.
 *
 */
public class FtpRegexPatternFileListFilter extends AbstractRegexPatternFileListFilter<FTPFile> {

    public FtpRegexPatternFileListFilter(String pattern) {
        super(pattern);
    }

    public FtpRegexPatternFileListFilter(Pattern pattern) {
        super(pattern);
    }


    @Override
    protected String getFilename(FTPFile file) {
        return (file != null) ? file.getName() : null;
    }

}

