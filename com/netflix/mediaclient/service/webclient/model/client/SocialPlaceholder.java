// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.client;

import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.VideoDetails;

public abstract class SocialPlaceholder implements VideoDetails
{
    private final LoMo lomo;
    
    public SocialPlaceholder(final LoMo lomo) {
        this.lomo = lomo;
    }
    
    @Override
    public String getActors() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getBifUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    public long getBookmarkCreationTime() {
        throw new RuntimeException("Not implemented");
    }
    
    public int getBookmarkPosition() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getBoxshotURL() {
        return null;
    }
    
    @Override
    public String getCatalogIdUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getCertification() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getCreators() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getEndtime() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getEpisodeNumber() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public VideoType getErrorType() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return this.lomo.getFacebookFriends();
    }
    
    @Override
    public boolean getFbDntShare() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getGenres() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getHorzDispUrl() {
        return null;
    }
    
    @Override
    public String getId() {
        return this.lomo.getId();
    }
    
    @Override
    public String getParentId() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getParentTitle() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getPlayableId() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getPlayableTitle() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public float getPredictedRating() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getQuality() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getRuntime() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getSeasonNumber() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
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
    
    @Override
    public VideoType getType() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public float getUserRating() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public int getYear() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isInQueue() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isPlayableEpisode() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public boolean isVideoHd() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public void setUserRating(final float n) {
        throw new RuntimeException("Not implemented");
    }
}
