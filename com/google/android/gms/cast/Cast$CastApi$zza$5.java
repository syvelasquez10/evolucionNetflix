// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.zza$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;

class Cast$CastApi$zza$5 extends Cast$zza
{
    final /* synthetic */ Cast$CastApi$zza zzQA;
    final /* synthetic */ String zzQB;
    
    Cast$CastApi$zza$5(final Cast$CastApi$zza zzQA, final GoogleApiClient googleApiClient, final String zzQB) {
        this.zzQA = zzQA;
        this.zzQB = zzQB;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zzb(this.zzQB, null, (zza$zzb<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.zzaL(2001);
        }
    }
}
