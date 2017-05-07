// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzgk
public abstract class zzhq
{
    private volatile Thread zzHt;
    private final Runnable zzx;
    
    public zzhq() {
        this.zzx = new zzhq$1(this);
    }
    
    public abstract void zzdG();
    
    public final Future zzgn() {
        return zzht.zza(this.zzx);
    }
}
