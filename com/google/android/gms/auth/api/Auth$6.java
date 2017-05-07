// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.auth.api.signin.internal.zzb;
import com.google.android.gms.common.api.Api$zza;

final class Auth$6 extends Api$zza<zzb, GoogleSignInConfig>
{
    @Override
    public zzb zza(final Context context, final Looper looper, final zzf zzf, final GoogleSignInConfig googleSignInConfig, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzb(context, looper, zzf, googleSignInConfig, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
