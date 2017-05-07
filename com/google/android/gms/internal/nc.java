// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public class nc extends d<na>
{
    public nc(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, (String[])null);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.a(d$e, 6111000, this.getContext().getPackageName(), new Bundle());
    }
    
    public na bB(final IBinder binder) {
        return na$a.bA(binder);
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.panorama.internal.IPanoramaService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.panorama.service.START";
    }
}
