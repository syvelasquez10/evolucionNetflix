// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

import java.util.Map;
import com.netflix.msl.client.NetflixUrlProvider;

public interface ApiEndpointRegistry extends NetflixUrlProvider
{
    Map<String, String> getApiRequestParams(final ApiEndpointRegistry$ResponsePathFormat p0);
    
    String getApiUrlFull(final ApiEndpointRegistry$ResponsePathFormat p0);
    
    String getClientLoggingUrlFull();
    
    String getConfigUrlFull();
    
    String getPresentationTrackingUrlFull();
    
    void updateApiEndpointHost(final String p0);
}
