// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.leafs.PostPlayExperience;
import com.netflix.falkor.PQL;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayContext;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import java.util.HashSet;
import java.util.Set;
import com.netflix.model.leafs.InteractivePostplay;
import com.netflix.mediaclient.servicemgr.interface_.IconFontGlyph;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.model.leafs.Episode$Detail;
import com.netflix.falkor.Sentinel;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.Video$Evidence;
import com.netflix.model.leafs.Video$VerticalStoryArt;
import com.netflix.model.leafs.Video$SupplementalVideos;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.model.leafs.Video$SearchTitle;
import com.netflix.model.leafs.Video$UserRating;
import com.netflix.model.leafs.PostPlayImpression;
import com.netflix.model.leafs.Video$KubrickSummary;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.model.leafs.Video$InQueue;
import com.netflix.model.leafs.Video$HeroImages;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.model.leafs.Video$Detail;
import com.netflix.model.leafs.Video$CwCleanBoxart;
import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.model.leafs.Video$Advisories;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.KubrickShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.InteractiveMoments;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.UserRating;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import com.netflix.mediaclient.servicemgr.interface_.BasicVideo;
import com.netflix.model.BaseFalkorObject;

public class FalkorVideo extends BaseFalkorObject implements BasicVideo, Billboard, CWVideo, KubrickVideo, Playable, UserRating, Video, InteractiveMoments, KubrickShowDetails, MovieDetails, PostPlayVideo, PostPlayVideosProvider, ShowDetails, SearchVideo, FalkorObject
{
    private static final String TAG = "FalkorVideo";
    private Video$Advisories advisories;
    private BillboardSummary billboardSummary;
    protected Video$Bookmark bookmark;
    private Video$CwCleanBoxart cleanBoxshot;
    private Video$Detail detail;
    private BranchMap<Ref> episodes;
    private Video$HeroImages heroImages;
    private Video$InQueue inQueue;
    private InteractiveInfo interactiveInfo;
    private InteractivePlaybackMoments interactivePlaybackMoments;
    private Video$KubrickSummary kubrick;
    private UnsummarizedList<MementoVideoSwatch> mementoVideoSwatches;
    private BranchMap<Ref> performerStills;
    private BranchMap<Ref> performers;
    private Ref postPlayExperience;
    private PostPlayImpression postPlayImpression;
    private Video$UserRating rating;
    private BranchMap<FalkorScene> scenes;
    private Video$SearchTitle searchTitle;
    private BranchMap<Ref> seasons;
    private SummarizedList<Ref, TrackableListSummary> sims;
    private Video$Summary summary;
    private Video$SupplementalVideos supplementalVideos;
    private Video$VerticalStoryArt vertStoryArtUrl;
    private Video$Evidence videoEvidence;
    
    public FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
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
        final Object value = this.episodes.get("current");
        if (value == null || value instanceof Sentinel) {
            return null;
        }
        return (FalkorEpisode)((Ref)value).getValue(this.getModelProxy());
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
    
    private boolean isPostPlayInvalid(final String s) {
        if (this.getId() == null) {
            this.logInvalidPostPlayMethod(s, "video ID");
            return true;
        }
        if (this.getType() == null) {
            this.logInvalidPostPlayMethod(s, "video type");
            return true;
        }
        return false;
    }
    
    private void logInvalidPostPlayMethod(String string, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("SPY-7478 - Can't get post play ").append(string).append(" because ").append(s).append(" is null - ").append("getType=").append(this.getType()).append(",getId=").append(this.getId()).append(",getCurrentEpisodeId=").append(this.getCurrentEpisodeId());
        string = sb.toString();
        Log.w("FalkorVideo", string);
        this.proxy.getServiceProvider().getService().getClientLogging().getErrorLogging().logHandledException(string);
    }
    
    @Override
    public String createModifiedBigStillUrl() {
        return UriUtil.buildStillUrlFromPos(this, true);
    }
    
