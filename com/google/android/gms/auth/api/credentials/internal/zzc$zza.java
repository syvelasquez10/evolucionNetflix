// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlb$zzb;

class zzc$zza extends zza
{
    private zzlb$zzb<Status> zzSI;
    
    zzc$zza(final zzlb$zzb<Status> zzSI) {
        this.zzSI = zzSI;
    }
    
    @Override
    public void zzg(final Status status) {
        this.zzSI.zzp(status);
    }
}
