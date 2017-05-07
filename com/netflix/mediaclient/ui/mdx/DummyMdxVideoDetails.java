// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;

class DummyMdxVideoDetails implements EpisodeDetails
{
    @Override
    public String getActors() {
        return null;
    }
    
    @Override
    public String getBifUrl() {
        return null;
    }
    
    @Override
    public int getBookmarkPosition() {
        return 0;
    }
    
    @Override
    public String getBoxshotURL() {
        return "http://dummyimage.com/150x214/bb0000/884444.png&text=Boxshot+Img";
    }
    
    @Override
    public String getCatalogIdUrl() {
        return null;
    }
    
    @Override
    public String getCertification() {
        return null;
    }
    
    @Override
    public String getCreators() {
        return null;
    }
    
    @Override
    public int getEndtime() {
        return 0;
    }
    
    @Override
    public String getEpisodeIdUrl() {
        return null;
    }
    
    @Override
    public int getEpisodeNumber() {
        return 6;
    }
    
    @Override
    public VideoType getErrorType() {
        return null;
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return null;
    }
    
    @Override
    public boolean getFbDntShare() {
        return false;
    }
    
    @Override
    public String getGenres() {
        return null;
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        return null;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        return null;
    }
    
    @Override
    public String getHorzDispUrl() {
        return "http://dummyimage.com/665x375/bb0000/884444.png&text=Horz+Disp+Img";
    }
    
    @Override
    public String getId() {
        return "70178217";
    }
    
    @Override
    public String getInterestingUrl() {
        return null;
    }
    
    @Override
    public String getNextEpisodeId() {
        return null;
    }
    
    @Override
    public String getNextEpisodeTitle() {
        return null;
    }
    
    @Override
    public String getParentId() {
        return "70178217";
    }
    
    @Override
    public String getParentTitle() {
        return "Dummy parent title - extra long version of the string to test textView ellipsize functionality";
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        return 0;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return 0L;
    }
    
    @Override
    public String getPlayableId() {
        return "70178217";
    }
    
    @Override
    public String getPlayableTitle() {
        return "Dummy playable title";
    }
    
    @Override
    public float getPredictedRating() {
        return 3.5f;
    }
    
    @Override
    public String getQuality() {
        return null;
    }
    
    @Override
    public int getRuntime() {
        return 4980;
    }
    
    @Override
    public String getSeasonId() {
        return "70178217";
    }
    
    @Override
    public int getSeasonNumber() {
        return 5;
    }
    
    @Override
    public String getShowId() {
        return "70248301";
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
        return null;
    }
    
    @Override
    public String getTitle() {
        return "Dummy MDX Title, Super Extra-Long Version";
    }
    
    @Override
    public String getTvCardUrl() {
        return "http://dummyimage.com/265x149/0000ff/ffffff.png&text=TV+Card+Img";
    }
    
    @Override
    public VideoType getType() {
        return VideoType.EPISODE;
    }
    
    @Override
    public float getUserRating() {
        return 0.0f;
    }
    
    @Override
    public int getYear() {
        return 0;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return false;
    }
    
    @Override
    public boolean isInQueue() {
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        return false;
    }
    
    @Override
    public boolean isVideoHd() {
        return true;
    }
    
    @Override
    public void setUserRating(final float n) {
    }
}
