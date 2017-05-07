// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlb$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.internal.zzh;

class Cast$CastApi$zza$6 extends zzh
{
    final /* synthetic */ Cast$CastApi$zza zzUR;
    
    Cast$CastApi$zza$6(final Cast$CastApi$zza zzUR, final GoogleApiClient googleApiClient) {
        this.zzUR = zzUR;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza("", (zzlb$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {
            this.zzba(2001);
        }
    }
}
