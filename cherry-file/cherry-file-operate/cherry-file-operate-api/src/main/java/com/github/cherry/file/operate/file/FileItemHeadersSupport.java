package com.github.cherry.file.operate.file;

/**
 * Interface that will indicate that {@link FileItem} or {@link FileItemStream}
 * implementations will accept the headers read for the item.
 *
 * @author Scott
 * @since 1.0
 */
public interface FileItemHeadersSupport {
    /**
     * Returns the collection of headers defined locally within this item.
     *
     * @return the {@link FileItemHeaders} present for this item.
     */
    FileItemHeaders getHeaders();

    /**
     * Sets the headers read from within an item. Implementations of {@link FileItem} or {@link FileItemStream} should
     * implement this
     * interface to be able to get the raw headers found within the item
     * header block.
     *
     * @param headers the instance that holds onto the headers
     * for this instance.
     */
    void setHeaders(FileItemHeaders headers);
}
