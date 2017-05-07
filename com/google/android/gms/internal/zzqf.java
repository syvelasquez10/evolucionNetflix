// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzqf
{
    private final boolean zzaTm;
    private final boolean zzaTn;
    private final String zzaTo;
    private final String zztw;
    
    private zzqf(final zzqf$zza zzqf$zza) {
        this.zzaTo = zzqf$zza.zzaTo;
        this.zzaTm = zzqf$zza.zzaTm;
        this.zzaTn = zzqf$zza.zzaTn;
        this.zztw = zzqf$zza.zztw;
    }
    
    public String getTrackingId() {
        return this.zztw;
    }
    
    public String zzBj() {
        return this.zzaTo;
    }
    
    public boolean zzBk() {
        return this.zzaTm;
    }
    
    public boolean zzBl() {
        return this.zzaTn;
    }
}
