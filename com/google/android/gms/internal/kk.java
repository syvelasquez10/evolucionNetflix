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
import java.util.Collections;
import java.util.Set;
import com.google.android.gms.common.internal.d;

public class kk extends d<ko> implements kj
{
    private static final Set<String> Tm;
    private final String Dd;
    
    static {
        Tm = Collections.unmodifiableSet((Set<? extends String>)new kk$1());
    }
    
    public kk(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String dd, final String[] array) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, array);
        this.Dd = dd;
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.a(d$e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR(), new Bundle());
    }
    
    protected ko ao(final IBinder binder) {
        return ko$a.as(binder);
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitnessService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.fitness.GoogleFitnessService.START";
    }
    
    @Override
    public ko iT() {
        return this.gS();
    }
}
