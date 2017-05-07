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

class Cast$CastApi$zza$1 extends zzh
{
    final /* synthetic */ String zzUQ;
    final /* synthetic */ Cast$CastApi$zza zzUR;
    final /* synthetic */ String zzyj;
    
    Cast$CastApi$zza$1(final Cast$CastApi$zza zzUR, final GoogleApiClient googleApiClient, final String zzUQ, final String zzyj) {
        this.zzUR = zzUR;
        this.zzUQ = zzUQ;
        this.zzyj = zzyj;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final zze zze) {
        try {
            zze.zza(this.zzUQ, this.zzyj, (zzlb$zzb<Status>)this);
        }
        catch (IllegalStateException ex) {}
        catch (IllegalArgumentException ex2) {
            goto Label_0015;
        }
    }
}
