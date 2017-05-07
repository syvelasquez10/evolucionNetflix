// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.LinkedHashMap;

public class ja<K, V>
{
    private final LinkedHashMap<K, V> Mb;
    private int Mc;
    private int Md;
    private int Me;
    private int Mf;
    private int Mg;
    private int Mh;
    private int size;
    
    public ja(final int mc) {
        if (mc <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.Mc = mc;
        this.Mb = new LinkedHashMap<K, V>(0, 0.75f, true);
    }
    
    private int c(final K k, final V v) {
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
    
    public final void evictAll() {
        this.trimToSize(-1);
    }
    
    public final V get(final K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        V v;
        synchronized (this) {
            v = this.Mb.get(k);
            if (v != null) {
                ++this.Mg;
                return v;
            }
            ++this.Mh;
            // monitorexit(this)
            v = this.create(k);
            if (v == null) {
                return null;
            }
        }
        synchronized (this) {
            ++this.Me;
            final K i;
            final V put = this.Mb.put(i, v);
            if (put != null) {
                this.Mb.put(i, put);
            }
            else {
                this.size += this.c(i, v);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, i, v, put);
                return put;
            }
        }
        this.trimToSize(this.Mc);
        return v;
    }
    
    public final V put(final K k, final V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            ++this.Md;
            this.size += this.c(k, v);
            final V put = this.Mb.put(k, v);
            if (put != null) {
                this.size -= this.c(k, put);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, k, put, v);
            }
            this.trimToSize(this.Mc);
            return put;
        }
    }
    
    public final int size() {
        synchronized (this) {
            return this.size;
        }
    }
    
    protected int sizeOf(final K k, final V v) {
        return 1;
    }
    
    @Override
    public final String toString() {
        int n = 0;
        synchronized (this) {
            final int n2 = this.Mg + this.Mh;
            if (n2 != 0) {
                n = this.Mg * 100 / n2;
            }
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.Mc, this.Mg, this.Mh, n);
        }
    }
    
    public void trimToSize(final int n) {
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.Mb.isEmpty() && this.size != 0)) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
            }
            if (this.size <= n || this.Mb.isEmpty()) {
                break;
            }
            final Map.Entry<K, V> entry = this.Mb.entrySet().iterator().next();
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.Mb.remove(key);
            this.size -= this.c(key, value);
            ++this.Mf;
            // monitorexit(this)
            this.entryRemoved(true, key, value, null);
        }
    }
    // monitorexit(this)
}
