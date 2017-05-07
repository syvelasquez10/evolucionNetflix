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

class Cast$CastApi$zza$1 extends zzh
{
    final /* synthetic */ String zzTb;
    final /* synthetic */ String zzTc;
    final /* synthetic */ Cast$CastApi$zza zzTd;
    
    Cast$CastApi$zza$1(final Cast$CastApi$zza zzTd, final GoogleApiClient googleApiClient, final String zzTb, final String zzTc) {
        this.zzTd = zzTd;
        this.zzTb = zzTb;
        this.zzTc = zzTc;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza(this.zzTb, this.zzTc, (zzc$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {}
        catch (IllegalArgumentException ex2) {
            goto Label_0015;
        }
    }
}
