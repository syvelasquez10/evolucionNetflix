// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public final class zzqf$zza
{
    private boolean zzaTm;
    private boolean zzaTn;
    private final String zzaTo;
    private String zztw;
    
    public zzqf$zza(final String zzaTo) {
        this.zzaTm = true;
        this.zzaTn = false;
        this.zzaTo = zzaTo;
    }
    
    public zzqf zzBm() {
        return new zzqf(this, null);
    }
    
    public zzqf$zza zzau(final boolean zzaTm) {
        this.zzaTm = zzaTm;
        return this;
    }
    
    public zzqf$zza zzav(final boolean zzaTn) {
        this.zzaTn = zzaTn;
        return this;
    }
    
    public zzqf$zza zzfh(final String zztw) {
        this.zztw = zztw;
        return this;
    }
}
