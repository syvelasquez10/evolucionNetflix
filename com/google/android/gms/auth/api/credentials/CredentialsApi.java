// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface CredentialsApi
{
    PendingResult<CredentialRequestResult> request(final GoogleApiClient p0, final CredentialRequest p1);
    
    PendingResult<Status> save(final GoogleApiClient p0, final Credential p1);
}
