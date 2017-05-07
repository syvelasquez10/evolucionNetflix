// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zzc$zza;
import com.google.android.gms.common.api.Result;

public abstract class zzb<R extends Result> extends zzc$zza<R, zze>
{
    public zzb(final GoogleApiClient googleApiClient) {
        super(zzk.zzQf, googleApiClient);
    }
    
    public void zzaT(final int n) {
        this.zza(this.zzb(new Status(n)));
    }
}
