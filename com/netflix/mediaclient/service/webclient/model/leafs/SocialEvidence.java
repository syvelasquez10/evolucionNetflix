// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;

public class SocialEvidence
{
    private List<FriendProfile> facebookFriends;
    private boolean isVideoHidden;
    
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
