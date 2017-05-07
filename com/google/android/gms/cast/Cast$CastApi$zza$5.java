// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.zzc$zzb;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.GoogleApiClient;

class Cast$CastApi$zza$5 extends Cast$zza
{
    final /* synthetic */ Cast$CastApi$zza zzTd;
    final /* synthetic */ String zzTe;
    
    Cast$CastApi$zza$5(final Cast$CastApi$zza zzTd, final GoogleApiClient googleApiClient, final String zzTe) {
        this.zzTd = zzTd;
        this.zzTe = zzTe;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zzb(this.zzTe, null, (zzc$zzb<Cast$ApplicationConnectionResult>)this);
        }
        catch (IllegalStateException ex) {
            this.zzaT(2001);
        }
    }
}
