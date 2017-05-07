// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.api.signin.zze;
import com.google.android.gms.common.internal.zzj;

public class zzd extends zzj<zzb>
{
    private final zze zzRS;
    
    public zzd(final Context context, final Looper looper, final zzf zzf, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 87, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzRS = zzx.zzv(zze);
    }
    
    protected zzb zzaz(final IBinder binder) {
        return zzb$zza.zzay(binder);
    }
    
    @Override
    protected String zzfA() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }
    
    @Override
    protected String zzfB() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }
}
