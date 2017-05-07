// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.zzc$zzb;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

class zzc$2 extends zzd<Status>
{
    final /* synthetic */ Credential zzRB;
    final /* synthetic */ zzc zzRz;
    
    zzc$2(final zzc zzRz, final GoogleApiClient googleApiClient, final Credential zzRB) {
        this.zzRz = zzRz;
        this.zzRB = zzRB;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final Context context, final zzh zzh) {
        zzh.zza(new com.google.android.gms.auth.api.credentials.internal.zzc$zza((zzc$zzb<Status>)this), new SaveRequest(this.zzRB));
    }
    
    protected Status zzd(final Status status) {
        return status;
    }
}
