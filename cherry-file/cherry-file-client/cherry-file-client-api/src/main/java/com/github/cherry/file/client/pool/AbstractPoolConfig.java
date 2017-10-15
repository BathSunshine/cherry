package com.github.cherry.file.client.pool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public abstract class AbstractPoolConfig extends GenericObjectPoolConfig {
    public AbstractPoolConfig() {
        setTestWhileIdle(true);
        setMinEvictableIdleTimeMillis(60000);
        setTimeBetweenEvictionRunsMillis(30000);
        setNumTestsPerEvictionRun(-1);
    }
}
