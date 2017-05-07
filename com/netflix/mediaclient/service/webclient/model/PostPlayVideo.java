// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.servicemgr.model.Playable;

public class PostPlayVideo implements com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo, Playable
{
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Bookmark bookmark;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Detail detail;
    public Episode.Detail episodeDetail;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.InQueue inQueue;
    public PostPlayContext postplayContext;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Rating rating;
    public SocialEvidence socialEvidence;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Summary summary;
    public boolean userConnectedToFacebook;
    
    private String getSynopsisNarrative(final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail detail) {
        if (detail.synopsisNarrative != null) {
            return detail.synopsisNarrative;
        }
        return detail.synopsis;
    }
    
    @Override
    public boolean canBeSharedOnFacebook() {
        return this.userConnectedToFacebook && this.socialEvidence != null && !this.socialEvidence.isVideoHidden();
    }
    
    @Override
    public String getActors() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.directors;
    }
    
    @Override
    public String getBifUrl() {
        if (VideoType.MOVIE.equals(this.getType())) {
            if (this.detail != null) {
                return this.detail.bifUrl;
            }
        }
        else if (this.episodeDetail != null) {
            return this.episodeDetail.bifUrl;
        }
        return null;
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
        if (VideoType.MOVIE.equals(this.getType())) {
            if (this.detail != null) {
                return this.detail.restUrl;
            }
        }
        else if (this.episodeDetail != null) {
            return this.episodeDetail.restUrl;
        }
        return null;
    }
    
    @Override
    public String getCertification() {
        if (VideoType.EPISODE.equals(this.getType()) || this.detail == null) {
            return null;
        }
        return this.detail.certification;
    }
    
    @Override
    public int getEndtime() {
        if (VideoType.MOVIE.equals(this.getType())) {
            if (this.detail != null) {
                return this.detail.endtime;
            }
        }
        else if (this.episodeDetail != null) {
            return this.episodeDetail.endtime;
        }
        return 0;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (this.episodeDetail == null) {
            return 0;
        }
        return this.episodeDetail.getEpisodeNumber();
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
        return null;
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
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.mdxHorzUrl;
            }
        }
        else if (this.detail != null) {
            return this.detail.mdxHorzUrl;
        }
        return null;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.mdxVertUrl;
            }
        }
        else if (this.detail != null) {
            return this.detail.mdxVertUrl;
        }
        return null;
    }
    
    @Override
    public String getHorzDispUrl() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.horzDispUrl;
            }
        }
        else if (this.detail != null) {
            return this.detail.horzDispUrl;
        }
        return null;
    }
    
    @Override
    public String getId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public String getInterestingUrl() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.getInterestingUrl();
            }
        }
        else if (this.detail != null) {
            return this.detail.storyImgUrl;
        }
        return null;
    }
    
    @Override
    public String getParentId() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowId();
    }
    
    @Override
    public String getParentTitle() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowTitle();
    }
    
    @Override
    public Playable getPlayable() {
        return this;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        int bookmarkPosition;
        if (this.bookmark == null) {
            bookmarkPosition = -1;
        }
        else {
            bookmarkPosition = this.bookmark.getBookmarkPosition();
        }
        return BrowseAgent.computePlayPos(bookmarkPosition, this.getEndtime(), this.getRuntime());
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        if (this.bookmark == null) {
            return 0L;
        }
        return this.bookmark.getLastModified();
    }
    
    @Override
    public String getPlayableId() {
        if (!VideoType.SHOW.equals(this.getType())) {
            return this.getId();
        }
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.getTitle();
        }
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getTitle();
    }
    
    @Override
    public String getPostPlayRequestId() {
        if (this.postplayContext == null) {
            return null;
        }
        return this.postplayContext.requestId;
    }
    
    @Override
    public int getPostPlayTrackId() {
        if (this.postplayContext == null) {
            return 0;
        }
        return this.postplayContext.trackId;
    }
    
    @Override
    public float getPredictedRating() {
        if (VideoType.EPISODE.equals(this.getType()) || this.detail == null) {
            return 0.0f;
        }
        return this.detail.predictedRating;
    }
    
    @Override
    public String getQuality() {
        return null;
    }
    
    @Override
    public int getRuntime() {
        if (VideoType.MOVIE.equals(this.getType())) {
            if (this.detail != null) {
                return this.detail.endtime;
            }
        }
        else if (this.episodeDetail != null) {
            return this.episodeDetail.endtime;
        }
        return 0;
    }
    
    @Override
    public int getSeasonNumber() {
        if (this.episodeDetail == null) {
            return 0;
        }
        return this.episodeDetail.getSeasonNumber();
    }
    
    @Override
    public String getSquareUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getSquareUrl();
    }
    
    @Override
    public String getStoryUrl() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.storyImgUrl;
            }
        }
        else if (this.detail != null) {
            return this.detail.storyImgUrl;
        }
        return null;
    }
    
    @Override
    public String getSynopsis() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.synopsis;
            }
        }
        else if (this.detail != null) {
            return this.getSynopsisNarrative(this.detail);
        }
        return null;
    }
    
    @Override
    public String getTitle() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.getTitle();
            }
        }
        else if (this.summary != null) {
            return this.summary.getTitle();
        }
        return null;
    }
    
    @Override
    public String getTvCardUrl() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.tvCardUrl;
            }
        }
        else if (this.detail != null) {
            return this.detail.tvCardUrl;
        }
        return null;
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
        if (VideoType.EPISODE.equals(this.getType()) || this.rating == null) {
            return 0.0f;
        }
        return this.rating.userRating;
    }
    
    @Override
    public int getYear() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.year;
            }
        }
        else if (this.detail != null) {
            return this.detail.year;
        }
        return 0;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        if (VideoType.MOVIE.equals(this.getType())) {
            if (this.detail != null) {
                return this.detail.isAutoPlayEnabled;
            }
        }
        else if (this.episodeDetail != null) {
            return this.episodeDetail.isAutoPlayEnabled();
        }
        return false;
    }
    
    @Override
    public boolean isInQueue() {
        return this.inQueue != null && this.inQueue.inQueue;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return !VideoType.MOVIE.equals(this.getType()) && this.episodeDetail != null && this.episodeDetail.isNextPlayableEpisode();
    }
    
    @Override
    public boolean isPinProtected() {
        if (VideoType.EPISODE.equals(this.getType())) {
            if (this.episodeDetail != null) {
                return this.episodeDetail.isPinProtected;
            }
        }
        else if (this.detail != null) {
            return this.detail.isPinProtected;
        }
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return !VideoType.MOVIE.equals(this.getType());
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        return this.userConnectedToFacebook;
    }
    
    @Override
    public boolean isVideoHd() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.detail.isHdAvailable;
        }
        return this.episodeDetail != null && this.episodeDetail.isHdAvailable;
    }
    
    @Override
    public void setUserRating(final float n) {
    }
    
    public class PostPlayContext
    {
        String requestId;
        int trackId;
    }
}
