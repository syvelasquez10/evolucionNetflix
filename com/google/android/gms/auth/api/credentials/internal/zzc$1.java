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
    final /* synthetic */ CredentialRequest zzSE;
    final /* synthetic */ zzc zzSF;
    
    zzc$1(final zzc zzSF, final GoogleApiClient googleApiClient, final CredentialRequest zzSE) {
        this.zzSF = zzSF;
        this.zzSE = zzSE;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final Context context, final zzh zzh) {
        zzh.zza(new zzc$1$1(this), this.zzSE);
    }
    
    protected CredentialRequestResult zzi(final Status status) {
        return zzb.zzh(status);
    }
}
