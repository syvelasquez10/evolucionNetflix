// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Api$zzb;
import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zzc$zza;
import com.google.android.gms.common.api.Result;

abstract class zzd<R extends Result> extends zzc$zza<R, zze>
{
    zzd(final GoogleApiClient googleApiClient) {
        super(Auth.zzQM, googleApiClient);
    }
    
    protected abstract void zza(final Context p0, final zzh p1);
    
    @Override
    protected final void zza(final zze zze) {
        this.zza(zze.getContext(), zze.zzoA());
    }
}
