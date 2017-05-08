// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.facebook.network.connectionclass.DeviceBandwidthSampler;
import com.android.volley.Request;
import com.android.volley.NetworkDispatcher$NetworkDispatcherListener;

final class ConnectivityLogger$2 implements NetworkDispatcher$NetworkDispatcherListener
{
    @Override
    public void onCompleted(final Request request, final Throwable t) {
        DeviceBandwidthSampler.getInstance().stopSampling();
    }
    
    @Override
    public void onStarted(final Request request) {
        DeviceBandwidthSampler.getInstance().startSampling();
    }
}
