// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zze;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.internal.zzjq;
import com.google.android.gms.common.api.Api$zza;

final class Auth$4 implements Api$zza<zzjq, Api$ApiOptions$NoOptions>
{
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    
    public zzjq zzf(final Context context, final Looper looper, final zze zze, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzjq(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
