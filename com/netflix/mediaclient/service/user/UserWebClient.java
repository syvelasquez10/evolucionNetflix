// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.Set;

public interface UserWebClient
{
    void addWebUserProfile(final String p0, final boolean p1, final String p2, final UserAgentWebCallback p3);
    
    void autoLogin(final String p0, final UserAgentWebCallback p1);
    
    void connectWithFacebook(final String p0, final UserAgentWebCallback p1);
    
    void createAutoLoginToken(final long p0, final UserAgentWebCallback p1);
    
    void disconnectFromFacebook(final UserAgentWebCallback p0);
    
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    void editWebUserProfile(final String p0, final String p1, final boolean p2, final String p3, final UserAgentWebCallback p4);
    
    void fetchAccountData(final UserAgentWebCallback p0);
    
    void fetchAvailableAvatarsList(final UserAgentWebCallback p0);
    
    void fetchFriendsForRecommendations(final String p0, final int p1, final String p2, final UserAgentWebCallback p3);
    
    void fetchProfileData(final String p0, final UserAgentWebCallback p1);
    
    void fetchUserData(final UserAgentWebCallback p0);
    
    boolean isSynchronous();
    
    void removeWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void sendRecommendationsToFriends(final String p0, final Set<FriendForRecommendation> p1, final String p2, final String p3, final UserAgentWebCallback p4);
    
    void switchWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void verifyPin(final String p0, final UserAgentWebCallback p1);
}
