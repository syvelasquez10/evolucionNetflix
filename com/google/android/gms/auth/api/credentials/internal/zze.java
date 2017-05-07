// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.auth.api.Auth$AuthCredentialsOptions;
import com.google.android.gms.common.internal.zzj;

public final class zze extends zzj<zzh>
{
    private final Auth$AuthCredentialsOptions zzSJ;
    
    public zze(final Context context, final Looper looper, final zzf zzf, final Auth$AuthCredentialsOptions zzSJ, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 68, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzSJ = zzSJ;
    }
    
    protected zzh zzar(final IBinder binder) {
        return zzh$zza.zzat(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }
    
    @Override
    protected Bundle zzly() {
        if (this.zzSJ == null) {
            return new Bundle();
        }
        return this.zzSJ.zzly();
    }
}
