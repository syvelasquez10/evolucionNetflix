// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import java.util.concurrent.Executors;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.signin.internal.zzi;
import com.google.android.gms.common.api.Api$zza;

final class zzb$2 extends Api$zza<zzi, Api$ApiOptions$NoOptions>
{
    public zzi zzt(final Context context, final Looper looper, final zzf zzf, final Api$ApiOptions$NoOptions api$ApiOptions$NoOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzi(context, looper, false, zzf, zze.zzaOd, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, Executors.newSingleThreadExecutor());
    }
}
