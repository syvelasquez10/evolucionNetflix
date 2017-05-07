// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.internal.zzh;

class Cast$CastApi$zza$8 extends zzh
{
    final /* synthetic */ Cast$CastApi$zza zzTd;
    
    Cast$CastApi$zza$8(final Cast$CastApi$zza zzTd, final GoogleApiClient googleApiClient) {
        this.zzTd = zzTd;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza("", (zzc$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {
            this.zzaT(2001);
        }
    }
}
