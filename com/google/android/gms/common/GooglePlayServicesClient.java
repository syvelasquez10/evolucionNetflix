// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Bundle;

@Deprecated
public interface GooglePlayServicesClient
{
    void connect();
    
    void disconnect();
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean isConnectionCallbacksRegistered(final ConnectionCallbacks p0);
    
    boolean isConnectionFailedListenerRegistered(final OnConnectionFailedListener p0);
    
    void registerConnectionCallbacks(final ConnectionCallbacks p0);
    
    void registerConnectionFailedListener(final OnConnectionFailedListener p0);
    
    void unregisterConnectionCallbacks(final ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final OnConnectionFailedListener p0);
    
    @Deprecated
    public interface ConnectionCallbacks
    {
        void onConnected(final Bundle p0);
        
        void onDisconnected();
    }
    
    @Deprecated
    public interface OnConnectionFailedListener
    {
        void onConnectionFailed(final ConnectionResult p0);
    }
}
