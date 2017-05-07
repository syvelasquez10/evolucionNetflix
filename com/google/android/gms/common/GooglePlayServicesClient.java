// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

@Deprecated
public interface GooglePlayServicesClient
{
    void connect();
    
    void disconnect();
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean isConnectionCallbacksRegistered(final GooglePlayServicesClient$ConnectionCallbacks p0);
    
    boolean isConnectionFailedListenerRegistered(final GooglePlayServicesClient$OnConnectionFailedListener p0);
    
    void registerConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks p0);
    
    void registerConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener p0);
    
    void unregisterConnectionCallbacks(final GooglePlayServicesClient$ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final GooglePlayServicesClient$OnConnectionFailedListener p0);
}
