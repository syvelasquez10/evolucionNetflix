// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesClient;
import android.content.Context;

public class by extends dw<cd>
{
    private final int hp;
    
    public by(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final int hp) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.hp = hp;
    }
    
    @Override
    protected void a(final ec ec, final e e) throws RemoteException {
        ec.g(e, this.hp, this.getContext().getPackageName(), new Bundle());
    }
    
    @Override
    protected String am() {
        return "com.google.android.gms.ads.service.START";
    }
    
    @Override
    protected String an() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
    
    public cd ao() {
        return super.bQ();
    }
    
    protected cd o(final IBinder binder) {
        return cd.a.q(binder);
    }
}
