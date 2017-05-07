// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;

public class EpisodeDetails extends Episode implements com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails, Playable
{
    private static final String TAG = "EpisodeDetails";
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Rating rating;
    public SocialEvidence showSocialEvidence;
    public boolean userConnectedToFacebook;
    
    @Override
    public boolean canBeSharedOnFacebook() {
        return this.userConnectedToFacebook && this.showSocialEvidence != null && !this.showSocialEvidence.isVideoHidden();
    }
    
    @Override
    public String getActors() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.actors;
    }
    
    public String getBaseUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.baseUrl;
    }
    
    @Override
    public String getBifUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.bifUrl;
    }
    
    @Override
    public int getBookmarkPosition() {
        if (this.bookmark == null) {
            return 0;
        }
        return this.bookmark.getBookmarkPosition();
    }
    
    @Override
    public String getBoxshotURL() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getBoxshotURL();
    }
    
    @Override
    public String getCatalogIdUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getShowRestUrl();
    }
    
    @Override
    public String getCertification() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.certification;
    }
    
    @Override
    public int getEndtime() {
        if (this.detail == null) {
            return 0;
        }
        return this.detail.endtime;
    }
    
    @Override
    public String getEpisodeIdUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.restUrl;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (this.detail == null) {
            return -1;
        }
        return this.detail.getEpisodeNumber();
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getErrorType();
    }
    
    @Override
    public String getGenres() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.genres;
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.mdxHorzUrl;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.mdxVertUrl;
    }
    
    @Override
    public String getHorzDispUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.horzDispUrl;
    }
    
    @Override
    public String getId() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getId();
    }
    
    @Override
    public String getInterestingUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getInterestingUrl();
    }
    
    @Override
    public String getNextEpisodeId() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getNextEpisodeId();
    }
    
    @Override
    public String getNextEpisodeTitle() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getNextEpisodeTitle();
    }
    
    @Override
    public String getParentId() {
        return this.getShowId();
    }
    
    @Override
    public String getParentTitle() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getShowTitle();
    }
    
    @Override
    public Playable getPlayable() {
        return this;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        final int computePlayPos = BrowseAgent.computePlayPos(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
        if (Log.isLoggable("EpisodeDetails", 3)) {
            Log.d("EpisodeDetails", String.format("id %s bookmark %d playPos %d endtime %d runtime %d", this.getId(), this.getBookmarkPosition(), computePlayPos, this.getEndtime(), this.getRuntime()));
        }
        return computePlayPos;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        if (this.bookmark == null) {
            return -1L;
        }
        return this.bookmark.getLastModified();
    }
    
    @Override
    public String getPlayableId() {
        return this.getId();
    }
    
    @Override
    public String getPlayableTitle() {
        return this.getTitle();
    }
    
    @Override
    public float getPredictedRating() {
        if (this.detail == null) {
            return -1.0f;
        }
        return this.detail.predictedRating;
    }
    
    @Override
    public String getQuality() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.quality;
    }
    
    @Override
    public int getRuntime() {
        if (this.detail == null) {
            return 0;
        }
        return this.detail.runtime;
    }
    
    @Override
    public String getSeasonId() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getSeasonId();
    }
    
    @Override
    public int getSeasonNumber() {
        if (this.detail == null) {
            return -1;
        }
        return this.detail.getSeasonNumber();
    }
    
    @Override
    public String getShowId() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getShowId();
    }
    
    @Override
    public String getSquareUrl() {
        return null;
    }
    
    @Override
    public String getStoryDispUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.storyImgDispUrl;
    }
    
    @Override
    public String getStoryUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.synopsis;
    }
    
    @Override
    public String getTitle() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.tvCardUrl;
    }
    
    @Override
    public VideoType getType() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.getType();
    }
    
    @Override
    public float getUserRating() {
        if (this.rating == null) {
            return -1.0f;
        }
        return this.rating.userRating;
    }
    
    @Override
    public int getYear() {
        if (this.detail == null) {
            return 0;
        }
        return this.detail.year;
    }
    
    public boolean hasSameSeasonAndEpisodeNumbers(final EpisodeDetails episodeDetails) {
        if (this.getSeasonNumber() != episodeDetails.getSeasonNumber()) {
            Log.v("EpisodeDetails", String.format("Season number does not match: %d, %d", this.getSeasonNumber(), episodeDetails.getSeasonNumber()));
            return false;
        }
        if (this.getEpisodeNumber() != episodeDetails.getEpisodeNumber()) {
            Log.v("EpisodeDetails", String.format("Episode number does not match: %d, %d", this.getEpisodeNumber(), episodeDetails.getEpisodeNumber()));
            return false;
        }
        Log.v("EpisodeDetails", String.format("Episode and season numbers match: s%d, e%d", this.getSeasonNumber(), this.getEpisodeNumber()));
        return true;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.detail != null && this.detail.isAutoPlayEnabled();
    }
    
    @Override
    public boolean isInQueue() {
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.detail != null && this.detail.isNextPlayableEpisode();
    }
    
    @Override
    public boolean isPinProtected() {
        return this.detail != null && this.detail.isPinProtected;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return true;
    }
    
    @Override
    public boolean isVideoHd() {
        return this.detail != null && this.detail.isHdAvailable;
    }
    
    public void setBookmarkPosition(final int bookmarkPosition) {
        if (this.bookmark != null) {
            this.bookmark.setBookmarkPosition(bookmarkPosition);
        }
    }
    
    @Override
    public void setUserRating(final float userRating) {
        if (this.rating != null) {
            this.rating.userRating = userRating;
        }
    }
    
    @Override
    public String toString() {
        return "EpisodeDetails [detail=" + this.detail + ", bookmark=" + this.bookmark + "]";
    }
}
