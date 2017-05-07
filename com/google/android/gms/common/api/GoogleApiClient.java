// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

public interface GoogleApiClient
{
     <C extends Api$a> C a(final Api$c<C> p0);
    
     <A extends Api$a, T extends BaseImplementation$a<? extends Result, A>> T b(final T p0);
    
    void connect();
    
    void disconnect();
    
    boolean isConnected();
    
    boolean isConnecting();
    
    void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks p0);
    
    void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
    
    void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
}
