// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Executors;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.signin.internal.zzi;
import com.google.android.gms.common.api.Api$zza;

final class zzqu$1 extends Api$zza<zzi, zzqx>
{
    @Override
    public zzi zza(final Context context, final Looper looper, final zzf zzf, zzqx zzaUZ, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        if (zzaUZ == null) {
            zzaUZ = zzqx.zzaUZ;
        }
        return new zzi(context, looper, true, zzf, zzaUZ, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, Executors.newSingleThreadExecutor());
    }
}
