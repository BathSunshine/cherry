package com.github.cherry.file.client.metadata;

/**
 * Strategy interface for storing metadata from certain adapters
 * to avoid duplicate delivery of messages, for example.
 *
 */

public interface MetadataStore {

    /**
     * Writes a key value pair to this MetadataStore.
     *
     * @param key The key.
     * @param value The value.
     */
    void put(String key, String value);

    /**
     * Reads a value for the given key from this MetadataStore.
     *
     * @param key The key.
     * @return The value.
     */

    String get(String key);

    /**
     * Remove a value for the given key from this MetadataStore.
     * 
     * @param key The key.
     * @return The previous value associated with <tt>key</tt>, or <tt>null</tt> if there was no mapping for
     * <tt>key</tt>.
     */

    String remove(String key);

}
