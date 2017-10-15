package com.github.cherry.file.client.remote;

import com.github.cherry.framework.common.utils.Assert;
import com.github.cherry.framework.common.utils.StringUtils;

/**
 *〈一句话功能简述〉
 *〈功能详细描述〉
 *
 * @author Scott
 * @since 1.0
 */
public enum FileExistsMode {

    /**
     * Append data to any pre-existing files; close after each append.
     */
    APPEND,

    /**
     * Append data to any pre-existing files; do not flush/close after
     * appending.
     */
    APPEND_NO_FLUSH,

    /**
     * Raise an exception in case the file to be written already exists.
     */
    FAIL,

    /**
     * If the file already exists, do nothing.
     */
    IGNORE,

    /**
     * If the file already exists, replace it.
     */
    REPLACE;

    /**
     * For a given non-null and not-empty input string, this method returns the
     * corresponding {@link FileExistsMode}. If it cannot be determined, an
     * {@link IllegalStateException} is thrown.
     *
     * @param fileExistsModeAsString Must neither be null nor empty
     * @return the enum for the string value.
     */
    public static FileExistsMode getForString(String fileExistsModeAsString) {

        Assert.hasText(fileExistsModeAsString, "'fileExistsModeAsString' must neither be null nor empty.");

        final FileExistsMode[] fileExistsModeValues = FileExistsMode.values();

        for (FileExistsMode fileExistsMode : fileExistsModeValues) {
            if (fileExistsModeAsString.equalsIgnoreCase(fileExistsMode.name())) {
                return fileExistsMode;
            }
        }

        throw new IllegalArgumentException("Invalid fileExistsMode '" + fileExistsModeAsString
                + "'. The (case-insensitive) supported values are: "
                + StringUtils.arrayToCommaDelimitedString(fileExistsModeValues));

    }
}
