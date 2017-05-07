// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.Api$zzd;
import android.os.IInterface;

public class zzac<T extends IInterface> extends zzj<T>
{
    private final Api$zzd<T> zzaep;
    
    public zzac(final Context context, final Looper looper, final int n, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final zzf zzf, final Api$zzd zzaep) {
        super(context, looper, n, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzaep = (Api$zzd<T>)zzaep;
    }
    
    @Override
    protected T zzV(final IBinder binder) {
        return this.zzaep.zzV(binder);
    }
    
    @Override
    protected void zzc(final int n, final T t) {
        this.zzaep.zza(n, t);
    }
    
    @Override
    protected String zzfA() {
        return this.zzaep.zzfA();
    }
    
    @Override
    protected String zzfB() {
        return this.zzaep.zzfB();
    }
}
