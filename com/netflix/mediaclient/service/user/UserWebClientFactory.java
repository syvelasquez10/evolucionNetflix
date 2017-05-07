// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.user.volley.UserVolleyWebClient;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.webclient.WebClient;
import com.netflix.mediaclient.service.NetflixService;

public class UserWebClientFactory
{
    public static final UserWebClient create(final NetflixService netflixService, final WebClient webClient) {
        return new UserVolleyWebClient(netflixService, (FalcorVolleyWebClient)webClient);
    }
}
