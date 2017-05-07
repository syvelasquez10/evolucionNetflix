// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.user.UserWebClient;

public final class UserVolleyWebClient implements UserWebClient
{
    private final NetflixService service;
    private final FalcorVolleyWebClient webclient;
    
    public UserVolleyWebClient(final NetflixService service, final FalcorVolleyWebClient webclient) {
        this.webclient = webclient;
        this.service = service;
    }
    
    @Override
    public void connectWithFacebook(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchConnectWithFacebookRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, userAgentWebCallback));
    }
    
    @Override
    public void disconnectFromFacebook(final UserAgentWebCallback userAgentWebCallback) {
        userAgentWebCallback.onDisconnectWithFacebook(-10, "error : not implemented");
    }
    
    @Override
    public void doDummyWebCall(final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchDummyWebRequest(this.service.getApplicationContext(), this.service.getConfiguration(), userAgentWebCallback));
    }
    
    @Override
    public void fetchAccountData(final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchAccountDataRequest(this.service.getApplicationContext(), this.service.getConfiguration(), userAgentWebCallback));
    }
    
    @Override
    public void fetchProfileData(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchProfileDataRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, userAgentWebCallback));
    }
    
    @Override
    public final boolean isSynchronous() {
        return this.webclient.isSynchronous();
    }
    
    @Override
    public void switchWebUserProfile(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new SwitchUserProfileRequest(this.service.getApplicationContext(), this.service.getConfiguration(), s, userAgentWebCallback));
    }
}
