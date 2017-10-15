package com.github.cherry.file.client.sftp;

import com.github.cherry.file.client.sftp.SftpAccessException;
import com.github.cherry.file.client.pool.AbstractPool;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpClientPool extends AbstractPool<SftpClientWapper> {
    public SftpClientPool(SftpConfiguration configuration) {
        super(configuration, new SftpClientFactory(configuration));
    }

    /**
     * @see com.github.cherry.file.client.pool.AbstractPool#getResource()
     */
    @Override
    public SftpClientWapper getResource() {
        SftpClientWapper sftpClient = super.getResource();
        sftpClient.setDataSource(this);
        return sftpClient;
    }

    @Override
    public void returnBrokenResource(final SftpClientWapper resource) {
        if (resource != null) {
            returnBrokenResourceObject(resource);
        }
    }

    @Override
    public void returnResource(final SftpClientWapper resource) {
        if (resource != null) {
            try {
                returnResourceObject(resource);
            } catch (Exception e) {
                returnBrokenResource(resource);
                throw new SftpAccessException("Could not return the resource to the pool", e);
            }
        }
    }
}
