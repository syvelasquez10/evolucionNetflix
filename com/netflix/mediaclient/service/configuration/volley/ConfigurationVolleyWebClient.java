// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.configuration.ConfigurationWebClient;

public class ConfigurationVolleyWebClient implements ConfigurationWebClient
{
    private final NetflixService service;
    private final FalkorVolleyWebClient webclient;
    
    public ConfigurationVolleyWebClient(final NetflixService service, final FalkorVolleyWebClient webclient) {
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
    
    @Override
    public void verifyLogin(final String s, final String s2, final ConfigurationAgentWebCallback configurationAgentWebCallback) {
        this.webclient.sendConfigRequest(new VerifyLoginRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, s2, configurationAgentWebCallback));
    }
}
