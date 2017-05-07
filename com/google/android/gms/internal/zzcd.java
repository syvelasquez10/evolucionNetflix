// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class zzcd
{
    private final Object zzpc;
    boolean zzvf;
    private final List<zzcc> zzvr;
    private final Map<String, String> zzvs;
    
    public zzcd(final boolean zzvf, final String s, final String s2) {
        this.zzvr = new LinkedList<zzcc>();
        this.zzvs = new LinkedHashMap<String, String>();
        this.zzpc = new Object();
        this.zzvf = zzvf;
        this.zzvs.put("action", s);
        this.zzvs.put("ad_format", s2);
    }
    
    private boolean zza(final zzcc zzcc, final long n, final String... array) {
        synchronized (this.zzpc) {
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zzvr.add(new zzcc(n, array[i], zzcc));
            }
            return true;
        }
    }
    
    public boolean zza(final zzcc zzcc, final String... array) {
        return this.zzvf && zzcc != null && this.zza(zzcc, zzp.zzbB().elapsedRealtime(), array);
    }
    
    public zzcc zzb(final long n) {
        if (!this.zzvf) {
            return null;
        }
        return new zzcc(n, null, null);
    }
    
    public zzcc zzdl() {
        return this.zzb(zzp.zzbB().elapsedRealtime());
    }
}
