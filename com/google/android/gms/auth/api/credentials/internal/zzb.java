// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

public final class zzb implements CredentialRequestResult
{
    private final Status zzSC;
    private final Credential zzSD;
    
    public zzb(final Status zzSC, final Credential zzSD) {
        this.zzSC = zzSC;
        this.zzSD = zzSD;
    }
    
    public static zzb zzh(final Status status) {
        return new zzb(status, null);
    }
    
    @Override
    public Credential getCredential() {
        return this.zzSD;
    }
    
    @Override
    public Status getStatus() {
        return this.zzSC;
    }
}
