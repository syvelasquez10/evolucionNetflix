// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import java.util.Set;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

public abstract class SimpleUserAgentWebCallback implements UserAgentWebCallback
{
    @Override
    public void onAccountDataFetched(final AccountData accountData, final Status status) {
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
    }
    
    @Override
    public void onAutologinCompleted(final ActivationTokens activationTokens, final Status status) {
    }
    
    @Override
    public void onAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
    }
    
    @Override
    public void onDummyWebCallDone(final Status status) {
    }
    
    @Override
    public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
    }
    
    @Override
    public void onProfileDataFetched(final UserProfile userProfile, final Status status) {
    }
    
    @Override
    public void onRecommendationsSent(final Set<FriendForRecommendation> set, final Status status) {
    }
    
    @Override
    public void onUserDataFetched(final User user, final Status status) {
    }
    
    @Override
    public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final Status status) {
    }
    
    @Override
    public void onUserProfilesUpdated(final AccountData accountData, final Status status) {
    }
    
    @Override
    public void onVerified(final boolean b, final Status status) {
    }
}
