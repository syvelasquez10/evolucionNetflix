// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public enum ApiEndpointRegistry$ResponsePathFormat
{
    GRAPH("&pathFormat=graph"), 
    HIERARCHICAL("&pathFormat=hierarchical");
    
    public final String urlParams;
    
    private ApiEndpointRegistry$ResponsePathFormat(final String urlParams) {
        this.urlParams = urlParams;
    }
}
