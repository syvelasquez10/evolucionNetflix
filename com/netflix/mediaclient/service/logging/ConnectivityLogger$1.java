// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.Log;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.ConnectionClassManager$ConnectionClassStateChangeListener;

final class ConnectivityLogger$1 implements ConnectionClassManager$ConnectionClassStateChangeListener
{
    @Override
    public void onBandwidthStateChange(final ConnectionQuality connectionQuality) {
        Log.v("ConnectivityLogger", "onBandwidthStateChange: %s, kbps: %f", connectionQuality, ConnectionClassManager.getInstance().getDownloadKBitsPerSecond());
    }
}
