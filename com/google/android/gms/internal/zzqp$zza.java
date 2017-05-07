// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Map;

public class zzqp$zza
{
    private final zzag$zza zzaRQ;
    private final Map<String, zzag$zza> zzaTS;
    
    private zzqp$zza(final Map<String, zzag$zza> zzaTS, final zzag$zza zzaRQ) {
        this.zzaTS = zzaTS;
        this.zzaRQ = zzaRQ;
    }
    
    public static zzqp$zzb zzBC() {
        return new zzqp$zzb(null);
    }
    
    @Override
    public String toString() {
        return "Properties: " + this.zzBD() + " pushAfterEvaluate: " + this.zzaRQ;
    }
    
    public zzag$zza zzAI() {
        return this.zzaRQ;
    }
    
    public Map<String, zzag$zza> zzBD() {
        return Collections.unmodifiableMap((Map<? extends String, ? extends zzag$zza>)this.zzaTS);
    }
    
    public void zza(final String s, final zzag$zza zzag$zza) {
        this.zzaTS.put(s, zzag$zza);
    }
}
