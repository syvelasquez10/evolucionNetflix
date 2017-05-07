// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Bundle;

public interface GoogleApiClient$ConnectionCallbacks
{
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    void onConnected(final Bundle p0);
    
    void onConnectionSuspended(final int p0);
}
