// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads.volley;

import com.netflix.mediaclient.service.logging.client.volley.ClientLoggingVolleyWebClientRequest;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.ads.AdvertiserIdLoggingCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.logging.ads.AdvertiserIdLoggingWebClient;

public final class AdvertiserIdLoggingVolleyWebClient implements AdvertiserIdLoggingWebClient
{
    private static final String TAG = "nf_log";
    private final FalkorVolleyWebClient mWebClient;
    
    public AdvertiserIdLoggingVolleyWebClient(final FalkorVolleyWebClient mWebClient) {
        this.mWebClient = mWebClient;
    }
    
    @Override
    public boolean isSynchronous() {
        return this.mWebClient.isSynchronous();
    }
    
    @Override
    public void sendLoggingEvent(final String s, final AdvertiserIdLoggingCallback advertiserIdLoggingCallback) {
        if (Log.isLoggable()) {
            Log.d("nf_log", "send Advertising ID event send starts: " + s);
        }
        this.mWebClient.sendLoggingRequest(new AdvertiserIdLoggingVolleyWebClientRequest(s, advertiserIdLoggingCallback));
        Log.d("nf_log", "send Advertising ID event send done.");
    }
}
