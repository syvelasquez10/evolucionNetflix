// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client;

import com.netflix.mediaclient.service.logging.client.volley.ClientLoggingVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;

public class ClientLoggingWebClientFactory
{
    public static final ClientLoggingWebClient create(final WebClient webClient) {
        return new ClientLoggingVolleyWebClient((FalkorVolleyWebClient)webClient);
    }
}
