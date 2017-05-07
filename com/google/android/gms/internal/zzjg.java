// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.zze;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzi;

public class zzjg extends zzi<zzji>
{
    public zzjg(final Context context, final Looper looper, final zze zze, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 74, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zze);
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.accountstatus.internal.IAccountStatusService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.auth.api.accountstatus.START";
    }
    
    protected zzji zzam(final IBinder binder) {
        return zzji$zza.zzao(binder);
    }
}
