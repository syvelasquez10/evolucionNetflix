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

public class zzjs extends zzj<zzju>
{
    public zzjs(final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 74, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    protected zzju zzao(final IBinder binder) {
        return zzju$zza.zzaq(binder);
    }
    
    @Override
    protected String zzfA() {
        return "com.google.android.gms.auth.api.accountstatus.START";
    }
    
    @Override
    protected String zzfB() {
        return "com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService";
    }
}
