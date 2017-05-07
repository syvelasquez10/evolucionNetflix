// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

import com.netflix.mediaclient.service.logging.presentation.volley.PresentationVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;

public class PresentationWebClientFactory
{
    public static final PresentationWebClient create(final WebClient webClient) {
        return new PresentationVolleyWebClient((FalkorVolleyWebClient)webClient);
    }
}
