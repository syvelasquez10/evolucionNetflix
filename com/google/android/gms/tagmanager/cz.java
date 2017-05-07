// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

class cz<K, V> implements k<K, V>
{
    private final Map<K, V> aap;
    private final int aaq;
    private final l.a<K, V> aar;
    private int aas;
    
    cz(final int aaq, final l.a<K, V> aar) {
        this.aap = new HashMap<K, V>();
        this.aaq = aaq;
        this.aar = aar;
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
        this.aas += this.aar.sizeOf(k, v);
        if (this.aas > this.aaq) {
            final Iterator<Map.Entry<K, V>> iterator = this.aap.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, V> entry = iterator.next();
                this.aas -= this.aar.sizeOf(entry.getKey(), entry.getValue());
                iterator.remove();
                if (this.aas <= this.aaq) {
                    break;
                }
            }
        }
        this.aap.put(k, v);
    }
    // monitorexit(this)
    
    @Override
    public V get(final K k) {
        synchronized (this) {
            return this.aap.get(k);
        }
    }
}
