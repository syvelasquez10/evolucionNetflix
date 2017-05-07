// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.configuration.volley.ConfigurationVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.netflix.mediaclient.service.NetflixService;

public class ConfigurationWebClientFactory
{
    public static final ConfigurationWebClient create(final NetflixService netflixService, final WebClient webClient) {
        return new ConfigurationVolleyWebClient(netflixService, (FalcorVolleyWebClient)webClient);
    }
}
