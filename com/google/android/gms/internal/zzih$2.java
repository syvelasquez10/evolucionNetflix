// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;

class zzih$2 implements zzm$zza
{
    final /* synthetic */ zzih$zzc zzJb;
    final /* synthetic */ zzih zzJc;
    final /* synthetic */ String zzyc;
    
    zzih$2(final zzih zzJc, final String zzyc, final zzih$zzc zzJb) {
        this.zzJc = zzJc;
        this.zzyc = zzyc;
        this.zzJb = zzJb;
    }
    
    @Override
    public void zze(final zzr zzr) {
        zzb.zzaH("Failed to load URL: " + this.zzyc + "\n" + zzr.toString());
        this.zzJb.zzb(null);
    }
}
