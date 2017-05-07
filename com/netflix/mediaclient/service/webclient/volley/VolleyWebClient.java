// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RetryPolicy;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.RequestQueue;
import com.netflix.mediaclient.service.webclient.WebClient;

public abstract class VolleyWebClient implements WebClient
{
    protected static RequestQueue sRequestQueue;
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
    public final boolean isSynchronous() {
        return false;
    }
    
    @Override
    public void setTimeout(final int mTimeoutInMs) {
        this.mTimeoutInMs = mTimeoutInMs;
    }
}
