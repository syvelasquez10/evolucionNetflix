// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.android.volley.NetworkDispatcher$NetworkDispatcherListener;
import com.facebook.network.connectionclass.ConnectionClassManager$ConnectionClassStateChangeListener;

class ConnectivityLogger
{
    private static final String TAG = "ConnectivityLogger";
    private static final ConnectionClassManager$ConnectionClassStateChangeListener connectionClassManagerListener;
    private static final NetworkDispatcher$NetworkDispatcherListener networkDispatcherListener;
    
    static {
        connectionClassManagerListener = new ConnectivityLogger$1();
        networkDispatcherListener = new ConnectivityLogger$2();
    }
    
    static ConnectionClassManager$ConnectionClassStateChangeListener getConnectionClassManagerListener() {
        return ConnectivityLogger.connectionClassManagerListener;
    }
    
    static NetworkDispatcher$NetworkDispatcherListener getNetworkDispatcherListener() {
        return ConnectivityLogger.networkDispatcherListener;
    }
}
