// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.branches.Show;

public class ShowDetails extends Show implements com.netflix.mediaclient.servicemgr.ShowDetails
{
    private static final String TAG = "nf_service_browse_showdetails";
    public Episode.Detail currentEpisode;
    public Bookmark currentEpisodeBookmark;
    public boolean userConnectedToFacebook;
    
    @Override
    public String getActors() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.actors;
    }
    
    @Override
    public String getBifUrl() {
        if (this.currentEpisode == null) {
            return null;
        }
        return this.currentEpisode.bifUrl;
    }
    
    public long getBookmarkCreationTime() {
        if (this.currentEpisodeBookmark == null) {
            return -1L;
        }
        return this.currentEpisodeBookmark.getLastModified();
    }
    
    public int getBookmarkPosition() {
        if (this.currentEpisodeBookmark == null) {
            return 0;
        }
        return this.currentEpisodeBookmark.getBookmarkPosition();
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
    public String getCreators() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.directors;
    }
    
    @Override
    public String getCurrentEpisodeId() {
        if (this.currentEpisode == null) {
            return "null";
        }
        return this.currentEpisode.getId();
    }
    
    @Override
    public int getCurrentEpisodeNumber() {
        if (this.currentEpisode == null) {
            return -1;
        }
        return this.currentEpisode.getEpisodeNumber();
    }
    
    @Override
    public String getCurrentEpisodeTitle() {
        if (this.currentEpisode == null) {
            return "null";
        }
        return this.currentEpisode.getTitle();
    }
    
    @Override
    public int getCurrentSeasonNumber() {
        if (this.currentEpisode == null) {
            return -1;
        }
        return this.currentEpisode.getSeasonNumber();
    }
    
    @Override
    public int getEndtime() {
        if (this.currentEpisode == null) {
            return 0;
        }
        return this.currentEpisode.endtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (this.currentEpisode == null) {
            return 0;
        }
        return this.currentEpisode.getEpisodeNumber();
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
    public boolean getFbDntShare() {
        return this.userConnectedToFacebook && this.socialEvidence != null && !this.socialEvidence.isVideoHidden();
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
        if (this.summary == null) {
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
    public int getNumOfEpisodes() {
        if (this.detail == null) {
            return -1;
        }
        return this.detail.episodeCount;
    }
    
    @Override
    public int getNumOfSeasons() {
        if (this.detail == null) {
            return -1;
        }
        return this.detail.seasonCount;
    }
    
    @Override
    public String getParentId() {
        return this.getId();
    }
    
    @Override
    public String getParentTitle() {
        return this.getTitle();
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        final int computePlayPos = BrowseAgent.computePlayPos(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
        if (Log.isLoggable("nf_service_browse_showdetails", 3)) {
            Log.d("nf_service_browse_showdetails", String.format("id %s bookmark %d playPos %d endtime %d runtime %d", this.getId(), this.getBookmarkPosition(), computePlayPos, this.getEndtime(), this.getRuntime()));
        }
        return computePlayPos;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return this.getBookmarkCreationTime();
    }
    
    @Override
    public String getPlayableId() {
        return this.getCurrentEpisodeId();
    }
    
    @Override
    public String getPlayableTitle() {
        return this.getCurrentEpisodeTitle();
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
        if (this.currentEpisode == null) {
            return 0;
        }
        return this.currentEpisode.runtime;
    }
    
    @Override
    public int getSeasonNumber() {
        if (this.currentEpisode == null) {
            return 0;
        }
        return this.currentEpisode.getSeasonNumber();
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
        return this.currentEpisode != null && this.currentEpisode.isAutoPlayEnabled();
    }
    
    @Override
    public boolean isInQueue() {
        return this.inQueue != null && this.inQueue.inQueue;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.currentEpisode != null && this.currentEpisode.isNextPlayableEpisode();
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.currentEpisode != null;
    }
    
    @Override
    public boolean isShared() {
        return this.userConnectedToFacebook && this.socialEvidence != null && !this.socialEvidence.isVideoHidden() && this.getPlayableBookmarkPosition() > 0;
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
        this.rating.userRating = userRating;
    }
}
