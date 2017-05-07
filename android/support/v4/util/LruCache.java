// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

import java.util.Map;
import java.util.LinkedHashMap;

public class LruCache<K, V>
{
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;
    
    public LruCache(final int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap<K, V>(0, 0.75f, true);
    }
    
    private int safeSizeOf(final K k, final V v) {
        final int size = this.sizeOf(k, v);
        if (size < 0) {
            throw new IllegalStateException("Negative size: " + k + "=" + v);
        }
        return size;
    }
    
    protected V create(final K k) {
        return null;
    }
    
    protected void entryRemoved(final boolean b, final K k, final V v, final V v2) {
    }
    
    public final V get(final K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V v;
        synchronized (this) {
            v = this.map.get(k);
            if (v != null) {
                ++this.hitCount;
                return v;
            }
            ++this.missCount;
            // monitorexit(this)
            v = this.create(k);
            if (v == null) {
                return null;
            }
        }
        synchronized (this) {
            ++this.createCount;
            final K i;
            final V put = this.map.put(i, v);
            if (put != null) {
                this.map.put(i, put);
            }
            else {
                this.size += this.safeSizeOf(i, v);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, i, v, put);
                return put;
            }
        }
        this.trimToSize(this.maxSize);
        return v;
    }
    
    public final V put(final K k, final V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            ++this.putCount;
            this.size += this.safeSizeOf(k, v);
            final V put = this.map.put(k, v);
            if (put != null) {
                this.size -= this.safeSizeOf(k, put);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, k, put, v);
            }
            this.trimToSize(this.maxSize);
            return put;
        }
    }
    
    protected int sizeOf(final K k, final V v) {
        return 1;
    }
    
    @Override
    public final String toString() {
        int n = 0;
        synchronized (this) {
            final int n2 = this.hitCount + this.missCount;
            if (n2 != 0) {
                n = this.hitCount * 100 / n2;
            }
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.maxSize, this.hitCount, this.missCount, n);
        }
    }
    
    public void trimToSize(final int n) {
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.map.isEmpty() && this.size != 0)) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
            }
            if (this.size <= n || this.map.isEmpty()) {
                break;
            }
            final Map.Entry<K, V> entry = this.map.entrySet().iterator().next();
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.map.remove(key);
            this.size -= this.safeSizeOf(key, value);
            ++this.evictionCount;
            // monitorexit(this)
            this.entryRemoved(true, key, value, null);
        }
    }
    // monitorexit(this)
}
