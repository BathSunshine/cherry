package com.github.cherry.http.client.internal;

import java.text.MessageFormat;

import com.github.cherry.http.client.common.utils.ResourceManager;

/**
 * 
 * common.properties 存储
 *
 * @author Scott
 * @since 1.0
 */
public final class CommonResourceHolder {
    private static volatile CommonResourceHolder resourceHolder;

    private ResourceManager resouceManager;

    private CommonResourceHolder() {
        this.resouceManager = ResourceManager.getInstance(ClientConstants.RESOURCE_NAME_COMMON);
    }

    public static CommonResourceHolder getInstance() {
        if (resourceHolder == null) {
            synchronized (CommonResourceHolder.class) {
                if (resourceHolder == null) {
                    resourceHolder = new CommonResourceHolder();
                }
            }
        }
        return resourceHolder;
    }

    public String getString(String key) {
        return resouceManager.getString(key);
    }

    public String getFormattedString(String key, Object... args) {
        return MessageFormat.format(getString(key), args);
    }
}
