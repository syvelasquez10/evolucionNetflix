// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;

public interface UserAgent$UserAgentCallback
{
    void onAvailableAvatarsListFetched(final List<AvatarInfo> p0, final Status p1);
    
    void onConnectWithFacebook(final Status p0);
    
    void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> p0, final Status p1);
    
    void onLoginComplete(final Status p0);
    
    void onLogoutComplete(final Status p0);
    
    void onPinVerified(final boolean p0, final Status p1);
    
    void onProfilesListUpdateResult(final Status p0);
    
    void onSocialNotificationsListFetched(final SocialNotificationsList p0, final Status p1);
}
