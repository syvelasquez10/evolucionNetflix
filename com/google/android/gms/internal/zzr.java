// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzr extends Exception
{
    private long zzC;
    public final zzi zzaj;
    
    public zzr() {
        this.zzaj = null;
    }
    
    public zzr(final zzi zzaj) {
        this.zzaj = zzaj;
    }
    
    public zzr(final Throwable t) {
        super(t);
        this.zzaj = null;
    }
    
    void zza(final long zzC) {
        this.zzC = zzC;
    }
}
