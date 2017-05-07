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

public final class zzki extends zzj<zzkk>
{
    private final Bundle zzSa;
    
    public zzki(final Context context, final Looper looper, final zzf zzf, final Auth$zza auth$zza, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 16, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        Bundle zzlE;
        if (auth$zza == null) {
            zzlE = new Bundle();
        }
        else {
            zzlE = auth$zza.zzlE();
        }
        this.zzSa = zzlE;
    }
    
    protected zzkk zzau(final IBinder binder) {
        return zzkk$zza.zzaw(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.auth.service.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }
    
    @Override
    public boolean zzlN() {
        final zzf zzpa = this.zzpa();
        return !TextUtils.isEmpty((CharSequence)zzpa.getAccountName()) && !zzpa.zzb(Auth.PROXY_API).isEmpty();
    }
    
    @Override
    protected Bundle zzly() {
        return this.zzSa;
    }
}
