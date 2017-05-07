// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

class DummyMdxVideoDetails implements EpisodeDetails
{
    @Override
    public String getActors() {
        return null;
    }
    
    @Override
    public String getAvailabilityDateMessage() {
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
    public String getBoxshotUrl() {
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
    public String getCopyright() {
        return "© 2015 Test";
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
    public String getHorzDispSmallUrl() {
        return "http://dummyimage.com/332x187/bb0000/884444.png&text=Horz+Disp+Img+Small";
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
    public String getNarrative() {
        return "Narrative";
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
    public Playable getPlayable() {
        return new DummyMdxVideoDetails$1(this);
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
    public String getSquareUrl() {
        return null;
    }
    
    @Override
    public String getStoryDispUrl() {
        return null;
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
        return "Synopsis";
    }
    
    @Override
    public String getTitle() {
        return "Dummy MDX Title, Super Extra-Long Version";
    }
    
    @Override
    public String getTitleImgUrl() {
        return null;
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
    public boolean isAvailableToStream() {
        return true;
    }
    
    @Override
    public boolean isInQueue() {
        return false;
    }
    
    @Override
    public boolean isVideo3D() {
        return false;
    }
    
    @Override
    public boolean isVideo5dot1() {
        return false;
    }
    
    @Override
    public boolean isVideoHd() {
        return true;
    }
    
    @Override
    public boolean isVideoUhd() {
        return false;
    }
    
    @Override
    public void setUserRating(final float n) {
    }
}
