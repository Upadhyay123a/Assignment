import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import com.speechify.cache.CacheLimits;
import com.speechify.cache.LRUCache;
import static com.speechify.cache.LRUCacheProvider.createLRUCache;

public class LruCacheTest {

    @Test
    public void getShouldReturnValueForExistingKey() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(10));
        lruCache.put("foo", "bar");
        assertEquals("bar", lruCache.get("foo"));
    }

    @Test
    public void getShouldReturnNullForNonExistentKey() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(10));
        lruCache.put("foo", "bar");
        assertNull(lruCache.get("bar"));
        assertNull(lruCache.get(""));
    }

    @Test
    public void getShouldReturnValueForManyExistingKeys() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(10));
        lruCache.put("foo", "foo");
        lruCache.put("baz", "baz");
        assertEquals("foo", lruCache.get("foo"));
        assertEquals("baz", lruCache.get("baz"));
    }

    @Test
    public void getShouldReturnNullForKeyNotFittingMaxItemsCount() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(1));
        lruCache.put("foo", "bar");
        lruCache.put("baz", "bar");
        assertNull(lruCache.get("foo"));
        assertEquals("bar", lruCache.get("baz"));
    }

    @Test
    public void getShouldReturnValueForRecreatedKeyAfterItWasPreviouslyRemoved() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(1));
        lruCache.put("foo", "bar");
        lruCache.put("baz", "bar");
        lruCache.put("foo", "bar");
        assertEquals("bar", lruCache.get("foo"));
        assertNull(lruCache.get("baz"));
    }

    @Test
    public void setShouldRemoveOldestKeyOnReachingMaxItemsCountIfNoGetOrHasBeenUsed() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(1));
        lruCache.put("foo", "bar");
        lruCache.put("baz", "bar");
        assertNull(lruCache.get("foo"));
        assertEquals("bar", lruCache.get("baz"));
    }

    @Test
    public void setShouldReplaceExistingValueAndValuesForAllKeysAreKeptWhenCacheLimitIsReached() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(3));
        lruCache.put("bax", "par");
        lruCache.put("foo", "bar1");
        lruCache.put("foo", "bar2");
        lruCache.put("foo", "bar3");
        lruCache.put("baz", "bar");

        assertEquals("bar3", lruCache.get("foo"));
        assertEquals("par", lruCache.get("bax"));
        assertEquals("bar", lruCache.get("baz"));
    }

    @Test
    public void setShouldRemoveLeastRecentlyUsedKeyOnReachingMaxItemsCount() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(2));
        lruCache.put("foo", "bar");
        lruCache.put("bar", "bar");
        lruCache.get("foo");
        lruCache.put("baz", "bar");

        assertEquals("bar", lruCache.get("foo"));
        assertNull(lruCache.get("bar"));
        assertEquals("bar", lruCache.get("baz"));
    }

    @Test
    public void itemIsConsideredAccessedWhenGetIsCalled() {
        LRUCache<String, String> lruCache = createLRUCache(new CacheLimits(2));
        lruCache.put("1key", "1value");
        lruCache.put("2key", "2value");

        lruCache.get("1key");
        lruCache.put("3key", "3value");

        assertEquals("1value", lruCache.get("1key"));
    }
}
