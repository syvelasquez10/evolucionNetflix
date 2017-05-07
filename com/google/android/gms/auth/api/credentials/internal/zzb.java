// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;

public final class zzb implements CredentialRequestResult
{
    private final Status zzQA;
    private final Credential zzRx;
    
    public zzb(final Status zzQA, final Credential zzRx) {
        this.zzQA = zzQA;
        this.zzRx = zzRx;
    }
    
    public static zzb zzm(final Status status) {
        return new zzb(status, null);
    }
    
    @Override
    public Credential getCredential() {
        return this.zzRx;
    }
    
    @Override
    public Status getStatus() {
        return this.zzQA;
    }
}
