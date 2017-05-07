// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.CredentialRequest;
import android.os.IInterface;

public interface ICredentialsService extends IInterface
{
    void performCredentialsDeleteOperation(final ICredentialsCallbacks p0, final DeleteRequest p1);
    
    void performCredentialsRequestOperation(final ICredentialsCallbacks p0, final CredentialRequest p1);
    
    void performCredentialsSaveOperation(final ICredentialsCallbacks p0, final SaveRequest p1);
    
    void performDisableAutoSignInOperation(final ICredentialsCallbacks p0);
}
