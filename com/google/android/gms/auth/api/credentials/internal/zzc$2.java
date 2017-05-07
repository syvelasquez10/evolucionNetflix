// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.internal.zzlb$zzb;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

class zzc$2 extends zzd<Status>
{
    final /* synthetic */ zzc zzSF;
    final /* synthetic */ Credential zzSH;
    
    zzc$2(final zzc zzSF, final GoogleApiClient googleApiClient, final Credential zzSH) {
        this.zzSF = zzSF;
        this.zzSH = zzSH;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final Context context, final zzh zzh) {
        zzh.zza(new zzc$zza((zzlb$zzb<Status>)this), new SaveRequest(this.zzSH));
    }
    
    protected Status zzd(final Status status) {
        return status;
    }
}
