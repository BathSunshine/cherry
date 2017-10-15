package com.github.cherry.file.client.sftp.filters;

import com.github.cherry.file.client.filters.AbstractSimplePatternFileListFilter;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * Implementation of {@link AbstractSimplePatternFileListFilter} for SFTP.
 *
 */
public class SftpSimplePatternFileListFilter extends AbstractSimplePatternFileListFilter<LsEntry> {

    public SftpSimplePatternFileListFilter(String pattern) {
        super(pattern);
    }

    @Override
    protected String getFilename(LsEntry entry) {
        return (entry != null) ? entry.getFilename() : null;
    }

}
