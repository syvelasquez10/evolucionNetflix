// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zzc$zzb;

class zzc$zza extends zza
{
    private zzc$zzb<Status> zzRC;
    
    zzc$zza(final zzc$zzb<Status> zzRC) {
        this.zzRC = zzRC;
    }
    
    @Override
    public void zzl(final Status status) {
        this.zzRC.zzn(status);
    }
}
