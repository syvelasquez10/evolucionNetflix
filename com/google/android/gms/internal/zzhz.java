// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzgr
public abstract class zzhz
{
    private volatile Thread zzIl;
    private boolean zzIm;
    private final Runnable zzx;
    
    public zzhz() {
        this.zzx = new zzhz$1(this);
        this.zzIm = false;
    }
    
    public abstract void zzbn();
    
    public final Future zzgz() {
        if (this.zzIm) {
            return zzic.zza(1, this.zzx);
        }
        return zzic.zza(this.zzx);
    }
}
