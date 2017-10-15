package com.github.cherry.file.client.remote;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;

import com.github.cherry.file.client.FileClient;

/**
 * Utility methods for supporting remote file operations.
 *
 * @author Scott
 * @since 1.0
 */
public abstract class RemoteFileUtils {

    /**
     * Recursively create remote directories.
     * 
     * @param <F> The session type.
     * @param path The directory path.
     * @param session The session.
     * @param remoteFileSeparator The remote file separator.
     * @param logger The logger.
     */
    public static <F> void makeDirectories(String path, FileClient<F> session, String remoteFileSeparator, Log logger) {
        if (!session.exists(path)) {

            int nextSeparatorIndex = path.lastIndexOf(remoteFileSeparator);

            if (nextSeparatorIndex > -1) {
                List<String> pathsToCreate = new LinkedList<String>();
                while (nextSeparatorIndex > -1) {
                    String pathSegment = path.substring(0, nextSeparatorIndex);
                    if (pathSegment.length() == 0 || session.exists(pathSegment)) {
                        // no more paths to create
                        break;
                    } else {
                        pathsToCreate.add(0, pathSegment);
                        nextSeparatorIndex = pathSegment.lastIndexOf(remoteFileSeparator);
                    }
                }

                for (String pathToCreate : pathsToCreate) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Creating '" + pathToCreate + "'");
                    }
                    session.mkdir(pathToCreate);
                }
            } else {
                session.mkdir(path);
            }
        }
    }

}
