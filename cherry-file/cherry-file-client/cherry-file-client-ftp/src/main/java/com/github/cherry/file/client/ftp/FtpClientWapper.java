package com.github.cherry.file.client.ftp;

import org.apache.commons.net.ftp.FTPClient;

import com.github.cherry.file.client.pool.AbstractPool;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class FtpClientWapper extends FtpClient {

    private AbstractPool<FtpClientWapper> dataSource;

    public FtpClientWapper(FTPClient ftpClient) {
        super(ftpClient);
    }

    public void setDataSource(AbstractPool<FtpClientWapper> dataSource) {
        this.dataSource = dataSource;
    }

    public void close() {
        if (dataSource != null) {
            this.dataSource.returnResource(this);
        } else {
            super.close();
        }
    }
    
    void closeInternal(){
        super.close();
    }
    
}
