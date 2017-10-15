package com.github.cherry.file.client.ftp;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 池对象工厂
 *
 * @author Scott
 * @since 1.0
 */
public class FtpClientFactory implements PooledObjectFactory<FtpClientWapper> {

    private FtpConfiguration ftpConfiguration;

    public FtpClientFactory(FtpConfiguration ftpConfiguration) {
        this.ftpConfiguration = ftpConfiguration;
    }

    /**
     * ftpConfiguration.
     *
     * @return the ftpConfiguration
     * @since 1.0
     */
    public FtpConfiguration getFtpConfiguration() {
        return ftpConfiguration;
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#makeObject()
     */
    @Override
    public PooledObject<FtpClientWapper> makeObject() throws Exception {
        return new DefaultPooledObject<FtpClientWapper>(new FtpClientBuilder().setConfiguration(ftpConfiguration)
                .build());
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#destroyObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void destroyObject(PooledObject<FtpClientWapper> p) throws Exception {
        FtpClientWapper client = p.getObject();
        if (client == null) {
            return;
        }
        client.closeInternal();
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#validateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public boolean validateObject(PooledObject<FtpClientWapper> p) {
        FtpClientWapper client = p.getObject();
        if (client == null) {
            return false;
        }
        return client.isOpen();
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#activateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void activateObject(PooledObject<FtpClientWapper> p) throws Exception {
        FtpClientWapper client = p.getObject();
        if (client != null) {
            client.isOpen();
        }
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#passivateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void passivateObject(PooledObject<FtpClientWapper> p) throws Exception {

    }

}
