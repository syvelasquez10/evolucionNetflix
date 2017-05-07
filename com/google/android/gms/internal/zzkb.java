// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zzf;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzj;

public class zzkb extends zzj<zzkd>
{
    public zzkb(final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 74, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    protected zzkd zzam(final IBinder binder) {
        return zzkd$zza.zzao(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.auth.api.accountstatus.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService";
    }
}
