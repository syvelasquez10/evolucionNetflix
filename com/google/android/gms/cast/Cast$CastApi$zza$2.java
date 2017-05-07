// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.internal.zzlb$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;

class Cast$CastApi$zza$2 extends Cast$zza
{
    final /* synthetic */ Cast$CastApi$zza zzUR;
    final /* synthetic */ String zzUS;
    
    Cast$CastApi$zza$2(final Cast$CastApi$zza zzUR, final GoogleApiClient googleApiClient, final String zzUS) {
        this.zzUR = zzUR;
        this.zzUS = zzUS;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza(this.zzUS, false, (zzlb$zzb<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.zzba(2001);
        }
    }
}
