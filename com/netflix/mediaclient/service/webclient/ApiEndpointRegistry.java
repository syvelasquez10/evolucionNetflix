// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

public interface ApiEndpointRegistry
{
    String getApiUrlFull(final ResponsePathFormat p0);
    
    String getClientLoggingUrlFull();
    
    String getConfigUrlFull();
    
    String getPresentationTrackingUrlFull();
    
    void updateApiEndpointHost(final String p0);
    
    public enum ResponsePathFormat
    {
        GRAPH("&pathFormat=graph"), 
        HIERARCHICAL("&pathFormat=hierarchical");
        
        public final String urlParams;
        
        private ResponsePathFormat(final String urlParams) {
            this.urlParams = urlParams;
        }
    }
}
