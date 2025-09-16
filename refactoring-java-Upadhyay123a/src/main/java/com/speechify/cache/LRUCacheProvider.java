package com.speechify.cache;

/**
 * Factory class to create an LRU cache instance.
 */
public class LRUCacheProvider {

    /**
     * Creates an LRUCache with a maximum size defined in CacheLimits.
     *
     * @param options cache configuration
     * @param <K>     type of the key
     * @param <V>     type of the value
     * @return an instance of LRUCache<K,V>
     */
    public static <K, V> LRUCache<K, V> createLRUCache(CacheLimits options) {
        return new LRUCache<>(options.getMaxItemsCount());
    }
}
