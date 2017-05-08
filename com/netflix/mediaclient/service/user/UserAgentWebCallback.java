// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.model.survey.Survey;
import java.util.Set;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

public interface UserAgentWebCallback
{
    void onAccountDataFetched(final AccountData p0, final Status p1);
    
    void onAutoLoginTokenCreated(final String p0, final Status p1);
    
    void onAutologinCompleted(final ActivationTokens p0, final Status p1);
    
    void onAvatarsListFetched(final List<AvatarInfo> p0, final Status p1);
    
    void onDummyWebCallDone(final Status p0);
    
    void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> p0, final Status p1);
    
    void onProfileDataFetched(final UserProfile p0, final Status p1);
    
    void onRecommendationsSent(final Set<FriendForRecommendation> p0, final Status p1);
    
    void onSurveyFetched(final Survey p0, final Status p1);
    
    void onUserDataFetched(final User p0, final Status p1);
    
    void onUserProfileSwitched(final UserBoundCookies p0, final Status p1);
    
    void onUserProfilesUpdated(final AccountData p0, final Status p1);
    
    void onVerified(final boolean p0, final Status p1);
}
