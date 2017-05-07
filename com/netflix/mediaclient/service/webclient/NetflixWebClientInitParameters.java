// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

import com.android.volley.RequestQueue;
import com.netflix.mediaclient.servicemgr.ErrorLogging;

public class NetflixWebClientInitParameters implements WebClientInitParameters
{
    private final ApiEndpointRegistry apiEndpointRegistry;
    private final ErrorLogging errorLogger;
    private final RequestQueue requestQueue;
    private final UserCredentialRegistry userCredentialRegistry;
    
    public NetflixWebClientInitParameters(final ApiEndpointRegistry apiEndpointRegistry, final UserCredentialRegistry userCredentialRegistry, final ErrorLogging errorLogger, final RequestQueue requestQueue) {
        this.apiEndpointRegistry = apiEndpointRegistry;
        this.userCredentialRegistry = userCredentialRegistry;
        this.errorLogger = errorLogger;
        this.requestQueue = requestQueue;
    }
    
    public ApiEndpointRegistry getApiEndpointRegistry() {
        return this.apiEndpointRegistry;
    }
    
    public ErrorLogging getErrorLogger() {
        return this.errorLogger;
    }
    
    public RequestQueue getRequestQueue() {
        return this.requestQueue;
    }
    
    public UserCredentialRegistry getUserCredentialRegistry() {
        return this.userCredentialRegistry;
    }
}
