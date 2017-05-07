// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.auth.api.Auth$zza;
import com.google.android.gms.common.internal.zze;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzi;

public final class zzjj extends zzi<zzjl>
{
    private final Bundle zzOR;
    
    public zzjj(final Context context, final Looper looper, final zze zze, final Auth$zza auth$zza, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 16, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zze);
        Bundle zzkY;
        if (auth$zza == null) {
            zzkY = new Bundle();
        }
        else {
            zzkY = auth$zza.zzkY();
        }
        this.zzOR = zzkY;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.auth.service.START";
    }
    
    @Override
    public boolean requiresSignIn() {
        final zze zznI = this.zznI();
        return !TextUtils.isEmpty((CharSequence)zznI.getAccountName()) && !zznI.zzb(Auth.zzOL).isEmpty();
    }
    
    protected zzjl zzar(final IBinder binder) {
        return zzjl$zza.zzat(binder);
    }
    
    @Override
    protected Bundle zzkR() {
        return this.zzOR;
    }
}
