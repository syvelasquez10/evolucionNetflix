// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import android.os.IInterface;

public interface ICredentialsCallbacks extends IInterface
{
    void onCredentialResult(final Status p0, final Credential p1);
    
    void onStatusResult(final Status p0);
}