    @Override
    public String createModifiedStillUrl() {
        return UriUtil.buildStillUrlFromPos(this, false);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "cleanBoxart": {
                return this.cleanBoxshot;
            }
            case "advisories": {
                return this.advisories;
            }
            case "summary": {
                return this.summary;
            }
            case "kubrick": {
                return this.kubrick;
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
            case "searchTitle": {
                return this.searchTitle;
            }
            case "evidence": {
                return this.videoEvidence;
            }
            case "heroImgs": {
                return this.heroImages;
            }
            case "defaultTrailer": {
                return this.supplementalVideos;
            }
            case "vertStoryArt": {
                return this.vertStoryArtUrl;
            }
            case "interactiveMoments": {
                return this.interactivePlaybackMoments;
            }
            case "postPlayImpression": {
                return this.postPlayImpression;
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
            case "scenes": {
                return this.scenes;
            }
            case "postPlayExperience": {
                return this.postPlayExperience;
            }
            case "cast": {
                return this.performers;
            }
            case "castStills": {
                return this.performerStills;
            }
            case "mementoVideoSwatches": {
                return this.mementoVideoSwatches;
            }
            case "interactive": {
                return this.interactiveInfo;
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
    public List<Advisory> getAdvisories() {
        if (this.advisories == null) {
            return new ArrayList<Advisory>(0);
        }
        return this.advisories.getAdvisoryList();
    }
    
    @Override
    public int getAutoPlayMaxCount() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return -1;
        }
        return detail.autoPlayMaxCount;
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
    public BillboardSummary getBillboardSummary() {
        return this.billboardSummary;
    }
    
    public Video$Bookmark getBookmark() {
        return this.bookmark;
    }
    
    @Override
    public String getBoxartImageTypeIdentifier() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxartImageTypeIdentifier();
    }
    
    @Override
    public String getBoxshotUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getBoxshotUrl();
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
        String certification2 = certification;
        if (StringUtils.isEmpty(certification)) {
            if (this.searchTitle == null) {
                certification2 = null;
            }
            else {
                certification2 = this.searchTitle.certification;
            }
        }
        if (!StringUtils.isEmpty(certification2)) {
            return certification2;
        }
        if (this.kubrick == null) {
            return null;
        }
        return this.kubrick.certification;
    }
    
    @Override
    public String getCleanBoxshotUrl() {
        if (this.cleanBoxshot == null) {
            return null;
        }
        return this.cleanBoxshot.getCleanBoxshotUrl();
    }
    
    @Override
    public String getCopyright() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.copyright;
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
    public List<String> getCurrentEpisodeBadges() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return new ArrayList<String>(3);
        }
        return currentEpisodeDetail.getEpisodeBadges();
    }
    
    @Override
    public String getCurrentEpisodeHorzDispUrl() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return null;
        }
        return currentEpisodeDetail.getHorzDispUrl();
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
    public String getCurrentEpisodeStoryImgUrl() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return null;
        }
        return currentEpisodeDetail.getSoryImgUrl();
    }
    
    @Override
    public String getCurrentEpisodeSynopsis() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null) {
            return null;
        }
        return currentEpisodeDetail.getSynopsis();
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
    
    @Override
    public String getDefaultTrailer() {
        if (this.supplementalVideos == null) {
            return null;
        }
        return this.supplementalVideos.defaultTrailer;
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
    public List<String> getEpisodeBadges() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.episodeBadges;
        }
        return new ArrayList<String>(3);
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
    public IconFontGlyph getEvidenceGlyph() {
        if (this.videoEvidence == null) {
            return null;
        }
        return this.videoEvidence.getIconFontGlyph();
    }
    
    @Override
    public String getEvidenceText() {
        if (this.videoEvidence == null) {
            return null;
        }
        return this.videoEvidence.getText();
    }
    
    @Override
    public long getExpirationTime() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return -1L;
        }
        return detail.expirationTime;
    }
    
    @Override
    public String getGenres() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.genres;
    }
    
    @Override
    public List<String> getHeroImages() {
        if (this.heroImages == null) {
            return null;
        }
        return this.heroImages.heroImgs;
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.hiResHorzUrl;
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
    public String getHorzDispSmallUrl() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getHorzDispSmallUrl();
    }
    
    @Override
    public String getHorzDispUrl() {
        final Video$Detail detail = this.getDetail();
        if (this.summary != null) {
            return this.summary.getHorzDispUrl();
        }
        if (detail == null) {
            return null;
        }
        return detail.horzDispUrl;
    }
    
    @Override
    public String getId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getId();
    }
    
    @Override
    public InteractivePlaybackMoments getInteractiveMoments() {
        return this.interactivePlaybackMoments;
    }
    
    @Override
    public InteractivePostplay getInteractivePostplay() {
        if (this.interactiveInfo == null) {
            return null;
        }
        return this.interactiveInfo.postplay;
    }
    
    @Override
    public String getInterestingSmallUrl() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail != null) {
            return currentEpisodeDetail.getInterestingSmallUrl();
        }
        return null;
    }
    
    @Override
    public String getInterestingUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.getInterestingUrl();
        }
        return null;
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.cleanBoxshot != null) {
            set.add("cleanBoxart");
        }
        if (this.advisories != null) {
            set.add("advisories");
        }
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.kubrick != null) {
            set.add("kubrick");
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
        if (this.searchTitle != null) {
            set.add("searchTitle");
        }
        if (this.videoEvidence != null) {
            set.add("evidence");
        }
        if (this.heroImages != null) {
            set.add("heroImgs");
        }
        if (this.supplementalVideos != null) {
            set.add("defaultTrailer");
        }
        if (this.vertStoryArtUrl != null) {
            set.add("vertStoryArt");
        }
        if (this.interactivePlaybackMoments != null) {
            set.add("interactiveMoments");
        }
        if (this.postPlayImpression != null) {
            set.add("postPlayImpression");
        }
        if (this.sims != null) {
            set.add("similars");
        }
        if (this.episodes != null) {
            set.add("episodes");
        }
        if (this.postPlayExperience != null) {
            set.add("postPlayExperience");
        }
        if (this.performers != null) {
            set.add("cast");
        }
        if (this.performerStills != null) {
            set.add("castStills");
        }
        if (this.mementoVideoSwatches != null) {
            set.add("mementoVideoSwatches");
        }
        if (this.seasons != null) {
            set.add("seasons");
        }
        if (this.scenes != null) {
            set.add("scenes");
        }
        if (this.interactiveInfo != null) {
            set.add("interactive");
        }
        return set;
    }
    
    @Override
    public String getKubrickStoryImgUrl() {
        if (this.kubrick == null) {
            return null;
        }
        return this.kubrick.storyImgUrl;
    }
    
    @Override
    public int getLogicalStart() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return 0;
        }
        return detail.logicalStart;
    }
    
    @Override
    public int getMaturityLevel() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return -1;
        }
        return detail.maturityLevel;
    }
    
    @Override
    public String getNarrative() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.synopsisNarrative;
        }
        if (this.kubrick == null) {
            return null;
        }
        return this.kubrick.narrative;
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
    public String getNumSeasonsLabel() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            if (detail.seasonNumLabel == null) {
                return "";
            }
            return detail.seasonNumLabel;
        }
        else {
            if (this.kubrick == null) {
                return "";
            }
            if (this.kubrick.seasonNumLabel == null) {
                return "";
            }
            return this.kubrick.seasonNumLabel;
        }
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("FalkorVideo", "Creating leaf for key: " + s);
        }
        switch (s) {
            default: {
                return null;
            }
            case "cleanBoxart": {
                return this.cleanBoxshot = new Video$CwCleanBoxart();
            }
            case "advisories": {
                return this.advisories = new Video$Advisories();
            }
            case "summary": {
                return this.summary = new Video$Summary();
            }
            case "kubrick": {
                return this.kubrick = new Video$KubrickSummary();
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
            case "searchTitle": {
                return this.searchTitle = new Video$SearchTitle();
            }
            case "evidence": {
                return this.videoEvidence = new Video$Evidence();
            }
            case "heroImgs": {
                return this.heroImages = new Video$HeroImages();
            }
            case "defaultTrailer": {
                return this.supplementalVideos = new Video$SupplementalVideos();
            }
            case "vertStoryArt": {
                return this.vertStoryArtUrl = new Video$VerticalStoryArt();
            }
            case "interactiveMoments": {
                return this.interactivePlaybackMoments = new InteractivePlaybackMoments();
            }
            case "postPlayImpression": {
                return this.postPlayImpression = new PostPlayImpression();
            }
            case "similars": {
                return this.sims = new SummarizedList<Ref, TrackableListSummary>(Falkor$Creator.Ref, Falkor$Creator.TrackableListSummary);
            }
            case "episodes": {
                return this.episodes = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "postPlayExperience": {
                return this.postPlayExperience = new Ref();
            }
            case "cast": {
                return this.performers = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "castStills": {
                return this.performerStills = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "mementoVideoSwatches": {
                return this.mementoVideoSwatches = new UnsummarizedList<MementoVideoSwatch>(Falkor$Creator.MementoVideoSwatch);
            }
            case "seasons": {
                return this.seasons = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "scenes": {
                return this.scenes = new BranchMap<FalkorScene>(Falkor$Creator.FalkorScene(this.proxy));
            }
            case "interactive": {
                return this.interactiveInfo = new InteractiveInfo();
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
        final int computePlayPos = TimeUtils.computePlayPos(this.getBookmarkPosition(), this.getEndtime(), this.getRuntime());
        if (Log.isLoggable()) {
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
        return this.getCurrentEpisodeId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (VideoType.MOVIE.equals(this.getType())) {
            return this.getTitle();
        }
        return this.getCurrentEpisodeTitle();
    }
    
    @Override
    public List<PostPlayContext> getPostPlayContexts() {
        if (this.isPostPlayInvalid("contexts")) {
            return null;
        }
        return this.proxy.getItemsAsList(PQL.create(this.getType().getValue(), this.getId(), "postPlayExperience", PQL.range(2), "postplayContext"));
    }
    
    @Override
    public PostPlayExperience getPostPlayExperienceData() {
        if (this.postPlayExperience != null && this.getId() != null) {
            final List<PostPlayExperience> itemsAsList = (List<PostPlayExperience>)this.proxy.getItemsAsList(PQL.create("postPlayExperiences", this.getId(), "experienceData"));
            if (itemsAsList.size() != 0) {
                return itemsAsList.get(0);
            }
        }
        return null;
    }
    
    @Override
    public List<PostPlayVideo> getPostPlayVideos() {
        if (this.isPostPlayInvalid("videos")) {
            return null;
        }
        return this.proxy.getItemsAsList(PQL.create(this.getType().getValue(), this.getId(), "postPlayExperience", PQL.range(2), "videoRef", "summary"));
    }
    
    @Override
    public float getPredictedRating() {
        if (this.kubrick != null) {
            return this.kubrick.predictedRating;
        }
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
        int runtime;
        if (detail == null) {
            runtime = 0;
        }
        else {
            runtime = detail.runtime;
        }
        if (runtime > 0) {
            return runtime;
        }
        if (this.kubrick == null) {
            return 0;
        }
        return this.kubrick.runtime;
    }
    
    @Override
    public String getSeasonAbbrSeqLabel() {
        final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
        if (currentEpisodeDetail == null || currentEpisodeDetail.abbrSeqLabel == null) {
            return "";
        }
        return currentEpisodeDetail.abbrSeqLabel;
    }
    
    @Override
    public int getSeasonCount() {
        if (this.kubrick == null) {
            return 0;
        }
        return this.kubrick.seasonCount;
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
    public String getStoryDispUrl() {
        final Video$Summary summary = this.getSummary();
        if (summary == null) {
            return null;
        }
        return summary.storyImgDispUrl;
    }
    
    @Override
    public String getStoryUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.storyImgUrl;
    }
    
    public Video$Summary getSummary() {
        return this.summary;
    }
    
    @Override
    public String getSupplementalMessage() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.supplementalMessage;
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
    public String getTitleCroppedImgUrl() {
        if (this.detail == null) {
            return null;
        }
        return this.detail.titleCroppedUrl;
    }
    
    @Override
    public String getTitleImgUrl() {
        if (this.detail != null) {
            return this.detail.titleUrl;
        }
        if (this.kubrick == null) {
            return null;
        }
        return this.kubrick.titleUrl;
    }
    
    @Override
    public String getTrickplayBigImgBaseUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.baseUrlBig;
    }
    
    @Override
    public String getTrickplayImgBaseUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail == null) {
            return null;
        }
        return detail.baseUrl;
    }
    
    @Override
    public String getTvCardUrl() {
        final Video$Detail detail = this.getDetail();
        if (detail != null && StringUtils.isNotEmpty(detail.tvCardUrl)) {
            return detail.tvCardUrl;
        }
        if (this.summary == null || StringUtils.isEmpty(this.summary.getTvCardUrl())) {
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
    
    public String getVerticalStoryArtUrl() {
        return this.vertStoryArtUrl.url;
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
        int videoYear = year;
        if (year <= 0) {
            if (this.summary == null) {
                videoYear = 0;
            }
            else {
                videoYear = this.summary.videoYear;
            }
        }
        int releaseYear;
        if ((releaseYear = videoYear) <= 0) {
            if (this.searchTitle == null) {
                releaseYear = 0;
            }
            else {
                releaseYear = this.searchTitle.releaseYear;
            }
        }
        if (releaseYear > 0) {
            return releaseYear;
        }
        if (this.kubrick == null) {
            return 0;
        }
        return this.kubrick.year;
    }
    
    @Override
    public boolean hasTrailers() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.hasTrailers;
    }
    
    @Override
    public boolean hasWatched() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.hasWatched;
    }
    
    @Override
    public boolean isAdvisoryDisabled() {
        return false;
    }
    
    @Override
    public boolean isAgeProtected() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isAgeProtected;
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
    public boolean isAvailableToStream() {
        final Video$Detail detail = this.getDetail();
        return detail == null || detail.isAvailableToStream;
    }
    
    protected boolean isEpisode() {
        Boolean value;
        if (this.summary == null) {
            value = null;
        }
        else {
            value = this.summary.isEpisode();
        }
        return value;
    }
    
    @Override
    public boolean isExemptFromInterrupterLimit() {
        if (VideoType.MOVIE.equals(this.getType())) {
            final Video$Detail detail = this.getDetail();
            if (detail != null) {
                return detail.isExemptFromInterrupterLimit;
            }
        }
        else {
            final Episode$Detail currentEpisodeDetail = this.getCurrentEpisodeDetail();
            if (currentEpisodeDetail != null) {
                return currentEpisodeDetail.isExemptFromInterrupterLimit();
            }
        }
        return false;
    }
    
    @Override
    public boolean isInQueue() {
        return this.inQueue != null && this.inQueue.inQueue;
    }
    
    @Override
    public boolean isNSRE() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isNSRE;
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
    public boolean isOriginal() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.isOriginal;
        }
        return this.searchTitle != null && this.searchTitle.isOriginal;
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
    public boolean isPreRelease() {
        final Video$Detail detail = this.getDetail();
        if (detail != null) {
            return detail.isPreRelease;
        }
        return this.searchTitle != null && this.searchTitle.isPreRelease;
    }
    
    @Override
    public boolean isSupplementalVideo() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isSupplementalVideo;
    }
    
    @Override
    public boolean isVideo3D() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.is3DAvailable;
    }
    
    @Override
    public boolean isVideo5dot1() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.is5dot1Available;
    }
    
    @Override
    public boolean isVideoDolbyVision() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isDolbyVisionAvailable;
    }
    
    @Override
    public boolean isVideoHd() {
        if (this.kubrick != null) {
            return this.kubrick.isHd;
        }
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isHdAvailable;
    }
    
    @Override
    public boolean isVideoHdr10() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isHdr10Avaiable;
    }
    
    @Override
    public boolean isVideoUhd() {
        final Video$Detail detail = this.getDetail();
        return detail != null && detail.isUhdAvailable;
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {}
            case "cleanBoxart": {
                this.cleanBoxshot = (Video$CwCleanBoxart)o;
            }
            case "advisories": {
                this.advisories = (Video$Advisories)o;
            }
            case "summary": {
                this.summary = (Video$Summary)o;
            }
            case "kubrick": {
                this.kubrick = (Video$KubrickSummary)o;
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
            case "searchTitle": {
                this.searchTitle = (Video$SearchTitle)o;
            }
            case "evidence": {
                this.videoEvidence = (Video$Evidence)o;
            }
            case "heroImgs": {
                this.heroImages = (Video$HeroImages)o;
            }
            case "defaultTrailer": {
                this.supplementalVideos = (Video$SupplementalVideos)o;
            }
            case "billboardSummary": {
                this.billboardSummary = (BillboardSummary)o;
            }
            case "vertStoryArt": {
                this.vertStoryArtUrl = (Video$VerticalStoryArt)o;
            }
            case "interactiveMoments": {
                this.interactivePlaybackMoments = (InteractivePlaybackMoments)o;
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
            case "scenes": {
                this.scenes = (BranchMap<FalkorScene>)o;
            }
            case "postPlayExperience": {
                this.postPlayExperience = (Ref)o;
            }
            case "cast": {
                this.performers = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "castStills": {
                this.performerStills = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "mementoVideoSwatches": {
                this.mementoVideoSwatches = (UnsummarizedList<MementoVideoSwatch>)o;
            }
            case "interactive": {
                this.interactiveInfo = (InteractiveInfo)o;
            }
        }
    }
    
    @Override
    public void setUserRating(final float userRating) {
        this.rating.userRating = userRating;
    }
    
    @Override
    public String toString() {
        return "FalkorVideo [getId()=" + this.getId() + ", getTitle()=" + this.getTitle() + ", getType()=" + this.getType() + "]";
    }
}
