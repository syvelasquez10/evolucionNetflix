// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.zza$zzb;
import android.content.Context;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

class zzc$2 extends zzd<Status>
{
    final /* synthetic */ zzc zzPc;
    final /* synthetic */ Credential zzPe;
    
    zzc$2(final zzc zzPc, final GoogleApiClient googleApiClient, final Credential zzPe) {
        this.zzPc = zzPc;
        this.zzPe = zzPe;
        super(googleApiClient);
    }
    
    @Override
    protected void zza(final Context context, final ICredentialsService credentialsService) {
        credentialsService.performCredentialsSaveOperation(new zzc$zza((zza$zzb<Status>)this), new SaveRequest(this.zzPe));
    }
    
    protected Status zzb(final Status status) {
        return status;
    }
}
