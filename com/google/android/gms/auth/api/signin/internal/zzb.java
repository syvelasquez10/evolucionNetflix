// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.common.internal.zzj;

public class zzb extends zzj<zze>
{
    private final GoogleSignInConfig zzTn;
    
    public zzb(final Context context, final Looper looper, final zzf zzf, GoogleSignInConfig zzTh, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 91, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        if (zzTh == null) {
            zzTh = GoogleSignInConfig.zzTh;
        }
        this.zzTn = zzTh;
    }
    
    protected zze zzax(final IBinder binder) {
        return zze$zza.zzaz(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.auth.api.signin.service.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.auth.api.signin.internal.ISignInService";
    }
}
