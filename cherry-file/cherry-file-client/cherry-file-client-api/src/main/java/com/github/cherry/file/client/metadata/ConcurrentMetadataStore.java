package com.github.cherry.file.client.metadata;

/**
 * Supports atomic updates to values in the store.
 *
 * 
 *
 */
public interface ConcurrentMetadataStore extends MetadataStore {

    /**
     * Atomically insert the key into the store.
     *
     * @param key The key.
     * @param value The value.
     * @return null if successful, the old value otherwise.
     */
    String putIfAbsent(String key, String value);

    /**
     * Atomically replace the value for the key in the store if the old
     * value matches the oldValue argument.
     *
     * @param key The key.
     * @param oldValue The old value.
     * @param newValue The new value.
     * @return true if successful.
     */
    boolean replace(String key, String oldValue, String newValue);

}
