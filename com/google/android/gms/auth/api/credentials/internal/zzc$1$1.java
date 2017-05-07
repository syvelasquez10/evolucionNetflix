// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

class zzc$1$1 extends zza
{
    final /* synthetic */ zzc$1 zzRA;
    
    zzc$1$1(final zzc$1 zzRA) {
        this.zzRA = zzRA;
    }
    
    @Override
    public void zza(final Status status, final Credential credential) {
        this.zzRA.zza((R)new zzb(status, credential));
    }
}
