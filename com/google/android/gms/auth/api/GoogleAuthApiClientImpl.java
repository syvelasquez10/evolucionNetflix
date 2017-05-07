// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.IInterface;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.d;

public final class GoogleAuthApiClientImpl extends d<IGoogleAuthService>
{
    public static final String ACTION_START_SERVICE = "com.google.android.gms.auth.service.START";
    public static final String SERVICE_DESCRIPTOR = "com.google.android.gms.auth.api.IGoogleAuthService";
    private final String Dd;
    private String[] Ds;
    
    public GoogleAuthApiClientImpl(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String dd, final String[] ds) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, ds);
        this.Dd = dd;
        this.Ds = ds;
    }
    
    public GoogleAuthApiClientImpl(final Context context, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener, final String s, final String[] array) {
        this(context, context.getMainLooper(), clientSettings, connectionCallbacks, onConnectionFailedListener, s, array);
    }
    
    @Override
    protected void a(final k k, final e e) throws RemoteException {
        k.b(e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR());
    }
    
    protected IGoogleAuthService createServiceInterface(final IBinder binder) {
        return IGoogleAuthService.Stub.asInterface(binder);
    }
    
    public String getAccountName() {
        return this.Dd;
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.IGoogleAuthService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.auth.service.START";
    }
}
