// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.model.leafs.Episode;
import com.netflix.mediaclient.Log;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.SocialEvidence;
import com.netflix.model.leafs.Season;
import com.netflix.mediaclient.service.webclient.model.PostPlayVideo;
import com.netflix.model.leafs.KidsCharacter;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.model.BaseFalkorObject;

public class FalkorVideo extends BaseFalkorObject implements Video, Billboard, CWVideo, Playable
{
    private static final String TAG = "FalkorVideo";
    public com.netflix.model.leafs.Video.Bookmark bookmark;
    public com.netflix.model.leafs.Video.BookmarkStill bookmarkStill;
    public com.netflix.model.leafs.Video.Detail detail;
    public BranchMap<Ref> episodes;
    public SummarizedList<Ref, TrackableListSummary> galleryVideos;
    public com.netflix.model.leafs.Video.InQueue inQueue;
    public KidsCharacter.KidsDetail kidsDetail;
    public KidsCharacter.KidsSummary kidsSummary;
    public PostPlayVideo.PostPlayContext postplayContext;
    public com.netflix.model.leafs.Video.Rating rating;
    public com.netflix.model.leafs.Video.SearchTitle searchTitle;
    public Season.Detail seasonDetail;
    public SummarizedList<Ref, TrackableListSummary> sims;
    public SocialEvidence socialEvidence;
    public com.netflix.model.leafs.Video.Summary summary;
    
    public FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
        if (Log.isLoggable("FalkorVideo", 2)) {
            Log.v("FalkorVideo", "Creating FalkorVideo");
        }
    }
    
    private long getBookmarkLastUpdateTime() {
        long lastModified = -1L;
        if (this.bookmark != null) {
            lastModified = this.bookmark.getLastModified();
        }
        long lastModified2 = lastModified;
        if (this.getCurrentEpisodeDetail() != null) {
            final com.netflix.model.leafs.Video.Bookmark bookmark = (com.netflix.model.leafs.Video.Bookmark)this.getCurrentEpisode().get("bookmark");
            lastModified2 = lastModified;
            if (bookmark != null) {
                lastModified2 = bookmark.getLastModified();
            }
        }
        return lastModified2;
    }
    
    private int getBookmarkPosition() {
        int bookmarkPosition = -1;
        if (this.bookmark != null) {
            bookmarkPosition = this.bookmark.getBookmarkPosition();
        }
        int bookmarkPosition2 = bookmarkPosition;
        if (this.getCurrentEpisodeDetail() != null) {
            final com.netflix.model.leafs.Video.Bookmark bookmark = (com.netflix.model.leafs.Video.Bookmark)this.getCurrentEpisode().get("bookmark");
            bookmarkPosition2 = bookmarkPosition;
            if (bookmark != null) {
                bookmarkPosition2 = bookmark.getBookmarkPosition();
            }
        }
        return bookmarkPosition2;
    }
    
    private FalkorEpisode getCurrentEpisode() {
        final Ref ref = (Ref)this.episodes.get("current");
        if (ref == null) {
            return null;
        }
        return (FalkorEpisode)ref.getValue(this.getModelProxy());
    }
    
    private Episode.Detail getCurrentEpisodeDetail() {
        final FalkorEpisode currentEpisode = this.getCurrentEpisode();
        if (currentEpisode == null) {
            return null;
        }
        return (Episode.Detail)currentEpisode.get("detail");
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
            case "similars": {
                return this.sims;
            }
            case "episodes": {
                return this.episodes;
            }
        }
    }
    
    @Override
    public String getBoxshotURL() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxshotURL();
    }
    
    @Override
    public String getCertification() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.certification;
    }
    
    protected com.netflix.model.leafs.Video.Detail getDetail() {
        return this.detail;
    }
    
    @Override
    public int getEndtime() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.endtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        final Episode.Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
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
    public String getHorzDispUrl() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
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
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
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
        if (this.sims != null) {
            set.add("similars");
        }
        if (this.episodes != null) {
            set.add("episodes");
        }
        return set;
    }
    
    @Override
    public int getNumOfSeasons() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
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
                return this.summary = new com.netflix.model.leafs.Video.Summary();
            }
            case "detail": {
                return this.detail = new com.netflix.model.leafs.Video.Detail();
            }
            case "rating": {
                return this.rating = new com.netflix.model.leafs.Video.Rating();
            }
            case "inQueue": {
                return this.inQueue = new com.netflix.model.leafs.Video.InQueue();
            }
            case "bookmark": {
                return this.bookmark = new com.netflix.model.leafs.Video.Bookmark();
            }
            case "bookmarkStill": {
                return this.bookmarkStill = new com.netflix.model.leafs.Video.BookmarkStill();
            }
            case "socialEvidence": {
                return this.socialEvidence = new SocialEvidence();
            }
            case "similars": {
                return this.sims = new SummarizedList<Ref, TrackableListSummary>(Falkor.Creator.Ref, Falkor.Creator.TrackableListSummary);
            }
            case "episodes": {
                return this.episodes = new BranchMap<Ref>(Falkor.Creator.Ref);
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
    public int getRuntime() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.runtime;
    }
    
    @Override
    public int getSeasonNumber() {
        final Episode.Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return -1;
        }
        return currentEpisodeDetail.getSeasonNumber();
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
    public String getStoryUrl() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
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
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
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
    public int getYear() {
        final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.year;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        if (VideoType.MOVIE.equals(this.getType())) {
            final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
            if (detail != null) {
                return detail.isAutoPlayEnabled;
            }
        }
        else {
            final Episode.Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isAutoPlayEnabled();
            }
        }
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        if (VideoType.SHOW.equals(this.getType())) {
            final Episode.Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isNextPlayableEpisode();
            }
        }
        return false;
    }
    
    @Override
    public boolean isPinProtected() {
        if (VideoType.MOVIE.equals(this.getType())) {
            final com.netflix.model.leafs.Video.Detail detail = this.getDetail();
            if (detail != null) {
                return detail.isPinProtected;
            }
        }
        else {
            final Episode.Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
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
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (com.netflix.model.leafs.Video.Summary)o;
        }
        else {
            if ("detail".equals(s)) {
                this.detail = (com.netflix.model.leafs.Video.Detail)o;
                return;
            }
            if ("rating".equals(s)) {
                this.rating = (com.netflix.model.leafs.Video.Rating)o;
                return;
            }
            if ("inQueue".equals(s)) {
                this.inQueue = (com.netflix.model.leafs.Video.InQueue)o;
                return;
            }
            if ("bookmark".equals(s)) {
                this.bookmark = (com.netflix.model.leafs.Video.Bookmark)o;
                return;
            }
            if ("bookmarkStill".equals(s)) {
                this.bookmarkStill = (com.netflix.model.leafs.Video.BookmarkStill)o;
                return;
            }
            if ("socialEvidence".equals(s)) {
                this.socialEvidence = (SocialEvidence)o;
                return;
            }
            if ("similars".equals(s)) {
                this.sims = (SummarizedList<Ref, TrackableListSummary>)o;
                return;
            }
            if ("episodes".equals(s)) {
                this.episodes = (BranchMap<Ref>)o;
            }
        }
    }
}
