// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.configuration.ConfigurationWebClient;

public class ConfigurationVolleyWebClient implements ConfigurationWebClient
{
    private final NetflixService service;
    private final FalcorVolleyWebClient webclient;
    
    public ConfigurationVolleyWebClient(final NetflixService service, final FalcorVolleyWebClient webclient) {
        this.webclient = webclient;
        this.service = service;
    }
    
    @Override
    public void fetchConfigData(final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.webclient.sendConfigRequest(new FetchConfigDataRequest(this.service.getApplicationContext(), this.service.getConfiguration(), configurationAgentWebCallback));
    }
    
    @Override
    public boolean isSynchronous() {
        return this.webclient.isSynchronous();
    }
}
