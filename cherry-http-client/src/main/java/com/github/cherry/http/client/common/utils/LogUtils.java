package com.github.cherry.http.client.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.cherry.http.client.internal.ClientConstants;

public class LogUtils {

    private static final Log log = LogFactory.getLog(ClientConstants.LOGGER_PACKAGE_NAME);

    public static Log getLog() {
        return log;
    }

    public static <ExType> void logException(String messagePrefix, ExType ex) {
        assert (ex instanceof Exception);
        String detailMessage = messagePrefix + ((Exception) ex).getMessage();
        log.warn(detailMessage);
    }
}
