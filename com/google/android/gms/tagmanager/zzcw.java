// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

class zzcw<K, V> implements zzl<K, V>
{
    private final Map<K, V> zzaSl;
    private final int zzaSm;
    private final zzm$zza<K, V> zzaSn;
    private int zzaSo;
    
    zzcw(final int zzaSm, final zzm$zza<K, V> zzaSn) {
        this.zzaSl = new HashMap<K, V>();
        this.zzaSm = zzaSm;
        this.zzaSn = zzaSn;
    }
    
    @Override
    public V get(final K k) {
        synchronized (this) {
            return this.zzaSl.get(k);
        }
    }
    
    @Override
    public void zzf(final K k, final V v) {
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
        this.zzaSo += this.zzaSn.sizeOf(k, v);
        if (this.zzaSo > this.zzaSm) {
            final Iterator<Map.Entry<K, V>> iterator = this.zzaSl.entrySet().iterator();
            while (iterator.hasNext()) {
                final Map.Entry<K, V> entry = iterator.next();
                this.zzaSo -= this.zzaSn.sizeOf(entry.getKey(), entry.getValue());
                iterator.remove();
                if (this.zzaSo <= this.zzaSm) {
                    break;
                }
            }
        }
        this.zzaSl.put(k, v);
    }
    // monitorexit(this)
}
