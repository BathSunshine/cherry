package com.github.cherry.file.client.ftp;

import java.util.TimeZone;

import org.apache.commons.net.ProtocolCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.io.CopyStreamListener;

import com.github.cherry.file.client.pool.AbstractPoolConfig;

/**
 * 全局配置
 *
 * @author Scott
 * @since 1.0
 * @see org.apache.commons.net.ftp.FTPClientConfig
 * @see org.apache.commons.net.ftp.FTPClient
 */
public class FtpConfiguration extends AbstractPoolConfig {

    private static final String DEFAULT_SERVER_SYSTEM_KEY = FTPClientConfig.SYST_UNIX;

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String DEFAULT_SERVER_LANGUAGE = "en";

    private static final int DEFAULT_BUFFER_SIZE = 1 * 1024 * 1024;

    private static final String DEFAULT_CONTROL_ENCODING = "UTF-8";

    private static final int DEFAULT_CONNECTION_TIMEOUT = 10 * 1000;

    private static final int DEFAULT_DATA_TIMEOUT = 30 * 1000;

    private static final int DEFAULT_KEEP_ALIVE_TIMEOUT = 60;

    private static final int DEFAULT_KEEP_ALIVE_REPLY_TIMEOUT = 1 * 1000;

    /*** AuthenticateConfig **/
    private String server;

    private int port;

    private String username;

    private String password;

    /************************* FTPClientConfig *******************************/
    private boolean saveUnparseableEntries = false;

    private String serverSystemKey = DEFAULT_SERVER_SYSTEM_KEY;

    private String defaultDateFormatStr = DEFAULT_DATE_FORMAT;

    private String recentDateFormatStr = DEFAULT_DATE_FORMAT;

    private String serverLanguageCode = DEFAULT_SERVER_LANGUAGE;

    private String serverTimeZoneId = TimeZone.getDefault().getID();

    /************************** FTPClient ****************************/

    private int bufferSize = DEFAULT_BUFFER_SIZE;

    private int sendDataSocketBufferSize = DEFAULT_BUFFER_SIZE;

    private int receiveDataSocketBufferSize = DEFAULT_BUFFER_SIZE;

    private String controlEncoding = DEFAULT_CONTROL_ENCODING;

    private int connectTimeout = DEFAULT_CONNECTION_TIMEOUT;

    private int dataTimeout = DEFAULT_DATA_TIMEOUT;

    private int fileType = FTPClient.BINARY_FILE_TYPE;

    // only support PASSIVE_LOCAL_DATA_CONNECTION_MODE = 2; || ACTIVE_LOCAL_DATA_CONNECTION_MODE
    private int dataConnectionMode = FTPClient.ACTIVE_LOCAL_DATA_CONNECTION_MODE;

    private boolean remoteVerificationEnabled;

    private boolean listHiddenFiles;

    private boolean useEPSVwithIPv4;

    private CopyStreamListener copyStreamListener;

    private ProtocolCommandListener protocolCommandListener;

    private int controlIdle = DEFAULT_KEEP_ALIVE_TIMEOUT;

    private int controlKeepAliveReplyTimeout = DEFAULT_KEEP_ALIVE_REPLY_TIMEOUT;

    /************************** Http Proxy ******************************/

    private boolean useHttpProxy;

    private String proxyHost;

    private int proxyPort;

    private String proxyUsername;

    private String proxyPassword;

    /**
     * serverSystemKey.
     *
     * @return the serverSystemKey
     * @since 1.0
     */
    public String getServerSystemKey() {
        return serverSystemKey;
    }

    public void setServerSystemKey(String serverSystemKey) {
        this.serverSystemKey = serverSystemKey;
    }

    /**
     * defaultDateFormatStr.
     *
     * @return the defaultDateFormatStr
     * @since 1.0
     */
    public String getDefaultDateFormatStr() {
        return defaultDateFormatStr;
    }

    public void setDefaultDateFormatStr(String defaultDateFormatStr) {
        this.defaultDateFormatStr = defaultDateFormatStr;
    }

    /**
     * recentDateFormatStr.
     *
     * @return the recentDateFormatStr
     * @since 1.0
     */
    public String getRecentDateFormatStr() {
        return recentDateFormatStr;
    }

    public void setRecentDateFormatStr(String recentDateFormatStr) {
        this.recentDateFormatStr = recentDateFormatStr;
    }

    /**
     * serverLanguageCode.
     *
     * @return the serverLanguageCode
     * @since 1.0
     */
    public String getServerLanguageCode() {
        return serverLanguageCode;
    }

    public void setServerLanguageCode(String serverLanguageCode) {
        this.serverLanguageCode = serverLanguageCode;
    }

    /**
     * serverTimeZoneId.
     *
     * @return the serverTimeZoneId
     * @since 1.0
     */
    public String getServerTimeZoneId() {
        return serverTimeZoneId;
    }

    public void setServerTimeZoneId(String serverTimeZoneId) {
        this.serverTimeZoneId = serverTimeZoneId;
    }

    /**
     * bufferSize.
     *
     * @return the bufferSize
     * @since 1.0
     */
    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    /**
     * sendDataSocketBufferSize.
     *
     * @return the sendDataSocketBufferSize
     * @since 1.0
     */
    public int getSendDataSocketBufferSize() {
        return sendDataSocketBufferSize;
    }

    public void setSendDataSocketBufferSize(int sendDataSocketBufferSize) {
        this.sendDataSocketBufferSize = sendDataSocketBufferSize;
    }

