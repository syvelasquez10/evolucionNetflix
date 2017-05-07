// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

class zzc$1$1 extends zza
{
    final /* synthetic */ zzc$1 zzPd;
    
    zzc$1$1(final zzc$1 zzPd) {
        this.zzPd = zzPd;
    }
    
    @Override
    public void onCredentialResult(final Status status, final Credential credential) {
        this.zzPd.setResult((R)new zzb(status, credential));
    }
}
