// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;

class l<K, V>
{
    final a<K, V> anP;
    
    public l() {
        this.anP = (a<K, V>)new a<K, V>() {
            @Override
            public int sizeOf(final K k, final V v) {
                return 1;
            }
        };
    }
    
    public k<K, V> a(final int n, final a<K, V> a) {
        if (n <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (this.nN() < 12) {
            return new da<K, V>(n, a);
        }
        return new bb<K, V>(n, a);
    }
    
    int nN() {
        return Build$VERSION.SDK_INT;
    }
    
    public interface a<K, V>
    {
        int sizeOf(final K p0, final V p1);
    }
}
