// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.FriendProfile;
import com.netflix.mediaclient.servicemgr.VideoType;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;

public class DummyEpisodeDetails implements EpisodeDetails
{
    private final int epNumber;
    
    public DummyEpisodeDetails(final int epNumber) {
        this.epNumber = epNumber;
    }
    
    public static List<EpisodeDetails> createDummyArray() {
        final ArrayList<DummyEpisodeDetails> list = (ArrayList<DummyEpisodeDetails>)new ArrayList<EpisodeDetails>();
        for (int i = 0; i < 100; ++i) {
            list.add(new DummyEpisodeDetails(i));
        }
        return (List<EpisodeDetails>)list;
    }
    
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
        return new Random().nextInt(100);
    }
    
    @Override
    public String getBoxshotURL() {
        return null;
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
        return this.epNumber;
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
        return null;
    }
    
    @Override
    public String getId() {
        return String.valueOf(this.epNumber);
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
        return null;
    }
    
    @Override
    public String getParentTitle() {
        return null;
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
        return null;
    }
    
    @Override
    public String getPlayableTitle() {
        return null;
    }
    
    @Override
    public float getPredictedRating() {
        return 0.0f;
    }
    
    @Override
    public String getQuality() {
        return null;
    }
    
    @Override
    public int getRuntime() {
        return 100;
    }
    
    @Override
    public String getSeasonId() {
        return null;
    }
    
    @Override
    public int getSeasonNumber() {
        return 0;
    }
    
    @Override
    public String getShowId() {
        return null;
    }
    
    @Override
    public String getSquareUrl() {
        return null;
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
        return "GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB GKB";
    }
    
    @Override
    public String getTitle() {
        return "Episode " + this.epNumber;
    }
    
    @Override
    public String getTvCardUrl() {
        return null;
    }
    
    @Override
    public VideoType getType() {
        return null;
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
    public boolean isPinProtected() {
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return true;
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
