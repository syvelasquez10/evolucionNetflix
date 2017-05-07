// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
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
    public void addWebUserProfile(final String s, final boolean b, final String s2, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new AddUserProfileRequest(this.service.getApplicationContext(), s, b, s2, userAgentWebCallback));
    }
    
    @Override
    public void connectWithFacebook(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchConnectWithFacebookRequest(this.service.getApplicationContext(), s, userAgentWebCallback));
    }
    
    @Override
    public void disconnectFromFacebook(final UserAgentWebCallback userAgentWebCallback) {
        final NetflixStatus netflixStatus = new NetflixStatus(StatusCode.NOT_IMPLEMENTED);
        netflixStatus.setMessage("error : not implemented");
        userAgentWebCallback.onDisconnectWithFacebook(netflixStatus);
    }
    
    @Override
    public void doDummyWebCall(final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchDummyWebRequest(this.service.getApplicationContext(), userAgentWebCallback));
    }
    
    @Override
    public void editWebUserProfile(final String s, final String s2, final boolean b, final String s3, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new EditUserProfileRequest(this.service.getApplicationContext(), s, s2, b, s3, userAgentWebCallback));
    }
    
    @Override
    public void fetchAccountData(final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchAccountDataRequest(this.service.getApplicationContext(), userAgentWebCallback));
    }
    
    @Override
    public void fetchAvailableAvatarsList(final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchAvailableAvatarsRequest(this.service.getApplicationContext(), userAgentWebCallback));
    }
    
    @Override
    public void fetchFriendsForRecommendations(final String s, final int n, final String s2, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchFriendsForRecommendationRequest(this.service.getApplicationContext(), s, n, s2, userAgentWebCallback));
    }
    
    @Override
    public void fetchProfileData(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new FetchProfileDataRequest(this.service.getApplicationContext(), s, userAgentWebCallback));
    }
    
    @Override
    public final boolean isSynchronous() {
        return this.webclient.isSynchronous();
    }
    
    @Override
    public void removeWebUserProfile(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new RemoveUserProfileRequest(this.service.getApplicationContext(), s, userAgentWebCallback));
    }
    
    @Override
    public void sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2, final String s3, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new SendRecommendationRequest(this.service.getApplicationContext(), s, set, s2, s3, userAgentWebCallback));
    }
    
    @Override
    public void switchWebUserProfile(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new SwitchUserProfileRequest(this.service.getApplicationContext(), s, userAgentWebCallback));
    }
    
    @Override
    public void verifyPin(final String s, final UserAgentWebCallback userAgentWebCallback) {
        this.webclient.sendRequest(new VerifyPinRequest(this.service.getApplicationContext(), s, userAgentWebCallback));
    }
}
