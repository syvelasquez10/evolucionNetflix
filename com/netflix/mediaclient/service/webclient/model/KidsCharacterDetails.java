// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.branches.KidsCharacter;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;

public class KidsCharacterDetails implements com.netflix.mediaclient.servicemgr.KidsCharacterDetails
{
    private static final String TAG = "nf_kidscharacter";
    public TrackableListSummary galleryListSummary;
    public List<Video> galleryVideos;
    public KidsCharacter.KidsDetail kidsDetail;
    public KidsCharacter.KidsSummary kidsSummary;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Bookmark watchNextBookmark;
    public Episode.Detail watchNextEpisodeDetail;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextMovieDetail;
    public com.netflix.mediaclient.service.webclient.model.branches.Video.Summary watchNextSummary;
    
    private String getCharacterSquareUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return ((com.netflix.mediaclient.service.webclient.model.branches.Video.Summary)this.kidsSummary).getSquareUrl();
    }
    
    private boolean getHasWatchedRecently() {
        return this.kidsDetail != null && this.kidsDetail.hasWatchedRecently;
    }
    
    private com.netflix.mediaclient.service.webclient.model.branches.Video.Detail getWatchNextDetails() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.watchNextMovieDetail;
        }
        if (VideoType.EPISODE.equals(this.getType())) {
            return this.watchNextEpisodeDetail;
        }
        throw new IllegalStateException("Invalid details type for watch next: " + this.getType());
    }
    
    private String getWatchNextSquareUrl() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getSquareUrl();
    }
    
    private Boolean isFirstPlay() {
        boolean b = true;
        if (this.kidsDetail == null) {
            return true;
        }
        if (this.getHasWatchedRecently() || (VideoType.EPISODE.equals(this.getType()) && this.getEpisodeNumber() != 1) || this.getPlayableBookmarkPosition() > 0) {
            b = false;
        }
        return b;
    }
    
    @Override
    public String getBifUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.bifUrl;
    }
    
    @Override
    public String getBoxshotURL() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getBoxshotURL();
    }
    
    @Override
    public String getCatalogIdUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.restUrl;
    }
    
    @Override
    public String getCharacterId() {
        if (this.kidsSummary == null) {
            return null;
        }
        return ((com.netflix.mediaclient.service.webclient.model.branches.Video.Summary)this.kidsSummary).getId();
    }
    
    @Override
    public String getCharacterName() {
        if (this.kidsSummary == null) {
            return null;
        }
        return ((com.netflix.mediaclient.service.webclient.model.branches.Video.Summary)this.kidsSummary).getTitle();
    }
    
    @Override
    public String getCharacterSynopsis() {
        if (this.kidsDetail == null) {
            return null;
        }
        return this.kidsDetail.synopsis;
    }
    
    @Override
    public int getEndtime() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        Integer value;
        if (watchNextDetails == null) {
            value = null;
        }
        else {
            value = watchNextDetails.endtime;
        }
        return value;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (!VideoType.EPISODE.equals(this.getType()) || this.watchNextEpisodeDetail == null) {
            return 0;
        }
        return this.watchNextEpisodeDetail.getEpisodeNumber();
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.watchNextSummary == null) {
            return VideoType.UNKNOWN;
        }
        return this.watchNextSummary.getErrorType();
    }
    
    @Override
    public boolean getFbDntShare() {
        return true;
    }
    
    @Override
    public List<Video> getGallery() {
        return this.galleryVideos;
    }
    
    @Override
    public String getGalleryRequestId() {
        if (this.galleryListSummary == null) {
            return null;
        }
        return this.galleryListSummary.getRequestId();
    }
    
    @Override
    public int getGalleryTrackId() {
        if (this.galleryListSummary == null) {
            return 0;
        }
        return this.galleryListSummary.getTrackId();
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.mdxHorzUrl;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.mdxVertUrl;
    }
    
    @Override
    public String getHorzDispUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.horzDispUrl;
    }
    
    @Override
    public String getId() {
        return this.getPlayableId();
    }
    
    @Override
    public String getParentId() {
        if (!VideoType.EPISODE.equals(this.getType()) || this.watchNextEpisodeDetail == null) {
            return null;
        }
        return this.watchNextEpisodeDetail.getShowId();
    }
    
    @Override
    public String getParentTitle() {
        if (!VideoType.EPISODE.equals(this.getType()) || this.watchNextEpisodeDetail == null) {
            return null;
        }
        return this.watchNextEpisodeDetail.getShowTitle();
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        int bookmarkPosition;
        if (this.watchNextBookmark == null) {
            bookmarkPosition = 0;
        }
        else {
            bookmarkPosition = this.watchNextBookmark.getBookmarkPosition();
        }
        return BrowseAgent.computePlayPos(bookmarkPosition, this.getEndtime(), this.getRuntime());
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        if (this.watchNextBookmark == null) {
            return 0L;
        }
        return this.watchNextBookmark.getLastModified();
    }
    
    @Override
    public String getPlayableId() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getTitle();
    }
    
    @Override
    public int getRuntime() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        Integer value;
        if (watchNextDetails == null) {
            value = null;
        }
        else {
            value = watchNextDetails.runtime;
        }
        return value;
    }
    
    @Override
    public int getSeasonNumber() {
        if (!VideoType.EPISODE.equals(this.getType()) || this.watchNextEpisodeDetail == null) {
            return 0;
        }
        return this.watchNextEpisodeDetail.getSeasonNumber();
    }
    
    @Override
    public String getSquareUrl() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getSquareUrl();
    }
    
    @Override
    public String getStoryUrl() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.synopsis;
    }
    
    @Override
    public String getTitle() {
        return this.getPlayableTitle();
    }
    
    @Override
    public String getTvCardUrl() {
        if (this.watchNextSummary == null) {
            return null;
        }
        return this.watchNextSummary.getTvCardUrl();
    }
    
    @Override
    public VideoType getType() {
        if (this.watchNextSummary == null) {
            return VideoType.UNKNOWN;
        }
        return this.watchNextSummary.getType();
    }
    
    @Override
    public String getWatchNextDispUrl() {
        Log.d("nf_kidscharacter", String.format("[%s %s], firstPlay:%b (watchedRecently:%b), S%d:E%d, pos:%d", this.getType(), this.getPlayableId(), this.isFirstPlay(), this.getHasWatchedRecently(), this.getSeasonNumber(), this.getEpisodeNumber(), this.getPlayableBookmarkPosition()));
        if (this.isFirstPlay()) {
            return this.getCharacterSquareUrl();
        }
        return this.getWatchNextSquareUrl();
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isPinProtected() {
        final com.netflix.mediaclient.service.webclient.model.branches.Video.Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isPinProtected;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.watchNextSummary != null && this.watchNextSummary.isEpisode();
    }
    
    @Override
    public boolean isUserConnectedToFacebook() {
        return false;
    }
    
    @Override
    public String toString() {
        return "KidsCharacterDetails [kidsSummary=" + this.kidsSummary + ", kidsDetail=" + this.kidsDetail + ", watchNextSummary=" + this.watchNextSummary + ", watchNextBookmark=" + this.watchNextBookmark + ", watchNextMovieDetail=" + this.watchNextMovieDetail + ", watchNextEpisodeDetail=" + this.watchNextEpisodeDetail + ", galleryListSummary=" + this.galleryListSummary + ", galleryVideos=" + this.galleryVideos + "]";
    }
    
    public void updateWatchNext(final KidsCharacterDetails kidsCharacterDetails) {
        if (kidsCharacterDetails == null) {
            return;
        }
        this.watchNextSummary = kidsCharacterDetails.watchNextSummary;
        this.watchNextMovieDetail = kidsCharacterDetails.watchNextMovieDetail;
        this.watchNextEpisodeDetail = kidsCharacterDetails.watchNextEpisodeDetail;
        this.watchNextBookmark = kidsCharacterDetails.watchNextBookmark;
    }
}
