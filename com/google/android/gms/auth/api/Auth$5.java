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
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.common.api.Api$zza;

final class Auth$5 extends Api$zza<zzg, com.google.android.gms.auth.api.signin.zzg>
{
    @Override
    public zzg zza(final Context context, final Looper looper, final zzf zzf, final com.google.android.gms.auth.api.signin.zzg zzg, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzg(context, looper, zzf, zzg, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
