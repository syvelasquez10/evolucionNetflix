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

public class cw extends ff<db>
{
    final int pe;
    
    public cw(final Context context, final ConnectionCallbacks connectionCallbacks, final OnConnectionFailedListener onConnectionFailedListener, final int pe) {
        super(context, connectionCallbacks, onConnectionFailedListener, new String[0]);
        this.pe = pe;
    }
    
    @Override
    protected void a(final fm fm, final e e) throws RemoteException {
        fm.g(e, this.pe, this.getContext().getPackageName(), new Bundle());
    }
    
    @Override
    protected String bg() {
        return "com.google.android.gms.ads.service.START";
    }
    
    @Override
    protected String bh() {
        return "com.google.android.gms.ads.internal.request.IAdRequestService";
    }
    
    public db bi() {
        return super.eM();
    }
    
    protected db q(final IBinder binder) {
        return db.a.s(binder);
    }
}
