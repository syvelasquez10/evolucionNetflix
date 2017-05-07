// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;

public class SocialNotificationsFriendProfile extends FriendProfile
{
    @SerializedName("image145x145")
    private String imageUrl;
    
    public SocialNotificationsFriendProfile(final String s, final String s2, final String s3, final String imageUrl) {
        super(s, s2, s3, imageUrl);
        this.imageUrl = imageUrl;
    }
    
    @Override
    public String getImageUrl() {
        return this.imageUrl;
    }
}
