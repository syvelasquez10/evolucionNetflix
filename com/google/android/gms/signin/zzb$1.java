// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import java.util.Arrays;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import java.util.concurrent.Executors;
import com.google.android.gms.common.api.Api$zzb;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.signin.internal.zzi;
import com.google.android.gms.common.api.Api$zza;

final class zzb$1 extends Api$zza<zzi, zze>
{
    @Override
    public zzi zza(final Context context, final Looper looper, final zzf zzf, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        return new zzi(context, looper, true, zzf, zze, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, Executors.newSingleThreadExecutor());
    }
    
    public List<Scope> zza(final zze zze) {
        return Arrays.asList(zzb.zzaOa, zzb.zzaOb);
    }
}
