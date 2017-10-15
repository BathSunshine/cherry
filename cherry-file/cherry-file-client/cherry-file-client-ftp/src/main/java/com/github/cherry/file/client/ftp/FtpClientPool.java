package com.github.cherry.file.client.ftp;

import com.github.cherry.file.client.pool.AbstractPool;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class FtpClientPool extends AbstractPool<FtpClientWapper> {
    public FtpClientPool(FtpConfiguration configuration) {
        super(configuration, new FtpClientFactory(configuration));
    }

    /**
     * @see com.github.cherry.file.client.pool.AbstractPool#getResource()
     */
    @Override
    public FtpClientWapper getResource() {
        FtpClientWapper ftpClient = super.getResource();
        ftpClient.setDataSource(this);
        return ftpClient;
    }

    @Override
    public void returnBrokenResource(final FtpClientWapper resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    @Override
    public void returnResource(final FtpClientWapper resource) {
        if (resource != null) {
            try {
                returnResourceObject(resource);
            } catch (Exception e) {
                returnBrokenResource(resource);
                throw new FtpAccessException("Could not return the resource to the pool", e);
            }
        }
    }
}
