// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.util.LruCache;

class bb<K, V> implements k<K, V>
{
    private LruCache<K, V> apx;
    
    bb(final int n, final l.a<K, V> a) {
        this.apx = new LruCache<K, V>(n) {
            protected int sizeOf(final K k, final V v) {
                return a.sizeOf(k, v);
            }
        };
    }
    
    @Override
    public void e(final K k, final V v) {
        this.apx.put((Object)k, (Object)v);
    }
    
    @Override
    public V get(final K k) {
        return (V)this.apx.get((Object)k);
    }
}
