// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.cast.internal.zzb;

abstract class Cast$zza extends zzb<Cast$ApplicationConnectionResult>
{
    public Cast$zza(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
    
    public Cast$ApplicationConnectionResult zzl(final Status status) {
        return new Cast$zza$1(this, status);
    }
}
