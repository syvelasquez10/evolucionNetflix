// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.List;

class UserAgent$FetchFriendsForRecommendationsTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$FetchFriendsForRecommendationsTask this$1;
    
    UserAgent$FetchFriendsForRecommendationsTask$1(final UserAgent$FetchFriendsForRecommendationsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
        this.this$1.mCallback.onFriendsForRecommendationsListFetched(list, status);
    }
}
