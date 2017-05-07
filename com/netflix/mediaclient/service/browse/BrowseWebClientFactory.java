// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.browse.volley.BrowseVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;

public final class BrowseWebClientFactory
{
    public static final BrowseWebClient create(final BrowseWebClientCache browseWebClientCache, final NetflixService netflixService, final WebClient webClient) {
        return new BrowseVolleyWebClient(browseWebClientCache, netflixService, (FalcorVolleyWebClient)webClient);
    }
}
