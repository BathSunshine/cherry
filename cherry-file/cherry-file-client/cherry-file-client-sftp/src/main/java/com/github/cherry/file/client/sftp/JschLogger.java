package com.github.cherry.file.client.sftp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jcraft.jsch.Logger;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
class JschLogger implements Logger {
    private final static Log logger = LogFactory.getLog("com.jcraft.jsch");

    public boolean isEnabled(int level) {
        switch (level) {
            case Logger.INFO:
                return logger.isInfoEnabled();
            case Logger.WARN:
                return logger.isWarnEnabled();
            case Logger.DEBUG:
                return logger.isDebugEnabled();
            case Logger.ERROR:
                return logger.isErrorEnabled();
            case Logger.FATAL:
                return logger.isFatalEnabled();
            default:
                return false;
        }
    }

    public void log(int level, String message) {

        switch (level) {
            case Logger.INFO:
                logger.info(message);
                break;
            case Logger.WARN:
                logger.warn(message);
                break;
            case Logger.DEBUG:
                logger.debug(message);
                break;
            case Logger.ERROR:
                logger.error(message);
                break;
            case Logger.FATAL:
                logger.fatal(message);
                break;
            default:
                break;
        }
    }
}
