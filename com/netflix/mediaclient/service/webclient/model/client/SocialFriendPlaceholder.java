// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.client;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.LoMo;

public class SocialFriendPlaceholder extends SocialPlaceholder
{
    public SocialFriendPlaceholder(final LoMo loMo) {
        super(loMo);
    }
    
    @Override
    public Playable getPlayable() {
        return null;
    }
    
    @Override
    public String getTitle() {
        if (this.getFacebookFriends() != null && this.getFacebookFriends().size() > 0) {
            return this.getFacebookFriends().get(0).getFullName();
        }
        return "";
    }
    
    @Override
    public VideoType getType() {
        return VideoType.SOCIAL_FRIEND;
    }
}
