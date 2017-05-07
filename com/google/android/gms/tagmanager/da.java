// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

class da<K, V> implements k<K, V>
{
    private final Map<K, V> ars;
    private final int art;
    private final l.a<K, V> aru;
    private int arv;
    
    da(final int art, final l.a<K, V> aru) {
        this.ars = new HashMap<K, V>();
        this.art = art;
        this.aru = aru;
    }
    
    @Override
    public void e(final K k, final V v) {
        // monitorenter(this)
        Label_0025: {
            if (k != null) {
                if (v != null) {
                    break Label_0025;
                }
            }
            try {
                throw new NullPointerException("key == null || value == null");
            }
            finally {
            }
            // monitorexit(this)
        }
        this.arv += this.aru.sizeOf(k, v);
        if (this.arv > this.art) {
            final Iterator<Map.Entry<K, V>> iterator = this.ars.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, V> entry = iterator.next();
                this.arv -= this.aru.sizeOf(entry.getKey(), entry.getValue());
                iterator.remove();
                if (this.arv <= this.art) {
                    break;
                }
            }
        }
        this.ars.put(k, v);
    }
    // monitorexit(this)
    
    @Override
    public V get(final K k) {
        synchronized (this) {
            return this.ars.get(k);
        }
    }
}
