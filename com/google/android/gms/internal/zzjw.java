// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import android.text.TextUtils;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.auth.api.Auth$zza;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.zzj;

public final class zzjw extends zzj<zzjy>
{
    private final Bundle zzQZ;
    
    public zzjw(final Context context, final Looper looper, final zzf zzf, final Auth$zza auth$zza, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 16, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        Bundle zzlq;
        if (auth$zza == null) {
            zzlq = new Bundle();
        }
        else {
            zzlq = auth$zza.zzlq();
        }
        this.zzQZ = zzlq;
    }
    
    protected zzjy zzau(final IBinder binder) {
        return zzjy$zza.zzaw(binder);
    }
    
    @Override
    protected String zzfA() {
        return "com.google.android.gms.auth.service.START";
    }
    
    @Override
    protected String zzfB() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }
    
    @Override
    protected Bundle zzli() {
        return this.zzQZ;
    }
    
    @Override
    public boolean zzlm() {
        final zzf zzoy = this.zzoy();
        return !TextUtils.isEmpty((CharSequence)zzoy.getAccountName()) && !zzoy.zzb(Auth.PROXY_API).isEmpty();
    }
}
