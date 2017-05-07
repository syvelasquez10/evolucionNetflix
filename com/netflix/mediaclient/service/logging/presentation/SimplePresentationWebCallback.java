// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

import com.netflix.mediaclient.Log;

public class SimplePresentationWebCallback implements PresentationWebCallback
{
    private static final String TAG = "nf_log";
    
    @Override
    public void onEventsDelivered(final String s) {
        Log.v("nf_log", String.format("onEventsDelivered: %s", s));
    }
    
    @Override
    public void onEventsDeliveryFailed(final String s) {
        Log.v("nf_log", String.format("onEventsDeliveryFailed: %s", s));
    }
}
