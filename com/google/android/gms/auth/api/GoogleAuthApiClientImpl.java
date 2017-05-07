// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.j;
import com.google.android.gms.common.internal.d$e;
import com.google.android.gms.common.internal.k;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
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
    
    public GoogleAuthApiClientImpl(final Context context, final Looper looper, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String dd, final String[] ds) {
        super(context, looper, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, ds);
        this.Dd = dd;
        this.Ds = ds;
    }
    
    public GoogleAuthApiClientImpl(final Context context, final ClientSettings clientSettings, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener, final String s, final String[] array) {
        this(context, context.getMainLooper(), clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener, s, array);
    }
    
    @Override
    protected void a(final k k, final d$e d$e) {
        k.b(d$e, 6111000, this.getContext().getPackageName(), this.Dd, this.gR());
    }
    
    protected IGoogleAuthService createServiceInterface(final IBinder binder) {
        return IGoogleAuthService$Stub.asInterface(binder);
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