    /**
     * receiveDataSocketBufferSize.
     *
     * @return the receiveDataSocketBufferSize
     * @since 1.0
     */
    public int getReceiveDataSocketBufferSize() {
        return receiveDataSocketBufferSize;
    }

    public void setReceiveDataSocketBufferSize(int receiveDataSocketBufferSize) {
        this.receiveDataSocketBufferSize = receiveDataSocketBufferSize;
    }

    /**
     * controlEncoding.
     *
     * @return the controlEncoding
     * @since 1.0
     */
    public String getControlEncoding() {
        return controlEncoding;
    }

    public void setControlEncoding(String controlEncoding) {
        this.controlEncoding = controlEncoding;
    }

    /**
     * connectTimeout.
     *
     * @return the connectTimeout
     * @since 1.0
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    /**
     * dataTimeout.
     *
     * @return the dataTimeout
     * @since 1.0
     */
    public int getDataTimeout() {
        return dataTimeout;
    }

    public void setDataTimeout(int dataTimeout) {
        this.dataTimeout = dataTimeout;
    }

    /**
     * fileType.
     *
     * @return the fileType
     * @since 1.0
     */
    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    /**
     * dataConnectionMode.
     *
     * @return the dataConnectionMode
     * @since 1.0
     */
    public int getDataConnectionMode() {
        return dataConnectionMode;
    }

    public void setDataConnectionMode(int dataConnectionMode) {
        this.dataConnectionMode = dataConnectionMode;
    }

    /**
     * remoteVerificationEnabled.
     *
     * @return the remoteVerificationEnabled
     * @since 1.0
     */
    public boolean isRemoteVerificationEnabled() {
        return remoteVerificationEnabled;
    }

    public void setRemoteVerificationEnabled(boolean remoteVerificationEnabled) {
        this.remoteVerificationEnabled = remoteVerificationEnabled;
    }

    /**
     * listHiddenFiles.
     *
     * @return the listHiddenFiles
     * @since 1.0
     */
    public boolean isListHiddenFiles() {
        return listHiddenFiles;
    }

    public void setListHiddenFiles(boolean listHiddenFiles) {
        this.listHiddenFiles = listHiddenFiles;
    }

    /**
     * useEPSVwithIPv4.
     *
     * @return the useEPSVwithIPv4
     * @since 1.0
     */
    public boolean isUseEPSVwithIPv4() {
        return useEPSVwithIPv4;
    }

    public void setUseEPSVwithIPv4(boolean useEPSVwithIPv4) {
        this.useEPSVwithIPv4 = useEPSVwithIPv4;
    }

    /**
     * copyStreamListener.
     *
     * @return the copyStreamListener
     * @since 1.0
     */
    public CopyStreamListener getCopyStreamListener() {
        return copyStreamListener;
    }

    public void setCopyStreamListener(CopyStreamListener copyStreamListener) {
        this.copyStreamListener = copyStreamListener;
    }

    /**
     * controlIdle.
     *
     * @return the controlIdle
     * @since 1.0
     */
    public int getControlIdle() {
        return controlIdle;
    }

    public void setControlIdle(int controlIdle) {
        this.controlIdle = controlIdle;
    }

    /**
     * controlKeepAliveReplyTimeout.
     *
     * @return the controlKeepAliveReplyTimeout
     * @since 1.0
     */
    public int getControlKeepAliveReplyTimeout() {
        return controlKeepAliveReplyTimeout;
    }

    public void setControlKeepAliveReplyTimeout(int controlKeepAliveReplyTimeout) {
        this.controlKeepAliveReplyTimeout = controlKeepAliveReplyTimeout;
    }

    /**
     * useHttpProxy.
     *
     * @return the useHttpProxy
     * @since 1.0
     */
    public boolean isUseHttpProxy() {
        return useHttpProxy;
    }

    public void setUseHttpProxy(boolean useHttpProxy) {
        this.useHttpProxy = useHttpProxy;
    }

    /**
     * proxyHost.
     *
     * @return the proxyHost
     * @since 1.0
     */
    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    /**
     * proxyPort.
     *
     * @return the proxyPort
     * @since 1.0
     */
    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    /**
     * proxyUsername.
     *
     * @return the proxyUsername
     * @since 1.0
     */
    public String getProxyUsername() {
        return proxyUsername;
    }

    public void setProxyUsername(String proxyUsername) {
        this.proxyUsername = proxyUsername;
    }

    /**
     * proxyPassword.
     *
     * @return the proxyPassword
     * @since 1.0
     */
    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    /**
     * protocolCommandListener.
     *
     * @return the protocolCommandListener
     * @since 1.0
     */
    public ProtocolCommandListener getProtocolCommandListener() {
        return protocolCommandListener;
    }

    public void setProtocolCommandListener(ProtocolCommandListener protocolCommandListener) {
        this.protocolCommandListener = protocolCommandListener;
    }

    /**
     * saveUnparseableEntries.
     *
     * @return the saveUnparseableEntries
     * @since 1.0
     */
    public boolean isSaveUnparseableEntries() {
        return saveUnparseableEntries;
    }

    public void setSaveUnparseableEntries(boolean saveUnparseableEntries) {
        this.saveUnparseableEntries = saveUnparseableEntries;
    }

    /**
     * server.
     *
     * @return the server
     * @since 1.0
     */
    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    /**
     * port.
     *
     * @return the port
     * @since 1.0
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * username.
     *
     * @return the username
     * @since 1.0
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password.
     *
     * @return the password
     * @since 1.0
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
