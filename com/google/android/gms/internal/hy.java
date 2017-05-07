// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public class hy extends d<hv>
{
    public hy(final Context context, final Looper looper, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, new String[0]);
    }
    
    protected hv H(final IBinder binder) {
        return hv.a.F(binder);
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.b(e, 6111000, this.getContext().getPackageName());
    }
    
    public hv fo() {
        return this.gS();
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.appdatasearch.internal.ILightweightAppDataSearch";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.icing.LIGHTWEIGHT_INDEX_SERVICE";
    }
}
