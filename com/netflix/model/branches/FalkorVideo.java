// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.Episode$Detail;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.model.leafs.SocialEvidence;
import com.netflix.mediaclient.servicemgr.model.user.SocialBadge;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.model.leafs.Video$SearchTitle;
import com.netflix.model.leafs.Video$UserRating;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.model.leafs.Video$Detail;
import com.netflix.model.leafs.Video$BookmarkStill;
import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.BasicVideo;
import com.netflix.model.BaseFalkorObject;

public class FalkorVideo extends BaseFalkorObject implements BasicVideo, Billboard, CWVideo, Playable, UserRating, Video, MovieDetails, ShowDetails, SearchVideo, FalkorObject
{
    private static final String TAG = "FalkorVideo";
    protected Video$Bookmark bookmark;
    private Video$BookmarkStill bookmarkStill;
    private Video$Detail detail;
    private BranchMap<Ref> episodes;
    private Video$InQueue inQueue;
    private Video$UserRating rating;
    private Video$SearchTitle searchTitle;
    private BranchMap<Ref> seasons;
    private SummarizedList<Ref, TrackableListSummary> sims;
    private SocialBadge socialBadge;
    private SocialEvidence socialEvidence;
    private Video$Summary summary;
    
    public FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
        if (Log.isLoggable("FalkorVideo", 2)) {
            Log.v("FalkorVideo", "Creating FalkorVideo");
        }
    }
    
    private long getBookmarkLastUpdateTime() {
        long lastModified;
        if (this.bookmark != null) {
            lastModified = this.bookmark.getLastModified();
        }
        else {
            lastModified = -1L;
        }
        long lastModified2 = lastModified;
        if (this.getCurrentEpisodeDetail() != null) {
            final Video$Bookmark video$Bookmark = (Video$Bookmark)this.getCurrentEpisode().get("bookmark");
            lastModified2 = lastModified;
            if (video$Bookmark != null) {
                lastModified2 = video$Bookmark.getLastModified();
            }
        }
        return lastModified2;
    }
    
    private int getBookmarkPosition() {
        int bookmarkPosition;
        if (this.bookmark != null) {
            bookmarkPosition = this.bookmark.getBookmarkPosition();
        }
        else {
            bookmarkPosition = -1;
        }
        int bookmarkPosition2 = bookmarkPosition;
        if (this.getCurrentEpisodeDetail() != null) {
            final Video$Bookmark video$Bookmark = (Video$Bookmark)this.getCurrentEpisode().get("bookmark");
            bookmarkPosition2 = bookmarkPosition;
            if (video$Bookmark != null) {
                bookmarkPosition2 = video$Bookmark.getBookmarkPosition();
            }
        }
        return bookmarkPosition2;
    }
    
    private FalkorEpisode getCurrentEpisode() {
        if (this.episodes == null) {
            return null;
        }
        final Ref ref = (Ref)this.episodes.get("current");
        if (ref == null) {
            return null;
        }
        return (FalkorEpisode)ref.getValue(this.getModelProxy());
    }
    
    private Episode$Detail getCurrentEpisodeDetail() {
        final FalkorEpisode currentEpisode = this.getCurrentEpisode();
        if (currentEpisode == null) {
            return null;
        }
        return (Episode$Detail)currentEpisode.get("detail");
    }
    
    private TrackableListSummary getSimilarsSummary() {
        if (this.sims == null) {
            return null;
        }
        return (TrackableListSummary)this.sims.get("summary");
    }
    
    @Override
    public boolean canBeSharedOnFacebook() {
        return this.isCurrentProfileFacebookConnected() && this.socialEvidence != null && !this.socialEvidence.isVideoHidden();
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.summary;
            }
            case "detail": {
                return this.getDetail();
            }
            case "rating": {
                return this.rating;
            }
            case "inQueue": {
                return this.inQueue;
            }
            case "bookmark": {
                return this.bookmark;
            }
            case "bookmarkStill": {
                return this.bookmarkStill;
            }
            case "socialEvidence": {
                return this.socialEvidence;
            }
            case "searchTitle": {
                return this.searchTitle;
            }
            case "similars": {
                return this.sims;
            }
            case "episodes": {
                return this.episodes;
            }
            case "seasons": {
                return this.seasons;
            }
        }
    }
    
    @Override
    public String getActors() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.actors;
    }
    
    @Override
    public String getBifUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.bifUrl;
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
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.restUrl;
    }
    
    @Override
    public String getCertification() {
        final Video$Detail detail = this.getDetail();
        String certification;
        if (detail == null) {
            certification = null;
        }
        else {
            certification = detail.certification;
        }
        if (!StringUtils.isEmpty(certification)) {
            return certification;
        }
        if (this.searchTitle == null) {
            return null;
        }
        return this.searchTitle.certification;
    }
    
    @Override
    public String getCreators() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.directors;
    }
    
    @Override
    public String getCurrentEpisodeId() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return null;
        }
        return currentEpisodeDetail.getId();
    }
    
    @Override
    public int getCurrentEpisodeNumber() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return -1;
        }
        return currentEpisodeDetail.getEpisodeNumber();
    }
    
    @Override
    public String getCurrentEpisodeTitle() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return null;
        }
        return currentEpisodeDetail.getTitle();
    }
    
    @Override
    public int getCurrentSeasonNumber() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return -1;
        }
        return currentEpisodeDetail.getSeasonNumber();
    }
    
    protected Video$Detail getDetail() {
        return this.detail;
    }
    
    @Override
    public String getDirectors() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.directors;
    }
    
    @Override
    public int getEndtime() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.endtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return -1;
        }
        return currentEpisodeDetail.getEpisodeNumber();
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
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.mdxHorzUrl;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.mdxVertUrl;
    }
    
    @Override
    public String getHorzDispUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.horzDispUrl;
        }
        if (this.summary == null) {
            return null;
        }
        return this.summary.getHorzDispUrl();
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
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.intrUrl;
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.getDetail() != null) {
            set.add("detail");
        }
        if (this.rating != null) {
            set.add("rating");
        }
        if (this.inQueue != null) {
            set.add("inQueue");
        }
        if (this.bookmark != null) {
            set.add("bookmark");
        }
        if (this.bookmarkStill != null) {
            set.add("bookmarkStill");
        }
        if (this.socialEvidence != null) {
            set.add("socialEvidence");
        }
        if (this.searchTitle != null) {
            set.add("searchTitle");
        }
        if (this.sims != null) {
            set.add("similars");
        }
        if (this.episodes != null) {
            set.add("episodes");
        }
        if (this.seasons != null) {
            set.add("seasons");
        }
        return set;
    }
    
    @Override
    public int getNumDirectors() {
        return StringUtils.getCsvCount(this.getDirectors());
    }
    
    @Override
    public int getNumOfEpisodes() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return -1;
        }
        return detail.episodeCount;
    }
    
    @Override
    public int getNumOfSeasons() {
        final Video$Detail detail = this.getDetail();
        if (!VideoType.SHOW.equals(this.getType()) || detail == null) {
            return 0;
        }
        return detail.seasonCount;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if (Log.isLoggable("FalkorVideo", 2)) {
            Log.v("FalkorVideo", "Creating leaf for key: " + s);
        }
        switch (s) {
            default: {
                return null;
            }
            case "summary": {
                return this.summary = new Video$Summary();
            }
            case "detail": {
                return this.detail = new Video$Detail();
            }
            case "rating": {
                return this.rating = new Video$UserRating();
            }
            case "inQueue": {
                return this.inQueue = new Video$InQueue();
            }
            case "bookmark": {
                return this.bookmark = new Video$Bookmark();
            }
            case "bookmarkStill": {
                return this.bookmarkStill = new Video$BookmarkStill();
            }
            case "socialEvidence": {
                return this.socialEvidence = new SocialEvidence();
            }
            case "searchTitle": {
                return this.searchTitle = new Video$SearchTitle();
            }
            case "similars": {
                return this.sims = new SummarizedList<Ref, TrackableListSummary>(Falkor$Creator.Ref, Falkor$Creator.TrackableListSummary);
            }
            case "episodes": {
                return this.episodes = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "seasons": {
                return this.seasons = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
        }
    }
    
    @Override
    public String getParentId() {
        return this.getId();
    }
    
    @Override
    public String getParentTitle() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return null;
        }
        return this.getTitle();
    }
    
    @Override
    public Playable getPlayable() {
        return this;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        final int computePlayPos = BrowseAgent.computePlayPos(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
        if (Log.isLoggable("FalkorVideo", 3)) {
            Log.d("FalkorVideo", String.format("id %s bookmark %d playPos %d endtime %d runtime %d", this.getId(), this.getBookmarkPosition(), computePlayPos, this.getEndtime(), this.getRuntime()));
        }
        return computePlayPos;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return this.getBookmarkLastUpdateTime();
    }
    
    @Override
    public String getPlayableId() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.getId();
        }
        return this.getCurrentEpisodeDetail().getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.getTitle();
        }
        return this.getCurrentEpisodeDetail().getTitle();
    }
    
    @Override
    public float getPredictedRating() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return -1.0f;
        }
        return detail.predictedRating;
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
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.runtime;
    }
    
    @Override
    public int getSeasonNumber() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return -1;
        }
        return currentEpisodeDetail.getSeasonNumber();
    }
    
    @Override
    public List<Video> getSimilars() {
        return this.getModelProxy().getItemsAsList(CachedModelProxy.buildVideoSimsPql(this.getType() == VideoType.MOVIE, this.getId()));
    }
    
    @Override
    public int getSimilarsListPos() {
        return 0;
    }
    
    @Override
    public String getSimilarsRequestId() {
        final TrackableListSummary similarsSummary = this.getSimilarsSummary();
        if (similarsSummary == null) {
            return null;
        }
        return similarsSummary.getRequestId();
    }
    
    @Override
    public int getSimilarsTrackId() {
        final TrackableListSummary similarsSummary = this.getSimilarsSummary();
        if (similarsSummary == null) {
            return 0;
        }
        return similarsSummary.getTrackId();
    }
    
    @Override
    public SocialBadge getSocialBadge() {
        return this.socialBadge;
    }
    
    @Override
    public String getSquareUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getSquareUrl();
    }
    
    @Override
    public String getStillUrl() {
        if (this.bookmarkStill == null) {
            return null;
        }
        return this.bookmarkStill.stillUrl;
    }
    
    @Override
    public String getStoryDispUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.storyImgDispUrl;
    }
    
    @Override
    public String getStoryUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.synopsis;
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
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.tvCardUrl;
        }
        if (this.summary == null) {
            return null;
        }
        return this.summary.getTvCardUrl();
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
        final Video$Detail detail = this.getDetail();
        int year;
        if (detail == null) {
            year = 0;
        }
        else {
            year = detail.year;
        }
        if (year > 0) {
            return year;
        }
        if (this.searchTitle == null) {
            return 0;
        }
        return this.searchTitle.releaseYear;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        if (VideoType.MOVIE.equals(this.getType())) {
            final Video$Detail detail = this.getDetail();
            if (detail != null) {
                return detail.isAutoPlayEnabled;
            }
        }
        else {
            final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isAutoPlayEnabled();
            }
        }
        return false;
    }
    
    @Override
    public boolean isInQueue() {
        return this.inQueue != null && this.inQueue.inQueue;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        if (VideoType.SHOW.equals(this.getType())) {
            final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isNextPlayableEpisode();
            }
        }
        return false;
    }
    
    @Override
    public boolean isPinProtected() {
        if (VideoType.MOVIE.equals(this.getType())) {
            final Video$Detail detail = this.getDetail();
            if (detail != null) {
                return detail.isPinProtected;
            }
        }
        else {
            final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isPinProtected();
            }
        }
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.getCurrentEpisodeDetail() != null;
    }
    
    @Override
    public boolean isVideoHd() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isHdAvailable;
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {}
            case "summary": {
                this.summary = (Video$Summary)o;
            }
            case "detail": {
                this.detail = (Video$Detail)o;
            }
            case "rating": {
                this.rating = (Video$UserRating)o;
            }
            case "inQueue": {
                this.inQueue = (Video$InQueue)o;
            }
            case "bookmark": {
                this.bookmark = (Video$Bookmark)o;
            }
            case "bookmarkStill": {
                this.bookmarkStill = (Video$BookmarkStill)o;
            }
            case "socialEvidence": {
                this.socialEvidence = (SocialEvidence)o;
            }
            case "searchTitle": {
                this.searchTitle = (Video$SearchTitle)o;
            }
            case "similars": {
                this.sims = (SummarizedList<Ref, TrackableListSummary>)o;
            }
            case "episodes": {
                this.episodes = (BranchMap<Ref>)o;
            }
            case "seasons": {
                this.seasons = (BranchMap<Ref>)o;
            }
        }
    }
    
    @Override
    public void setUserRating(final float userRating) {
        this.rating.userRating = userRating;
    }
    
    @Override
    public String toString() {
        return "FalkorVideo [getId()=" + this.getId() + ", getTitle()=" + this.getTitle() + "]";
    }
}
