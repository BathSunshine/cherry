package com.github.cherry.file.client.test.sftp;

import java.io.FileNotFoundException;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;

import com.github.cherry.file.client.remote.RemoteFileOperations;
import com.github.cherry.file.client.remote.RemoteFileTemplate;
import com.github.cherry.file.client.sftp.SftpClientPool;
import com.github.cherry.file.client.sftp.SftpConfiguration;
import com.jcraft.jsch.ChannelSftp.LsEntry;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class TestSftpCase {

    @Test
    public void test01() throws FileNotFoundException {
        SftpConfiguration configuration = new SftpConfiguration();
        configuration.setHost("192.168.151.12");
        configuration.setPort(22);
        configuration.setUser("dafeisftp");
        configuration.setPassword("dafeisftp");
        Properties sshConfig = new Properties();

        sshConfig.put("StrictHostKeyChecking", "no");
        configuration.setSessionConfig(sshConfig);

        SftpClientPool pool = new SftpClientPool(configuration);

        RemoteFileOperations<LsEntry> client = new RemoteFileTemplate<LsEntry>(pool);
        
        LsEntry[] f = client.list("/frbao/download");
        
        Assert.assertNotNull(f);
    }
}
