package com.github.cherry.file.client.test.ftp;

import java.io.FileNotFoundException;

import junit.framework.Assert;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.Test;

import com.github.cherry.file.client.ftp.FtpClientPool;
import com.github.cherry.file.client.ftp.FtpConfiguration;
import com.github.cherry.file.client.remote.RemoteFileOperations;
import com.github.cherry.file.client.remote.RemoteFileTemplate;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class TestFtpCase {

    @Test
    public void test01() throws FileNotFoundException {
        FtpConfiguration configuration = new FtpConfiguration();
        configuration.setUsername("ftpuser");
        configuration.setPassword("ftpuser");
        configuration.setServer("192.168.151.11");
        configuration.setPort(21);

        FtpClientPool pool = new FtpClientPool(configuration);
        
        RemoteFileOperations<FTPFile> fileOperations = new RemoteFileTemplate<FTPFile>(pool);
        
        FTPFile[] files = fileOperations.list("/FTPUpload");
        
        pool.close();
        Assert.assertNotNull(files);
        
    }
}
