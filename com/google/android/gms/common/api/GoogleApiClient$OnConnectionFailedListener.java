// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient$OnConnectionFailedListener;

public interface GoogleApiClient$OnConnectionFailedListener extends GooglePlayServicesClient$OnConnectionFailedListener
{
    void onConnectionFailed(final ConnectionResult p0);
}
