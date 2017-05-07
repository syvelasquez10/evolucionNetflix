// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.Request;
import com.netflix.mediaclient.service.webclient.NetflixWebClientInitParameters;
import com.netflix.mediaclient.service.webclient.WebClientInitParameters;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.webclient.WebClient;

public class VolleyWebClient implements WebClient
{
    protected static RequestQueue mRequestQueue;
    protected ErrorLogging mErrorLogger;
    protected int mTimeoutInMs;
    protected UserCredentialRegistry mUserCredentialRegistry;
    
    protected RetryPolicy createRetryPolicy() {
        int mTimeoutInMs;
        if (this.mTimeoutInMs <= 0) {
            mTimeoutInMs = 2500;
        }
        else {
            mTimeoutInMs = this.mTimeoutInMs;
        }
        return new DefaultRetryPolicy(mTimeoutInMs, 1, 1.0f);
    }
    
    @Override
    public void init(final WebClientInitParameters webClientInitParameters) {
        if (webClientInitParameters instanceof NetflixWebClientInitParameters) {
            final NetflixWebClientInitParameters netflixWebClientInitParameters = (NetflixWebClientInitParameters)webClientInitParameters;
            this.mUserCredentialRegistry = netflixWebClientInitParameters.getUserCredentialRegistry();
            VolleyWebClient.mRequestQueue = (RequestQueue)netflixWebClientInitParameters.getConnectionObject();
            this.mErrorLogger = netflixWebClientInitParameters.getErrorLogger();
            return;
        }
        throw new IllegalArgumentException("Expecting NetflixWebClientInitParameters and receiving " + webClientInitParameters);
    }
    
    @Override
    public final boolean isSynchronous() {
        return false;
    }
    
    protected void sendRequest(final VolleyWebClientRequest<?> volleyWebClientRequest, final String s) {
        volleyWebClientRequest.setUserCredentialRegistry(this.mUserCredentialRegistry);
        volleyWebClientRequest.setRetryPolicy(this.createRetryPolicy());
        volleyWebClientRequest.initUrl(s);
        VolleyWebClient.mRequestQueue.add(volleyWebClientRequest);
    }
    
    @Override
    public void setTimeout(final int mTimeoutInMs) {
        this.mTimeoutInMs = mTimeoutInMs;
    }
}
