package com.github.cherry.file.client.sftp;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.ClassUtils;
import com.github.cherry.framework.common.utils.StreamUtils;
import com.github.cherry.framework.common.utils.StringUtils;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class JschSessionFactory {
    private static final Log logger = LogFactory.getLog(JschSessionFactory.class);
    
    private SftpConfiguration configuration;

    private final ReadWriteLock sharedSessionLock = new ReentrantReadWriteLock();

    private volatile JSchSessionWrapper sharedJschSession;

    private final UserInfo userInfoWrapper = new UserInfoWrapper();

    static {
        JSch.setLogger(new JschLogger());
    }
    
    public JschSessionFactory(SftpConfiguration configuration) {
        this.configuration = configuration;
    }

    public JSchSessionWrapper getSession() {
        JSchSessionWrapper jschSession;
        if (this.configuration.isSharedSession()) {
            this.sharedSessionLock.readLock().lock();
            try {
                if (this.sharedJschSession == null || !this.sharedJschSession.isConnected()) {
                    this.sharedSessionLock.readLock().unlock();
                    this.sharedSessionLock.writeLock().lock();
                    try {
                        if (this.sharedJschSession == null || !this.sharedJschSession.isConnected()) {
                            this.sharedJschSession = new JSchSessionWrapper(initJschSession());
                            try {
                                this.sharedJschSession.getSession().connect();
                            } catch (JSchException e) {
                                throw new IllegalStateException("failed to connect", e);
                            }
                        }
                    } finally {
                        this.sharedSessionLock.readLock().lock();
                        this.sharedSessionLock.writeLock().unlock();
                    }
                }
            } finally {
                this.sharedSessionLock.readLock().unlock();
            }
            jschSession = this.sharedJschSession;
        } else {
            jschSession = new JSchSessionWrapper(initJschSession());
        }

        return jschSession;

    }

    /**
     * 〈一句话功能简述〉
     * 〈功能详细描述〉
     * 
     * @return
     */
    private Session initJschSession() {
        try {
            JSch jsch = new JSch();
            int port = this.configuration.getPort();
            if (port <= 0) {
                port = 22;
            }

            if (StringUtils.hasText(this.configuration.getKnownHosts())) {
                jsch.setKnownHosts(this.configuration.getKnownHosts());
            }

            if (StringUtils.hasText(this.configuration.getPrivateKey())) {
                byte[] keyByteArray = StreamUtils.copyToByteArray(getInputStream(this.configuration.getPrivateKey()));
                String passphrase = this.userInfoWrapper.getPassphrase();
                if (StringUtils.hasText(passphrase)) {
                    jsch.addIdentity(this.configuration.getUser(), keyByteArray, null, passphrase.getBytes());
                } else {
                    jsch.addIdentity(this.configuration.getUser(), keyByteArray, null, null);
                }
            }

            Session session = jsch.getSession(this.configuration.getUser(), this.configuration.getHost(),
                    this.configuration.getPort());

            if (this.configuration.getSessionConfig() != null) {
                session.setConfig(this.configuration.getSessionConfig());
            }

            String password = this.userInfoWrapper.getPassword();

            if (StringUtils.hasText(password)) {
                session.setPassword(password);
            }
            session.setUserInfo(this.userInfoWrapper);

            if (this.configuration.isUserProxy()) {
                session.setProxy(new JschProxyFactoryBean(this.configuration.getProxyType(), this.configuration
                        .getProxyHost(), this.configuration.getProxyPort(), this.configuration.getProxyUser(),
                        this.configuration.getProxyPassword()).createInstance());
            }

            if (this.configuration.getSocketFactory() != null) {
                session.setSocketFactory(this.configuration.getSocketFactory());
            }

            if (this.configuration.getTimeout() != null) {
                session.setTimeout(this.configuration.getTimeout());
            }

            if (StringUtils.hasText(this.configuration.getClientVersion())) {
                session.setClientVersion(this.configuration.getClientVersion());
            }
            if (StringUtils.hasText(this.configuration.getHostKeyAlias())) {
                session.setHostKeyAlias(this.configuration.getHostKeyAlias());
            }
            if (this.configuration.getServerAliveInterval() != null) {
                session.setServerAliveInterval(this.configuration.getServerAliveInterval());
            }
            if (this.configuration.getServerAliveCountMax() != null) {
                session.setServerAliveCountMax(this.configuration.getServerAliveCountMax());
            }
            if (this.configuration.getEnableDaemonThread() != null) {
                session.setDaemonThread(this.configuration.getEnableDaemonThread());
            }

            return session;
        } catch (Exception e) {
            throw new SftpAccessException("create jsch session failed", e);
        }
    }

    private InputStream getInputStream(String path) throws FileNotFoundException {
        String pathToUse = StringUtils.cleanPath(path);
        if (pathToUse.startsWith("/")) {
            pathToUse = pathToUse.substring(1);
        }

        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        InputStream is;
        if (classLoader != null) {
            is = classLoader.getResourceAsStream(pathToUse);
        } else {
            is = ClassLoader.getSystemResourceAsStream(pathToUse);
        }
        if (is == null) {
            throw new FileNotFoundException(" cannot be opened because it does not exist");
        }

        return is;
    }

    /**
     * Wrapper class will delegate calls to a configured {@link UserInfo}, providing
     * sensible defaults if null. As the password is configured in this Factory, the
     * wrapper will return the factory's configured password and only delegate to the
     * UserInfo if null.
     */
    private class UserInfoWrapper implements UserInfo, UIKeyboardInteractive {

        /**
         * Convenience to check whether enclosing factory's UserInfo is configured.
         * 
         * @return true if there's a delegate.
         */
        private boolean hasDelegate() {
            return getDelegate() != null;
        }

        /**
         * Convenience to retrieve enclosing factory's UserInfo.
         * 
         * @return the {@link #userInfo} or null if not present.
         */
        private UserInfo getDelegate() {
            return JschSessionFactory.this.configuration.getUserInfo();
        }

        @Override
        public String getPassphrase() {
            if (hasDelegate()) {
                Assert.state(!StringUtils.hasText(JschSessionFactory.this.configuration.getPrivateKeyPassphrase()),
                        "When a 'UserInfo' is provided, 'privateKeyPassphrase' is not allowed");
                return getDelegate().getPassphrase();
            } else {
                return JschSessionFactory.this.configuration.getPrivateKeyPassphrase();
            }
        }

        @Override
        public String getPassword() {
            if (hasDelegate()) {
                Assert.state(!StringUtils.hasText(JschSessionFactory.this.configuration.getPassword()),
                        "When a 'UserInfo' is provided, 'password' is not allowed");
                return getDelegate().getPassword();
            } else {
                return JschSessionFactory.this.configuration.getPassword();
            }
        }

        @Override
        public boolean promptPassword(String message) {
            if (hasDelegate()) {
                return getDelegate().promptPassword(message);
            } else {

                return true;
            }
        }

        @Override
        public boolean promptPassphrase(String message) {
            if (hasDelegate()) {
                return getDelegate().promptPassphrase(message);
            } else {
                return true;
            }
        }

        @Override
        public boolean promptYesNo(String message) {
            if (hasDelegate()) {
                return getDelegate().promptYesNo(message);
            } else {
                return JschSessionFactory.this.configuration.isAllowUnknownKeys();
            }
        }

        @Override
        public void showMessage(String message) {
            if (hasDelegate()) {
                getDelegate().showMessage(message);
            } else {
                logger.debug(message);
            }
        }

        @Override
        public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt,
                boolean[] echo) {
            if (hasDelegate() && getDelegate() instanceof UIKeyboardInteractive) {
                return ((UIKeyboardInteractive) getDelegate()).promptKeyboardInteractive(destination, name,
                        instruction, prompt, echo);
            } else {

                return null;
            }
        }
    }
}
