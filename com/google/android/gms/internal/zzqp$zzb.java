// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public class zzqp$zzb
{
    private zzag$zza zzaRQ;
    private final Map<String, zzag$zza> zzaTS;
    
    private zzqp$zzb() {
        this.zzaTS = new HashMap<String, zzag$zza>();
    }
    
    public zzqp$zza zzBE() {
        return new zzqp$zza(this.zzaTS, this.zzaRQ, null);
    }
    
    public zzqp$zzb zzb(final String s, final zzag$zza zzag$zza) {
        this.zzaTS.put(s, zzag$zza);
        return this;
    }
    
    public zzqp$zzb zzq(final zzag$zza zzaRQ) {
        this.zzaRQ = zzaRQ;
        return this;
    }
}
