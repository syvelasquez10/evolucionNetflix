// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzj;

public class zzb extends zzj<zzi>
{
    private final String zzaFa;
    protected final zzp<zzi> zzaFb;
    
    public zzb(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String zzaFa, final zzf zzf) {
        super(context, looper, 23, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzaFb = new zzb$1(this);
        this.zzaFa = zzaFa;
    }
    
    protected zzi zzbY(final IBinder binder) {
        return zzi$zza.zzcb(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }
    
    @Override
    protected Bundle zzly() {
        final Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzaFa);
        return bundle;
    }
}
