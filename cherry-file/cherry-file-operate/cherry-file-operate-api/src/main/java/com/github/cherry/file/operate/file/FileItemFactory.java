package com.github.cherry.file.operate.file;

/**
 * <p>
 * A factory interface for creating {@link FileItem} instances. Factories can provide their own custom configuration,
 * over and above that provided by the default file upload implementation.
 * </p>
 */
public interface FileItemFactory {

    /**
     * Create a new {@link FileItem} instance from the supplied parameters and
     * any local factory configuration.
     *
     * @param contentType The content type of the form field.
     * @param fileName The name of the uploaded file, if any, as supplied
     * by the browser or other client.
     *
     * @return The newly created file item.
     */
    FileItem createItem(String contentType, String fileName);

}
