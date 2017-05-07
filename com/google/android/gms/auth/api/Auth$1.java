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
import com.google.android.gms.internal.zzjj;
import com.google.android.gms.common.api.Api$zza;

final class Auth$1 implements Api$zza<zzjj, Auth$zza>
{
    @Override
    public int getPriority() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public zzjj zza(final Context context, final Looper looper, final zze zze, final Auth$zza auth$zza, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzjj(context, looper, zze, auth$zza, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
