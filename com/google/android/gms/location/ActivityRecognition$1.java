// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.location.internal.zzl;
import com.google.android.gms.common.api.Api$zza;

final class ActivityRecognition$1 extends Api$zza<zzl, Api$ApiOptions$NoOptions>
{
    public zzl zzm(final Context context, final Looper looper, final zzf zzf, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzl(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, "activity_recognition");
    }
}
