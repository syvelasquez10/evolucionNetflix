// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.internal.zzi;

public final class CredentialsClientImpl extends zzi<ICredentialsService>
{
    public CredentialsClientImpl(final Context context, final Looper looper, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 68, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
    }
    
    protected ICredentialsService createServiceInterface(final IBinder binder) {
        return ICredentialsService$zza.zzaq(binder);
    }
    
    @Override
    protected String getServiceDescriptor() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }
    
    @Override
    protected String getStartServiceAction() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }
}
