package com.github.cherry.file.client.sftp;

import java.util.concurrent.atomic.AtomicInteger;

import com.jcraft.jsch.Session;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public class JSchSessionWrapper {
    private final Session session;

    private final AtomicInteger channels = new AtomicInteger();

    JSchSessionWrapper(Session session) {
        this.session = session;
    }

    public void addChannel() {
        this.channels.incrementAndGet();
    }

    public void close() {
        if (this.channels.decrementAndGet() <= 0) {
            this.session.disconnect();
        }
    }

    public final Session getSession() {
        return this.session;
    }

    public boolean isConnected() {
        return this.session.isConnected();
    }
}
