// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.zza$zza;
import com.google.android.gms.common.api.Result;

public abstract class zzb<R extends Result> extends zza$zza<R, zze>
{
    public zzb(final GoogleApiClient googleApiClient) {
        super(zzk.zzNX, googleApiClient);
    }
    
    public void zzaL(final int n) {
        this.setResult(this.createFailedResult(new Status(n)));
    }
}
