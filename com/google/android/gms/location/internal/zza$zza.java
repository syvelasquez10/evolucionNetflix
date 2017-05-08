// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition$zza;

abstract class zza$zza extends ActivityRecognition$zza<Status>
{
    public zza$zza(final GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }
    
    public Status zzd(final Status status) {
        return status;
    }
}
