package com.github.cherry.file.client.sftp;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpClientFactory implements PooledObjectFactory<SftpClientWapper> {

    private JschSessionFactory sessionFactory;

    public SftpClientFactory(SftpConfiguration configuration) {
        sessionFactory = new JschSessionFactory(configuration);
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#makeObject()
     */
    @Override
    public PooledObject<SftpClientWapper> makeObject() throws Exception {
        return new DefaultPooledObject<SftpClientWapper>(new SftpClientBuilder()
                .setSession(sessionFactory.getSession()).build());
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#destroyObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void destroyObject(PooledObject<SftpClientWapper> p) throws Exception {
        SftpClientWapper client = p.getObject();
        if (client == null) {
            return;
        }
        client.closeInternal();
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#validateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public boolean validateObject(PooledObject<SftpClientWapper> p) {
        SftpClientWapper client = p.getObject();
        if (client == null) {
            return false;
        }
        return client.isOpen();
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#activateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void activateObject(PooledObject<SftpClientWapper> p) throws Exception {
        SftpClientWapper client = p.getObject();
        if (client != null) {
            client.isOpen();
        }
    }

    /**
     * @see org.apache.commons.pool2.PooledObjectFactory#passivateObject(org.apache.commons.pool2.PooledObject)
     */
    @Override
    public void passivateObject(PooledObject<SftpClientWapper> p) throws Exception {
     

    }
}
