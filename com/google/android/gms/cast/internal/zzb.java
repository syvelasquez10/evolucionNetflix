// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.zzlb$zza;
import com.google.android.gms.common.api.Result;

public abstract class zzb<R extends Result> extends zzlb$zza<R, zze>
{
    public zzb(final GoogleApiClient googleApiClient) {
        super(zzk.zzRk, googleApiClient);
    }
    
    public void zzba(final int n) {
        this.zzb(this.zzb(new Status(n)));
    }
}
