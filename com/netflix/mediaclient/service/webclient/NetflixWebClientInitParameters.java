// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

import com.netflix.mediaclient.servicemgr.ErrorLogging;

public class NetflixWebClientInitParameters implements WebClientInitParameters
{
    private ApiEndpointRegistry apiEndpointRegistry;
    private Object connectionObject;
    private ErrorLogging errorLogger;
    private UserCredentialRegistry userCredentialRegistry;
    
    public NetflixWebClientInitParameters(final ApiEndpointRegistry apiEndpointRegistry, final UserCredentialRegistry userCredentialRegistry, final ErrorLogging errorLogger, final Object connectionObject) {
        this.apiEndpointRegistry = apiEndpointRegistry;
        this.userCredentialRegistry = userCredentialRegistry;
        this.errorLogger = errorLogger;
        this.connectionObject = connectionObject;
    }
    
    public ApiEndpointRegistry getApiEndpointRegistry() {
        return this.apiEndpointRegistry;
    }
    
    public Object getConnectionObject() {
        return this.connectionObject;
    }
    
    public ErrorLogging getErrorLogger() {
        return this.errorLogger;
    }
    
    public UserCredentialRegistry getUserCredentialRegistry() {
        return this.userCredentialRegistry;
    }
}
