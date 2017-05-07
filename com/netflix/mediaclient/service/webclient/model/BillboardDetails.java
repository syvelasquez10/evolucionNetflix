// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.branches.Billboard;

public class BillboardDetails extends Billboard implements ShowDetails, MovieDetails, Video
{
    private static final String TAG = "BillboardDetails";
    
    @Override
    public String getActors() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.actors;
    }
    
    @Override
    public String getBifUrl() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
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
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.restUrl;
    }
    
    @Override
    public String getCertification() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.certification;
    }
    
    @Override
    public String getCreators() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.directors;
    }
    
    @Override
    public String getCurrentEpisodeId() {
        return this.getId();
    }
    
    @Override
    public int getCurrentEpisodeNumber() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public String getCurrentEpisodeTitle() {
        return this.getTitle();
    }
    
    @Override
    public int getCurrentSeasonNumber() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public int getEndtime() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
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
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public boolean getFbDntShare() {
        return false;
    }
    
    @Override
    public String getGenres() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.genres;
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.mdxHorzUrl;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.mdxVertUrl;
    }
    
    @Override
    public String getHorzDispUrl() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.horzDispUrl;
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
        if (this.billboardDetail == null) {
            return -1;
        }
        return this.billboardDetail.episodeCount;
    }
    
    @Override
    public int getNumOfSeasons() {
        if (this.billboardDetail == null) {
            return -1;
        }
        return this.billboardDetail.seasonCount;
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
        return 0;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return 0L;
    }
    
    @Override
    public String getPlayableId() {
        if (this.getType() == VideoType.SHOW) {
            return this.getCurrentEpisodeId();
        }
        return this.getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (this.getType() == VideoType.SHOW) {
            return this.getCurrentEpisodeTitle();
        }
        return this.getTitle();
    }
    
    @Override
    public float getPredictedRating() {
        if (this.billboardDetail == null) {
            return -1.0f;
        }
        return this.billboardDetail.predictedRating;
    }
    
    @Override
    public String getQuality() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.quality;
    }
    
    @Override
    public int getRuntime() {
        if (this.billboardDetail == null) {
            return 0;
        }
        return this.billboardDetail.runtime;
    }
    
    @Override
    public int getSeasonNumber() {
        return -1;
    }
    
    @Override
    public List<Video> getSimilars() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public int getSimilarsListPos() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public String getSimilarsRequestId() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public int getSimilarsTrackId() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
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
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.synopsis;
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
        if (this.billboardDetail == null) {
            return null;
        }
        return this.billboardDetail.tvCardUrl;
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
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public int getYear() {
        if (this.billboardDetail == null) {
            return 0;
        }
        return this.billboardDetail.year;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.billboardDetail != null && this.billboardDetail.isAutoPlayEnabled;
    }
    
    @Override
    public boolean isInQueue() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.billboardDetail != null && this.billboardDetail.isAutoPlayEnabled;
    }
    
    @Override
    public boolean isPinProtected() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isShared() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public boolean isVideoHd() {
        return this.billboardDetail != null && this.billboardDetail.isHdAvailable;
    }
    
    @Override
    public void setUserRating(final float n) {
        throw new IllegalStateException("Not implemented by BillboardDetails");
    }
    
    @Override
    public String toString() {
        return "BillboardDetails [getTitle()=" + this.getTitle() + ", getId()=" + this.getId() + ", getSynopsis()=" + this.getSynopsis() + ", getQuality()=" + this.getQuality() + ", getActors()=" + this.getActors() + ", getGenres()=" + this.getGenres() + ", getCertification()=" + this.getCertification() + ", getCreators()=" + this.getCreators() + ", getPredictedRating()=" + this.getPredictedRating() + ", getYear()=" + this.getYear() + ", getBoxshotURL()=" + this.getBoxshotURL() + ", getType()=" + this.getType() + ", getNumOfEpisodes()=" + this.getNumOfEpisodes() + ", getNumOfSeasons()=" + this.getNumOfSeasons() + "]";
    }
}
