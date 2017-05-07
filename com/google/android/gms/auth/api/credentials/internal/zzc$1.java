// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

class zzc$1 extends zzd<CredentialRequestResult>
{
    final /* synthetic */ CredentialRequest zzRy;
    final /* synthetic */ zzc zzRz;
    
    zzc$1(final zzc zzRz, final GoogleApiClient googleApiClient, final CredentialRequest zzRy) {
        this.zzRz = zzRz;
        this.zzRy = zzRy;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final Context context, final zzh zzh) {
        zzh.zza(new zzc$1$1(this), this.zzRy);
    }
    
    protected CredentialRequestResult zzn(final Status status) {
        return com.google.android.gms.auth.api.credentials.internal.zzb.zzm(status);
    }
}
