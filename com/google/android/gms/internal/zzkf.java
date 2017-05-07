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

public class zzkf extends zzj<zzkg>
{
    public zzkf(final Context context, final Looper looper, final zzf zzf, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 92, zzf, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    protected zzkg zzap(final IBinder binder) {
        return zzkg$zza.zzaq(binder);
    }
    
    @Override
    protected String zzfK() {
        return "com.google.android.gms.auth.api.consent.START";
    }
    
    @Override
    protected String zzfL() {
        return "com.google.android.gms.auth.api.consent.internal.IConsentService";
    }
}
