// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzcf
{
    private final zzcg zzoo;
    private final Map<String, zzce> zzvQ;
    
    public zzcf(final zzcg zzoo) {
        this.zzoo = zzoo;
        this.zzvQ = new HashMap<String, zzce>();
    }
    
    public void zza(final String s, final zzce zzce) {
        this.zzvQ.put(s, zzce);
    }
    
    public void zza(final String s, final String s2, final long n) {
        zzcc.zza(this.zzoo, this.zzvQ.get(s2), n, s);
        this.zzvQ.put(s, zzcc.zza(this.zzoo, n));
    }
    
    public zzcg zzdm() {
        return this.zzoo;
    }
}
