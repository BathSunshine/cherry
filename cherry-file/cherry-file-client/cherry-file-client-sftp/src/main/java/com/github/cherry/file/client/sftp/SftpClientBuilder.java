package com.github.cherry.file.client.sftp;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpClientBuilder {
    private JSchSessionWrapper session;

    public SftpClientBuilder() {
    }

    public SftpClientBuilder setSession(JSchSessionWrapper session) {
        this.session = session;
        return this;
    }

    public SftpClientWapper build() {
        SftpClientWapper client = new SftpClientWapper(session);
        client.connect();
        session.addChannel();
        return client;
    }

}
