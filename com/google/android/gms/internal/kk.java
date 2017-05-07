// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import android.os.Bundle;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.google.android.gms.common.internal.d;

public class kk extends d<ko> implements kj
{
    private static final Set<String> Tm;
    private final String Dd;
    
    static {
        Tm = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>() {
            {
                this.add("https://www.googleapis.com/auth/fitness.activity.read");
                this.add("https://www.googleapis.com/auth/fitness.activity.write");
                this.add("https://www.googleapis.com/auth/fitness.body.read");
                this.add("https://www.googleapis.com/auth/fitness.body.write");
                this.add("https://www.googleapis.com/auth/fitness.location.read");
                this.add("https://www.googleapis.com/auth/fitness.location.write");
            }
        });
    }
    
    public kk(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String dd, final String[] array) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, array);
        this.Dd = dd;
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.a(e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR(), new Bundle());
    }
    
    protected ko ao(final IBinder binder) {
        return ko.a.as(binder);
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
