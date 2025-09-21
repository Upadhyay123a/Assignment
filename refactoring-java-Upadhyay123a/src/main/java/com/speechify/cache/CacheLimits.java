package com.speechify.cache;
im

public class CacheLimits {
    public static final int USER_CACHE_SIZE = 100;
    public static final int CLIENT_CACHE_SIZE = 50;

    private final int maxItemsCount;

    public CacheLimits(int maxItemsCount) {
        this.maxItemsCount = maxItemsCount;
    }

    public int getMaxItemsCount() {
        return maxItemsCount;
    }
}









