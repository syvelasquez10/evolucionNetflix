// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

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
    public String getAdvisoryDescription() {
        return null;
    }
    
    @Override
    public int getAdvisoryDisplayDuration() {
        return 0;
    }
    
    @Override
    public String getAdvisoryRating() {
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
        return new Random().nextInt(100);
    }
    
    @Override
    public String getBoxshotUrl() {
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
    public String getCopyright() {
        return "© 2015 Test";
    }
    
    @Override
    public String getDefaultTrailer() {
        return null;
    }
    
    @Override
    public List<String> getEpisodeBadges() {
        return new ArrayList<String>(3);
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
    public long getExpirationTime() {
        return 0L;
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
    public String getInterestingSmallUrl() {
        return null;
    }
    
    @Override
    public String getInterestingUrl() {
        return null;
    }
    
    @Override
    public int getMaturityLevel() {
        return 0;
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
        return new DummyPlayable();
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
    public String getSeasonAbbrSeqLabel() {
        return null;
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
    public String getStoryDispUrl() {
        return null;
    }
    
    @Override
    public String getStoryUrl() {
        return null;
    }
    
    @Override
    public String getSupplementalMessage() {
        return null;
    }
    
    @Override
    public String getSynopsis() {
        return "Synopsis";
    }
    
    @Override
    public String getTitle() {
        return "Episode " + this.epNumber;
    }
    
    @Override
    public String getTitleImgUrl() {
        return null;
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
    public boolean hasTrailers() {
        return false;
    }
    
    @Override
    public boolean hasWatched() {
        return false;
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
    public boolean isNSRE() {
        return false;
    }
    
    @Override
    public boolean isOriginal() {
        return false;
    }
    
    @Override
    public boolean isPreRelease() {
        return false;
    }
    
    @Override
    public boolean isSupplementalVideo() {
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
    public boolean isVideoDolbyVision() {
        return false;
    }
    
    @Override
    public boolean isVideoHd() {
        return true;
    }
    
    @Override
    public boolean isVideoHdr10() {
        return false;
    }
    
    @Override
    public boolean isVideoUhd() {
        return false;
    }
    
    @Override
    public void setUserRating(final float n) {
    }
}
