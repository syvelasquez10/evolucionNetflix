// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.service.webclient.model.branches.Movie;

public class MovieDetails extends Movie implements Playable, com.netflix.mediaclient.servicemgr.model.details.MovieDetails
{
    private static final String TAG = "nf_service_browse_moviedetails";
    public boolean userConnectedToFacebook;
    
    @Override
    public boolean canBeSharedOnFacebook() {
        if (Log.isLoggable("nf_service_browse_moviedetails", 2)) {
            final boolean userConnectedToFacebook = this.userConnectedToFacebook;
            Serializable value;
            if (this.socialEvidence == null) {
                value = "n/a";
            }
            else {
                value = this.socialEvidence.isVideoHidden();
            }
            Log.v("nf_service_browse_moviedetails", String.format("userConnectedToFacebook: %s, isVideoHidden(): %s", userConnectedToFacebook, value));
        }
        return this.userConnectedToFacebook && this.socialEvidence != null && !this.socialEvidence.isVideoHidden();
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
    
    public int getBookmarkPosition() {
        if (this.bookmark == null) {
            return 0;
        }
        return this.bookmark.getBookmarkPosition();
    }
    
    @Override
    public String getBoxshotURL() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxshotURL();
    }
    
    @Override
    public String getCatalogIdUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.restUrl;
    }
    
    @Override
    public String getCertification() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.certification;
    }
    
    @Override
    public String getDirectors() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.directors;
    }
    
    @Override
    public int getEndtime() {
        if (this.detail == null) {
            return 0;
        }
        return this.detail.endtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        return -1;
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getErrorType();
    }
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        if (this.socialEvidence == null) {
            return null;
        }
        return this.socialEvidence.getFacebookFriends();
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
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public int getNumDirectors() {
        return StringUtils.getCsvCount(this.getDirectors());
    }
    
    @Override
    public String getParentId() {
        return this.getId();
    }
    
    @Override
    public String getParentTitle() {
        return null;
    }
    
    @Override
    public Playable getPlayable() {
        return this;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        final int computePlayPos = BrowseAgent.computePlayPos(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
        if (Log.isLoggable("nf_service_browse_moviedetails", 3)) {
            Log.d("nf_service_browse_moviedetails", String.format("id %s bookmark %d playPos %d endtime %d runtime %d", this.getId(), this.getBookmarkPosition(), computePlayPos, this.getEndtime(), this.getRuntime()));
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
    public int getSeasonNumber() {
        return -1;
    }
    
    @Override
    public List<Video> getSimilars() {
        return this.similarVideos;
    }
    
    @Override
    public int getSimilarsListPos() {
        return 0;
    }
    
    @Override
    public String getSimilarsRequestId() {
        if (this.similarListSummary != null) {
            return this.similarListSummary.getRequestId();
        }
        return null;
    }
    
    @Override
    public int getSimilarsTrackId() {
        if (this.similarListSummary != null) {
            return this.similarListSummary.getTrackId();
        }
        return 0;
    }
    
    @Override
    public String getSquareUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getSquareUrl();
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
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTitle();
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
        if (this.summary == null) {
            return null;
        }
        return this.summary.getType();
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
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.detail != null && this.detail.isAutoPlayEnabled;
    }
    
    @Override
    public boolean isInQueue() {
        return this.inQueue != null && this.inQueue.inQueue;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.detail != null && this.detail.isNextPlayableEpisode;
    }
    
    @Override
    public boolean isPinProtected() {
        return this.detail != null && this.detail.isPinProtected;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return VideoType.EPISODE.equals(this.getType());
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
        this.rating.userRating = userRating;
    }
}
