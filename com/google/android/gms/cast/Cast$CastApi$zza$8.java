// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.internal.zzh;

class Cast$CastApi$zza$8 extends zzh
{
    final /* synthetic */ Cast$CastApi$zza zzQA;
    
    Cast$CastApi$zza$8(final Cast$CastApi$zza zzQA, final GoogleApiClient googleApiClient) {
        this.zzQA = zzQA;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza("", (zza$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {
            this.zzaL(2001);
        }
    }
}
