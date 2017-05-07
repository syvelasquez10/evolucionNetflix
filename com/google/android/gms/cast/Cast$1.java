// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.Api$zza;

final class Cast$1 extends Api$zza<zze, Cast$CastOptions>
{
    @Override
    public zze zza(final Context context, final Looper looper, final zzf zzf, final Cast$CastOptions cast$CastOptions, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        zzx.zzb(cast$CastOptions, "Setting the API options is required.");
        return new zze(context, looper, zzf, cast$CastOptions.zzUV, cast$CastOptions.zzUX, cast$CastOptions.zzUW, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
}
