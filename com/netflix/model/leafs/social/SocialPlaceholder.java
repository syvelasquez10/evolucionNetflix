// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.FriendProfilesProvider;

public abstract class SocialPlaceholder implements FriendProfilesProvider, Video
{
    private final LoMo lomo;
    
    public SocialPlaceholder(final LoMo lomo) {
        this.lomo = lomo;
    }
    
    @Override
    public String getBoxshotUrl() {
        return null;
    }
    
    @Override
    public VideoType getErrorType() {
        return null;
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return this.lomo.getFacebookFriends();
    }
    
    @Override
    public String getHorzDispUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getId() {
        return this.lomo.getId();
    }
    
    @Override
    public String getSquareUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getTitle() {
        return this.lomo.getTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        throw new RuntimeException("Not implemented");
    }
}
