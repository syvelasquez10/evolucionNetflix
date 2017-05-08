// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.leafs.DiscoverySummary;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;

public class DiscoveryRecord implements Discovery
{
    DiscoverySummary pivot1Summary;
    DiscoverySummary pivot2Summary;
    FalkorVideo video;
    
    public DiscoveryRecord(final FalkorVideo video, final DiscoverySummary pivot1Summary, final DiscoverySummary pivot2Summary) {
        this.video = video;
        this.pivot1Summary = pivot1Summary;
        this.pivot2Summary = pivot2Summary;
    }
    
    @Override
    public List<Advisory> getAdvisories() {
        return (List<Advisory>)this.video.getAdvisories();
    }
    
    @Override
    public int getAutoPlayMaxCount() {
        return this.video.getAutoPlayMaxCount();
    }
    
    @Override
    public String getBoxartImageTypeIdentifier() {
        return this.video.getBoxartImageTypeIdentifier();
    }
    
    @Override
    public String getBoxshotUrl() {
        return this.video.getBoxshotUrl();
    }
    
    @Override
    public int getEndtime() {
        return this.video.getEndtime();
    }
    
    @Override
    public int getEpisodeNumber() {
        return this.video.getEpisodeNumber();
    }
    
    @Override
    public VideoType getErrorType() {
        return this.video.getErrorType();
    }
    
    @Override
    public long getExpirationTime() {
        return this.video.getExpirationTime();
    }
    
    @Override
    public FalkorVideo getFalkorVideo() {
        return this.video;
    }
    
    @Override
    public String getHorzDispSmallUrl() {
        return this.video.getHorzDispSmallUrl();
    }
    
    @Override
    public String getHorzDispUrl() {
        return this.video.getHorzDispUrl();
    }
    
    @Override
    public String getId() {
        return this.video.getId();
    }
    
    @Override
    public int getLogicalStart() {
        return this.video.getLogicalStart();
    }
    
    @Override
    public String getParentId() {
        return this.video.getParentId();
    }
    
    @Override
    public String getParentTitle() {
        return this.video.getParentTitle();
    }
    
    @Override
    public String getPivot1BoxartUrl() {
        return this.pivot1Summary.getPivotBoxartUrl();
    }
    
    @Override
    public long getPivot1CollectionId() {
        return this.pivot1Summary.getPivotCollectionId();
    }
    
    @Override
    public String getPivot1Title() {
        return this.pivot1Summary.getPivotTitle();
    }
    
    @Override
    public int getPivot1TrackId() {
        return this.pivot1Summary.getTrackId();
    }
    
    @Override
    public String getPivot2BoxartUrl() {
        return this.pivot2Summary.getPivotBoxartUrl();
    }
    
    @Override
    public long getPivot2CollectionId() {
        return this.pivot2Summary.getPivotCollectionId();
    }
    
    @Override
    public String getPivot2Title() {
        return this.pivot2Summary.getPivotTitle();
    }
    
    @Override
    public int getPivot2TrackId() {
        return this.pivot2Summary.getTrackId();
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        return this.video.getPlayableBookmarkPosition();
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return this.video.getPlayableBookmarkUpdateTime();
    }
    
    @Override
    public String getPlayableId() {
        return this.video.getPlayableId();
    }
    
    @Override
    public String getPlayableTitle() {
        return this.video.getPlayableTitle();
    }
    
    @Override
    public int getRuntime() {
        return this.video.getRuntime();
    }
    
    @Override
    public String getSeasonAbbrSeqLabel() {
        return this.video.getSeasonAbbrSeqLabel();
    }
    
    @Override
    public int getSeasonNumber() {
        return this.video.getSeasonNumber();
    }
    
    @Override
    public String getStoryDispUrl() {
        return this.video.getStoryDispUrl();
    }
    
    @Override
    public String getTitle() {
        return this.video.getTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        return this.video.getTvCardUrl();
    }
    
    @Override
    public VideoType getType() {
        return this.video.getType();
    }
    
    @Override
    public String getVertStoryArtUrl() {
        return this.video.getVerticalStoryArtUrl();
    }
    
    public boolean hasWatched() {
        return this.video.hasWatched();
    }
    
    @Override
    public boolean isAdvisoryDisabled() {
        return this.video.isAdvisoryDisabled();
    }
    
    @Override
    public boolean isAgeProtected() {
        return this.video.isAgeProtected();
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.video.isAutoPlayEnabled();
    }
    
    @Override
    public boolean isAvailableOffline() {
        return this.video.isAvailableOffline();
    }
    
    @Override
    public boolean isAvailableToStream() {
        return this.video.isAvailableToStream();
    }
    
    @Override
    public boolean isExemptFromInterrupterLimit() {
        return this.video.isExemptFromInterrupterLimit();
    }
    
    @Override
    public boolean isNSRE() {
        return this.video.isNSRE();
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.video.isNextPlayableEpisode();
    }
    
    @Override
    public boolean isOriginal() {
        return this.video.isOriginal();
    }
    
    @Override
    public boolean isPinProtected() {
        return this.video.isPinProtected();
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.video.isPlayableEpisode();
    }
    
    @Override
    public boolean isPreRelease() {
        return this.video.isPreRelease();
    }
    
    @Override
    public boolean isSupplementalVideo() {
        return this.video.isSupplementalVideo();
    }
}
