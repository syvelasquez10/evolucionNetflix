// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzhy$2 implements zzm$zza
{
    final /* synthetic */ zzhy$zzc zzIh;
    final /* synthetic */ zzhy zzIi;
    final /* synthetic */ String zzxv;
    
    zzhy$2(final zzhy zzIi, final String zzxv, final zzhy$zzc zzIh) {
        this.zzIi = zzIi;
        this.zzxv = zzxv;
        this.zzIh = zzIh;
    }
    
    @Override
    public void zze(final zzr zzr) {
        zzb.zzaE("Failed to load URL: " + this.zzxv + "\n" + zzr.toString());
        this.zzIh.zzb(null);
    }
}
