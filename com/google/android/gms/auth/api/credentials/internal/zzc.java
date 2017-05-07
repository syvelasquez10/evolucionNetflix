// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.credentials.CredentialsApi;

public final class zzc implements CredentialsApi
{
    @Override
    public PendingResult<CredentialRequestResult> request(final GoogleApiClient googleApiClient, final CredentialRequest credentialRequest) {
        return googleApiClient.zza((PendingResult<CredentialRequestResult>)new zzc$1(this, googleApiClient, credentialRequest));
    }
    
    @Override
    public PendingResult<Status> save(final GoogleApiClient googleApiClient, final Credential credential) {
        return googleApiClient.zzb((PendingResult<Status>)new zzc$2(this, googleApiClient, credential));
    }
}
