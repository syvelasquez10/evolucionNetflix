// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import com.netflix.mediaclient.service.logging.ads.volley.AdvertiserIdLoggingVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;

public final class AdvertiserIdLoggingWebClientFactory
{
    public static final AdvertiserIdLoggingWebClient create(final WebClient webClient) {
        return new AdvertiserIdLoggingVolleyWebClient((FalkorVolleyWebClient)webClient);
    }
}
