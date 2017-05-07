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

class Cast$CastApi$zza$1 extends zzh
{
    final /* synthetic */ Cast$CastApi$zza zzQA;
    final /* synthetic */ String zzQy;
    final /* synthetic */ String zzQz;
    
    Cast$CastApi$zza$1(final Cast$CastApi$zza zzQA, final GoogleApiClient googleApiClient, final String zzQy, final String zzQz) {
        this.zzQA = zzQA;
        this.zzQy = zzQy;
        this.zzQz = zzQz;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza(this.zzQy, this.zzQz, (zza$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {}
        catch (IllegalArgumentException ex2) {
            goto Label_0015;
        }
    }
}
