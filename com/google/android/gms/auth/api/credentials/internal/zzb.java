// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

public final class zzb implements CredentialRequestResult
{
    private final Status zzOt;
    private final Credential zzPa;
    
    public zzb(final Status zzOt, final Credential zzPa) {
        this.zzOt = zzOt;
        this.zzPa = zzPa;
    }
    
    public static zzb zzj(final Status status) {
        return new zzb(status, null);
    }
    
    @Override
    public Credential getCredential() {
        return this.zzPa;
    }
    
    @Override
    public Status getStatus() {
        return this.zzOt;
    }
}
