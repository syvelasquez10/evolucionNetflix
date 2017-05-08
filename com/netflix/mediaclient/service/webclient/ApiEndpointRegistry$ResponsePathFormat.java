// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public enum ApiEndpointRegistry$ResponsePathFormat
{
    GRAPH("&pathFormat=graph", "graph"), 
    HIERARCHICAL("&pathFormat=hierarchical", "hierarchical");
    
    public final String urlParams;
    public final String value;
    
    private ApiEndpointRegistry$ResponsePathFormat(final String urlParams, final String value) {
        this.urlParams = urlParams;
        this.value = value;
    }
}
