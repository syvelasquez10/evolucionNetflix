// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza$zzb;

class zzc$zza extends zza
{
    private zza$zzb<Status> zzPf;
    
    zzc$zza(final zza$zzb<Status> zzPf) {
        this.zzPf = zzPf;
    }
    
    @Override
    public void onStatusResult(final Status status) {
        this.zzPf.zzm(status);
    }
}
