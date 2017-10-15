package com.github.cherry.file.client.sftp;

import com.jcraft.jsch.Proxy;
import com.jcraft.jsch.ProxyHTTP;
import com.jcraft.jsch.ProxySOCKS4;
import com.jcraft.jsch.ProxySOCKS5;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class JschProxyFactoryBean {

    private final ProxyType type;

    private final String host;

    private final int port;

    private final String user;

    private final String password;

    public JschProxyFactoryBean(ProxyType type, String host, int port, String user, String password) {
        this.type = type;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    public Class<?> getObjectType() {
        switch (this.type) {
            case SOCKS5:
                return ProxySOCKS5.class;
            case SOCKS4:
                return ProxySOCKS4.class;
            case HTTP:
                return ProxyHTTP.class;
            default:
                throw new IllegalArgumentException("Invalid type:" + this.type);
        }
    }

    Proxy createInstance() throws Exception {
        switch (this.type) {
            case SOCKS5:
                ProxySOCKS5 socks5proxy = new ProxySOCKS5(this.host, this.port);
                socks5proxy.setUserPasswd(this.user, this.password);
                return socks5proxy;
            case SOCKS4:
                ProxySOCKS4 socks4proxy = new ProxySOCKS4(this.host, this.port);
                socks4proxy.setUserPasswd(this.user, this.password);
                return socks4proxy;
            case HTTP:
                ProxyHTTP httpProxy = new ProxyHTTP(this.host, this.port);
                httpProxy.setUserPasswd(this.user, this.password);
                return httpProxy;
            default:
                throw new IllegalArgumentException("Invalid type:" + this.type);
        }
    }
}
