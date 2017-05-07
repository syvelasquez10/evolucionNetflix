// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.support.v4.app.FragmentActivity;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;

public interface GoogleApiClient
{
     <C extends Api$a> C a(final Api$c<C> p0);
    
     <A extends Api$a, R extends Result, T extends BaseImplementation$a<R, A>> T a(final T p0);
    
    boolean a(final Scope p0);
    
     <A extends Api$a, T extends BaseImplementation$a<? extends Result, A>> T b(final T p0);
    
    ConnectionResult blockingConnect();
    
    ConnectionResult blockingConnect(final long p0, final TimeUnit p1);
    
     <L> c<L> c(final L p0);
    
    void connect();
    
    void disconnect();
    
    Looper getLooper();
    
    boolean isConnected();
    
    boolean isConnecting();
    
    boolean isConnectionCallbacksRegistered(final GoogleApiClient$ConnectionCallbacks p0);
    
    boolean isConnectionFailedListenerRegistered(final GoogleApiClient$OnConnectionFailedListener p0);
    
    void reconnect();
    
    void registerConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks p0);
    
    void registerConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
    
    void stopAutoManage(final FragmentActivity p0);
    
    void unregisterConnectionCallbacks(final GoogleApiClient$ConnectionCallbacks p0);
    
    void unregisterConnectionFailedListener(final GoogleApiClient$OnConnectionFailedListener p0);
}
