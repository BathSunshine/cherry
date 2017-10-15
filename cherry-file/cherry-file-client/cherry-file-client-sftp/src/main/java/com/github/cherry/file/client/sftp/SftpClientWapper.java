package com.github.cherry.file.client.sftp;

import com.github.cherry.file.client.pool.AbstractPool;
import com.jcraft.jsch.Session;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpClientWapper extends SftpClient {

    private AbstractPool<SftpClientWapper> dataSource;

    public SftpClientWapper(JSchSessionWrapper wrapper) {
        super(wrapper);
    }

    public SftpClientWapper(Session session) {
        super(session);
    }

    public void close() {
        if (dataSource != null) {
            this.dataSource.returnResource(this);
        } else {
            super.close();
        }
    }

    public void setDataSource(AbstractPool<SftpClientWapper> dataSource) {
        this.dataSource = dataSource;
    }

    void closeInternal() {
        super.close();
    }
}
