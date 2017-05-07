// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.LinkedHashMap;

public class fu<K, V>
{
    private final LinkedHashMap<K, V> DL;
    private int DM;
    private int DN;
    private int DO;
    private int DP;
    private int DQ;
    private int DR;
    private int size;
    
    public fu(final int dm) {
        if (dm <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.DM = dm;
        this.DL = new LinkedHashMap<K, V>(0, 0.75f, true);
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
            v = this.DL.get(k);
            if (v != null) {
                ++this.DQ;
                return v;
            }
            ++this.DR;
            // monitorexit(this)
            v = this.create(k);
            if (v == null) {
                return null;
            }
        }
        synchronized (this) {
            ++this.DO;
            final K i;
            final V put = this.DL.put(i, v);
            if (put != null) {
                this.DL.put(i, put);
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
        this.trimToSize(this.DM);
        return v;
    }
    
    public final V put(final K k, final V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            ++this.DN;
            this.size += this.c(k, v);
            final V put = this.DL.put(k, v);
            if (put != null) {
                this.size -= this.c(k, put);
            }
            // monitorexit(this)
            if (put != null) {
                this.entryRemoved(false, k, put, v);
            }
            this.trimToSize(this.DM);
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
            final int n2 = this.DQ + this.DR;
            if (n2 != 0) {
                n = this.DQ * 100 / n2;
            }
            return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", this.DM, this.DQ, this.DR, n);
        }
    }
    
    public void trimToSize(final int n) {
        while (true) {
            synchronized (this) {
                if (this.size < 0 || (this.DL.isEmpty() && this.size != 0)) {
                    throw new IllegalStateException(this.getClass().getName() + ".sizeOf() is reporting inconsistent results!");
                }
            }
            if (this.size <= n || this.DL.isEmpty()) {
                break;
            }
            final Map.Entry<K, V> entry = this.DL.entrySet().iterator().next();
            final K key = entry.getKey();
            final V value = entry.getValue();
            this.DL.remove(key);
            this.size -= this.c(key, value);
            ++this.DP;
            // monitorexit(this)
            this.entryRemoved(true, key, value, null);
        }
    }
    // monitorexit(this)
}
