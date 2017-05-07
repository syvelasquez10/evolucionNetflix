// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;

public class EpisodeDetails extends Episode implements com.netflix.mediaclient.servicemgr.EpisodeDetails
{
    private static final String TAG = "nf_service_browse_episodedetails";
    public Rating rating;
    public SocialEvidence showSocialEvidence;
    public boolean userConnectedToFacebook;
    
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
    
    public long getBookmarkCreationTime() {
        if (this.bookmark == null) {
            return -1L;
        }
        return this.bookmark.getLastModified();
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
    public String getCreators() {
        return null;
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
    public boolean getFbDntShare() {
        return this.userConnectedToFacebook && this.showSocialEvidence != null && !this.showSocialEvidence.isVideoHidden();
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
    public int getPlayableBookmarkPosition() {
        Log.d("nf_service_browse_episodedetails", String.format("id %s bookmark %d playPos %d endtime %d runtime %d", this.getId(), this.getBookmarkPosition(), BrowseAgent.getPlayablePosition(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime()), this.getEndtime(), this.getRuntime()));
        return BrowseAgent.getPlayablePosition(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
    }
    
    @Override
    public String getPlayableId() {
        return this.getId();
    }
    
    @Override
    public long getPlayableServerBookmarkUpdateTime() {
        return this.getBookmarkCreationTime();
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
    public boolean isInQueue() {
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return true;
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        return this.userConnectedToFacebook;
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
}
