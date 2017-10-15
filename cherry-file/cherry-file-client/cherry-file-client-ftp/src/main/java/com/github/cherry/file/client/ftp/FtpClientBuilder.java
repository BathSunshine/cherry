package com.github.cherry.file.client.ftp;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPHTTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class FtpClientBuilder {
    private FtpConfiguration configuration;

    public FtpClientBuilder() {
    }

    public FtpClientBuilder setConfiguration(FtpConfiguration configuration) {
        this.configuration = configuration;
        return this;
    }

    public FtpClientWapper build() {
        FtpClientWapper innerClient = null;
        FTPClient client;
        if (configuration.isUseHttpProxy()) {
            client = new FTPHTTPClient(configuration.getProxyHost(), configuration.getProxyPort(),
                    configuration.getProxyUsername(), configuration.getProxyPassword());
        } else {
            client = new FTPClient();
        }

        FTPClientConfig config = new FTPClientConfig(configuration.getServerSystemKey());
        config.setDefaultDateFormatStr(configuration.getDefaultDateFormatStr());
        config.setRecentDateFormatStr(configuration.getRecentDateFormatStr());
        config.setServerLanguageCode(configuration.getServerLanguageCode());
        config.setServerTimeZoneId(configuration.getServerTimeZoneId());
        config.setUnparseableEntries(configuration.isSaveUnparseableEntries());

        client.configure(config);

        client.setCopyStreamListener(configuration.getCopyStreamListener());
        client.setControlKeepAliveTimeout(configuration.getControlKeepAliveReplyTimeout());
        client.setControlKeepAliveReplyTimeout(configuration.getControlKeepAliveReplyTimeout());
        client.setControlEncoding(configuration.getControlEncoding());
        client.setListHiddenFiles(configuration.isListHiddenFiles());

        client.setBufferSize(configuration.getBufferSize());
        client.setSendDataSocketBufferSize(configuration.getSendDataSocketBufferSize());
        client.setReceieveDataSocketBufferSize(configuration.getReceiveDataSocketBufferSize());
        client.setDefaultTimeout(configuration.getConnectTimeout());
        client.setDataTimeout(configuration.getDataTimeout());
        client.setRemoteVerificationEnabled(configuration.isRemoteVerificationEnabled());

        if (configuration.getProtocolCommandListener() != null) {
            // eg new PrintCommandListener(new PrintWriter(System.out), true)
            client.addProtocolCommandListener(configuration.getProtocolCommandListener());
        }

        try {
            int reply;
            if (configuration.getPort() > 0) {
                client.connect(configuration.getServer(), configuration.getPort());
            } else {
                client.connect(configuration.getServer());
            }
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                client.disconnect();
                throw new FtpAccessException("cannot connect the server using host: " + configuration.getServer() + ":"
                        + configuration.getPort());
            }

            if (!client.login(configuration.getUsername(), configuration.getPassword())) {
                client.logout();
                throw new FtpAccessException("cannot login using username: " + configuration.getUsername());
            }

            client.setFileType(configuration.getFileType());

            if (FTPClient.ACTIVE_LOCAL_DATA_CONNECTION_MODE == configuration.getDataConnectionMode()) {
                client.enterLocalActiveMode();
            } else {
                client.enterLocalPassiveMode();
            }

            client.setUseEPSVwithIPv4(configuration.isUseEPSVwithIPv4());

            innerClient = new FtpClientWapper(client);

        } catch (IOException e) {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException f) {
                    // do nothing
                }
            }
            throw new FtpAccessException("cannot connect the server using host: " + configuration.getServer() + ":"
                    + configuration.getPort(), e);
        }

        return innerClient;
    }
}
