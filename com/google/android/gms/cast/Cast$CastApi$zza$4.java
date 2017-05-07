// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.internal.zzlb$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;

class Cast$CastApi$zza$4 extends Cast$zza
{
    final /* synthetic */ String val$sessionId;
    final /* synthetic */ Cast$CastApi$zza zzUR;
    final /* synthetic */ String zzUS;
    final /* synthetic */ JoinOptions zzUU;
    
    Cast$CastApi$zza$4(final Cast$CastApi$zza zzUR, final GoogleApiClient googleApiClient, final String zzUS, final String val$sessionId, final JoinOptions zzUU) {
        this.zzUR = zzUR;
        this.zzUS = zzUS;
        this.val$sessionId = val$sessionId;
        this.zzUU = zzUU;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza(this.zzUS, this.val$sessionId, this.zzUU, (zzlb$zzb<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.zzba(2001);
        }
    }
}
