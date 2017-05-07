// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$zzb;
import android.os.IInterface;

public class zzaa<T extends IInterface> extends zzi<T>
{
    private final Api$zzb<T> zzabe;
    
    public zzaa(final Context context, final Looper looper, final int n, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final zze zze, final Api$zzb zzabe) {
        super(context, looper, n, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, zze);
        this.zzabe = (Api$zzb<T>)zzabe;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return this.zzabe.getServiceDescriptor();
    }
    
    @Override
    protected String getStartServiceAction() {
        return this.zzabe.getStartServiceAction();
    }
    
    @Override
    protected T zzT(final IBinder binder) {
        return this.zzabe.zzT(binder);
    }
}
