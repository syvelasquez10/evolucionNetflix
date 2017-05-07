// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public class zze implements zzn
{
    private final Executor zzs;
    
    public zze(final Handler handler) {
        this.zzs = new zze$1(this, handler);
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzm<?> zzm) {
        this.zza(zzk, zzm, null);
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzm<?> zzm, final Runnable runnable) {
        zzk.zzv();
        zzk.zzc("post-response");
        this.zzs.execute(new zze$zza(this, zzk, zzm, runnable));
    }
    
    @Override
    public void zza(final zzk<?> zzk, final zzr zzr) {
        zzk.zzc("post-error");
        this.zzs.execute(new zze$zza(this, zzk, zzm.zzd(zzr), null));
    }
}
