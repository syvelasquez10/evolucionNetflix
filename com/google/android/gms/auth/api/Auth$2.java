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
import com.google.android.gms.auth.api.credentials.internal.zze;
import com.google.android.gms.common.api.Api$zza;

final class Auth$2 extends Api$zza<zze, Auth$AuthCredentialsOptions>
{
    @Override
    public zze zza(final Context context, final Looper looper, final zzf zzf, final Auth$AuthCredentialsOptions auth$AuthCredentialsOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zze(context, looper, zzf, auth$AuthCredentialsOptions, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
