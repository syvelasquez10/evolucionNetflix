// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Api$Client;
import android.content.Context;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zza$zza;
import com.google.android.gms.common.api.Result;

abstract class zzd<R extends Result> extends zza$zza<R, CredentialsClientImpl>
{
    zzd(final GoogleApiClient googleApiClient) {
        super(Auth.CLIENT_KEY_CREDENTIALS_API, googleApiClient);
    }
    
    protected abstract void zza(final Context p0, final ICredentialsService p1);
    
    @Override
    protected final void zza(final CredentialsClientImpl credentialsClientImpl) {
        this.zza(credentialsClientImpl.getContext(), credentialsClientImpl.zznK());
    }
}
