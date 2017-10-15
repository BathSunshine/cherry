package com.github.cherry.file.client.sftp;

import java.util.Properties;

import com.github.cherry.file.client.pool.AbstractPoolConfig;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.SocketFactory;
import com.jcraft.jsch.UserInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class SftpConfiguration extends AbstractPoolConfig {

    /*** AuthenticateConfig **/
    private volatile String host;

    private volatile int port = 22; // the default

    private volatile String user;

    private volatile String password;

    private volatile String knownHosts;

    // base64 encode
    private volatile String privateKey;

    private volatile String privateKeyPassphrase;

    private volatile Properties sessionConfig;

    /** Proxy config */
    private boolean userProxy = false;

    private ProxyType proxyType;

    private String proxyHost;

    private int proxyPort;

    private String proxyUser;

    private String proxyPassword;

    /** connection config */

    private volatile Integer timeout;

    private volatile String clientVersion;

    private volatile String hostKeyAlias;

    private volatile Integer serverAliveInterval;

    private volatile Integer serverAliveCountMax;

    private volatile Boolean enableDaemonThread;

    /***/
    private boolean isSharedSession;

    private volatile SocketFactory socketFactory;

    private volatile UserInfo userInfo;

    private volatile boolean allowUnknownKeys = false;

    /**
     * The url of the host you want connect to. This is a mandatory property.
     * 
     * @param host The host.
     * @see JSch#getSession(String, String, int)
     */
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * The port over which the SFTP connection shall be established. If not specified,
     * this value defaults to <code>22</code>. If specified, this properties must
     * be a positive number.
     * 
     * @param port The port.
     * @see JSch#getSession(String, String, int)
     */
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    /**
     * The remote user to use. This is a mandatory property.
     * 
     * @param user The user.
     * @see JSch#getSession(String, String, int)
     */
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    /**
     * The password to authenticate against the remote host. If a password is
     * not provided, then a {@link DefaultSftpSessionFactory#setPrivateKey(Resource) privateKey} is
     * mandatory.
     * Not allowed if {@link #setUserInfo(UserInfo) userInfo} is provided - the password is obtained
     * from that object.
     * 
     * @param password The password.
     * @see com.jcraft.jsch.Session#setPassword(String)
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Specifies the filename that will be used for a host key repository.
     * The file has the same format as OpenSSH's known_hosts file.
     * <p>
     * <b>Required if {@link #setAllowUnknownKeys(boolean) allowUnknownKeys} is false (default).</b>
     * 
     * @param knownHosts The known hosts.
     * @see JSch#setKnownHosts(String)
     */
    public String getKnownHosts() {
        return knownHosts;
    }

    public void setKnownHosts(String knownHosts) {
        this.knownHosts = knownHosts;
    }

    /**
     * represents the location of the
     * private key used for authenticating against the remote host. If the privateKey
     * is not provided, then the {@link SftpConfiguration#setPassword(String) password} property is mandatory (or
     * {@link #setUserInfo(UserInfo) userInfo} that returns a
     * password.
     * 
     * @param privateKey The private key.
     * @see JSch#addIdentity(String)
     * @see JSch#addIdentity(String, String)
     */
    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * The password for the private key. Optional.
     * Not allowed if {@link #setUserInfo(UserInfo) userInfo} is provided - the passphrase is obtained
     * from that object.
     * 
     * @param privateKeyPassphrase The private key passphrase.
     * @see JSch#addIdentity(String, String)
     */
    public String getPrivateKeyPassphrase() {
        return privateKeyPassphrase;
    }

    public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
        this.privateKeyPassphrase = privateKeyPassphrase;
    }

    /**
     * Using {@link Properties}, you can set additional configuration settings on
     * the underlying JSch {@link com.jcraft.jsch.Session}.
     * 
     * @param sessionConfig The session configuration properties.
     * @see com.jcraft.jsch.Session#setConfig(Properties)
     */
    public Properties getSessionConfig() {
        return sessionConfig;
    }

    public void setSessionConfig(Properties sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    /**
     * userProxy.
     *
     * @return the userProxy
     * @since 1.0
     */
    public boolean isUserProxy() {
        return userProxy;
    }

    public void setUserProxy(boolean userProxy) {
        this.userProxy = userProxy;
    }

    /**
     * proxyType.
     *
     * @return the proxyType
     * @since 1.0
     */
    public ProxyType getProxyType() {
        return proxyType;
    }

    public void setProxyType(ProxyType proxyType) {
        this.proxyType = proxyType;
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
     * proxyUser.
     *
     * @return the proxyUser
     * @since 1.0
     */
    public String getProxyUser() {
        return proxyUser;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
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
     * The timeout property is used as the socket timeout parameter, as well as
     * the default connection timeout. Defaults to <code>0</code>, which means,
     * that no timeout will occur.
     * 
     * @param timeout The timeout.
     * @see com.jcraft.jsch.Session#setTimeout(int)
     */
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * Allows you to set the client version property. It's default depends on the
     * underlying JSch version but it will look like <code>SSH-2.0-JSCH-0.1.45</code>
     * 
     * @param clientVersion The client version.
     * @see com.jcraft.jsch.Session#setClientVersion(String)
     */
    public String getClientVersion() {
        return clientVersion;
    }

    public void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
    }

    /**
     * Sets the host key alias, used when comparing the host key to the known
     * hosts list.
     * 
     * @param hostKeyAlias The host key alias.
     * @see com.jcraft.jsch.Session#setHostKeyAlias(String)
     */
    public String getHostKeyAlias() {
        return hostKeyAlias;
    }

    public void setHostKeyAlias(String hostKeyAlias) {
        this.hostKeyAlias = hostKeyAlias;
    }

    /**
     * Sets the timeout interval (milliseconds) before a server alive message is
     * sent, in case no message is received from the server.
     * 
     * @param serverAliveInterval The server alive interval.
     * @see com.jcraft.jsch.Session#setServerAliveInterval(int)
     */
    public Integer getServerAliveInterval() {
        return serverAliveInterval;
    }

    public void setServerAliveInterval(Integer serverAliveInterval) {
        this.serverAliveInterval = serverAliveInterval;
    }

    /**
     * Specifies the number of server-alive messages, which will be sent without
     * any reply from the server before disconnecting. If not set, this property
     * defaults to <code>1</code>.
     * 
     * @param serverAliveCountMax The server alive count max.
     * @see com.jcraft.jsch.Session#setServerAliveCountMax(int)
     */
    public Integer getServerAliveCountMax() {
        return serverAliveCountMax;
    }

    public void setServerAliveCountMax(Integer serverAliveCountMax) {
        this.serverAliveCountMax = serverAliveCountMax;
    }

    /**
     * If true, all threads will be daemon threads. If set to <code>false</code>,
     * normal non-daemon threads will be used. This property will be set on the
     * underlying {@link com.jcraft.jsch.Session} using {@link com.jcraft.jsch.Session#setDaemonThread(boolean)}. There,
     * this
     * property will default to <code>false</code>, if not explicitly set.
     * 
     * @param enableDaemonThread true to enable a daemon thread.
     * @see com.jcraft.jsch.Session#setDaemonThread(boolean)
     */
    public Boolean getEnableDaemonThread() {
        return enableDaemonThread;
    }

    public void setEnableDaemonThread(Boolean enableDaemonThread) {
        this.enableDaemonThread = enableDaemonThread;
    }

    /**
     * isSharedSession.
     *
     * @return the isSharedSession
     * @since 1.0
     */
    public boolean isSharedSession() {
        return isSharedSession;
    }

    public void setSharedSession(boolean isSharedSession) {
        this.isSharedSession = isSharedSession;
    }

    /**
     * socketFactory.
     *
     * @return the socketFactory
     * @since 1.0
     */
    public SocketFactory getSocketFactory() {
        return socketFactory;
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
    }

    /**
     * Provide a {@link UserInfo} which exposes control over dealing with new keys or key
     * changes. As Spring Integration will not normally allow user interaction, the
     * implementation must respond to Jsch calls in a suitable way.
     * <p>
     * Jsch calls {@link UserInfo#promptYesNo(String)} when connecting to an unknown host, or when a known host's key
     * has changed (see {@link #setKnownHosts(String)
     * knownHosts}). Generally, it should return false as returning true will accept all new keys or key changes.
     * <p>
     * If no {@link UserInfo} is provided, the behavior is defined by {@link #setAllowUnknownKeys(boolean)
     * allowUnknownKeys}.
     * <p>
     * If {@link #setPassword(String) setPassword} is invoked with a non-null password, it will override any password in
     * the supplied {@link UserInfo}.
     * <p>
     * <b>NOTE: When this is provided, the {@link #setPassword(String) password} and
     * {@link #setPrivateKeyPassphrase(String) passphrase} are not allowed because those values will be obtained from
     * the {@link UserInfo}.</b>
     * 
     * @param userInfo the UserInfo.
     * @see com.jcraft.jsch.Session#setUserInfo(com.jcraft.jsch.UserInfo)
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    /**
     * When no {@link UserInfo} has been provided, set to true to unconditionally allow
     * connecting to an unknown host or when a host's key has changed (see {@link #setKnownHosts(String) knownHosts}).
     * Default false.
     * Set to true if a knownHosts file is not provided.
     * 
     * @param allowUnknownKeys true to allow connecting to unknown hosts.
     */
    public boolean isAllowUnknownKeys() {
        return allowUnknownKeys;
    }

    public void setAllowUnknownKeys(boolean allowUnknownKeys) {
        this.allowUnknownKeys = allowUnknownKeys;
    }

}
