// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;

public class SocialEvidence implements FriendProfilesProvider
{
    private List<FriendProfile> facebookFriends;
    private boolean isVideoHidden;
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return this.facebookFriends;
    }
    
    public boolean isVideoHidden() {
        return this.isVideoHidden;
    }
    
    public void setVideoHidden(final boolean isVideoHidden) {
        this.isVideoHidden = isVideoHidden;
    }
}
