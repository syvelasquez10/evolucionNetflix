// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.browse.volley.BrowseVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.netflix.mediaclient.service.NetflixService;
import java.util.List;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.cache.HardCache;

public final class BrowseWebClientFactory
{
    public static final BrowseWebClient create(final HardCache hardCache, final SoftCache softCache, final SoftCache softCache2, final List<String> list, final List<String> list2, final NetflixService netflixService, final WebClient webClient) {
        return new BrowseVolleyWebClient(hardCache, softCache, softCache2, list, list2, netflixService, (FalcorVolleyWebClient)webClient);
    }
}
