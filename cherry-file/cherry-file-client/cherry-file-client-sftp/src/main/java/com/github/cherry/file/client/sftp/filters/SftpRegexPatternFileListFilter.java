package com.github.cherry.file.client.sftp.filters;

import java.util.regex.Pattern;

import com.github.cherry.file.client.filters.AbstractRegexPatternFileListFilter;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * Implementation of {@link AbstractRegexPatternFileListFilter} for SFTP.
 *
 */
public class SftpRegexPatternFileListFilter extends AbstractRegexPatternFileListFilter<LsEntry> {

    public SftpRegexPatternFileListFilter(String pattern) {
        super(pattern);
    }

    public SftpRegexPatternFileListFilter(Pattern pattern) {
        super(pattern);
    }

    @Override
    protected String getFilename(LsEntry entry) {
        return (entry != null) ? entry.getFilename() : null;
    }

}
