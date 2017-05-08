// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.falkor.CachedModelProxy;
import java.util.Collections;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.ArrayList;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.falkor.Undefined;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.Video$Detail;
import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.leafs.KidsCharacter$Summary;
import com.netflix.model.leafs.KidsCharacter$Detail;
import com.netflix.model.leafs.Video$Advisories;
import java.util.Comparator;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.BasicVideo;
import com.netflix.model.BaseFalkorObject;

public class FalkorKidsCharacter extends BaseFalkorObject implements BasicVideo, Playable, KidsCharacterDetails, FalkorObject
{
    private static final Comparator<FalkorVideo> REVERSE_SORT_BY_YEAR;
    private static final String TAG = "FalkorKidsCharacter";
    private Video$Advisories advisories;
    public KidsCharacter$Detail kidsDetail;
    public KidsCharacter$Summary kidsSummary;
    public SummarizedList<Ref, TrackableListSummary> videoGallery;
    public Ref watchNext;
    
    static {
        REVERSE_SORT_BY_YEAR = new FalkorKidsCharacter$1();
    }
    
    public FalkorKidsCharacter(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    private String getCharacterFullBodyUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getCharacterImageUrl();
    }
    
    private boolean getHasWatchedRecently() {
        return this.kidsDetail != null && this.kidsDetail.hasWatchedRecently;
    }
    
    private TrackableListSummary getVideoGallerySummary() {
        if (this.videoGallery == null) {
            return null;
        }
        return (TrackableListSummary)this.videoGallery.get("summary");
    }
    
    private Video$Bookmark getWatchNextBookmark() {
        final FalkorVideo watchNextVideo = this.getWatchNextVideo();
        if (watchNextVideo == null) {
            return null;
        }
        return watchNextVideo.bookmark;
    }
    
    private Video$Detail getWatchNextDetails() {
        final FalkorVideo watchNextVideo = this.getWatchNextVideo();
        if (watchNextVideo == null) {
            return null;
        }
        return watchNextVideo.getDetail();
    }
    
    private String getWatchNextSquareUrl() {
        if (this.getWatchNextVideo() == null) {
            return null;
        }
        return this.getWatchNextVideo().getStoryUrl();
    }
    
    private VideoType getWatchNextType() {
        final FalkorVideo watchNextVideo = this.getWatchNextVideo();
        if (watchNextVideo == null) {
            return VideoType.UNKNOWN;
        }
        return watchNextVideo.getType();
    }
    
    private FalkorVideo getWatchNextVideo() {
        if (this.watchNext == null) {
            return null;
        }
        return (FalkorVideo)this.watchNext.getValue(this.getModelProxy());
    }
    
    private FalkorEpisode getWatchNextVideoAsEpisode() {
        final FalkorVideo watchNextVideo = this.getWatchNextVideo();
        if (watchNextVideo == null) {
            return null;
        }
        return (FalkorEpisode)watchNextVideo;
    }
    
    private Boolean isFirstPlay() {
        boolean b = true;
        if (this.kidsDetail == null) {
            return true;
        }
        if (this.getHasWatchedRecently() || (VideoType.EPISODE.equals(this.getWatchNextType()) && this.getEpisodeNumber() != 1) || this.getPlayableBookmarkPosition() > 0) {
            b = false;
        }
        return b;
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return null;
            }
            case "advisories": {
                return this.advisories;
            }
            case "summary": {
                return this.kidsSummary;
            }
            case "detail": {
                return this.kidsDetail;
            }
            case "kubrick": {
                return Undefined.getInstance();
            }
            case "rating": {
                return Undefined.getInstance();
            }
            case "watchNext": {
                return this.watchNext;
            }
            case "gallery": {
                return this.videoGallery;
            }
        }
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
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return -1;
        }
        return watchNextDetails.autoPlayMaxCount;
    }
    
    @Override
    public String getBifUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.bifUrl;
    }
    
    @Override
    public String getBoxartImageTypeIdentifier() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getBoxartImageTypeIdentifier();
    }
    
    @Override
    public String getBoxshotUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getBoxshotUrl();
    }
    
    @Override
    public String getCatalogIdUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
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
        return this.kidsSummary.getId();
    }
    
    @Override
    public String getCharacterImageUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getCharacterImageUrl();
    }
    
    @Override
    public String getCharacterName() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getTitle();
    }
    
    @Override
    public int getEndtime() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return -1;
        }
        return watchNextDetails.endtime;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType()) || this.getWatchNextVideoAsEpisode() == null) {
            return 0;
        }
        return this.getWatchNextVideoAsEpisode().getEpisodeNumber();
    }
    
    @Override
    public VideoType getErrorType() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getErrorType();
    }
    
    @Override
    public long getExpirationTime() {
        return -1L;
    }
    
    @Override
    public List<Video> getGallery() {
        if (StringUtils.isEmpty(this.getCharacterId())) {
            Log.e("FalkorKidsCharacter", "getGallery() kidsCharacterId is null");
            return (List<Video>)Collections.EMPTY_LIST;
        }
        final List<FalkorVideo> itemsAsList = (List<FalkorVideo>)this.getModelProxy().getItemsAsList(CachedModelProxy.buildKidsCharacterVideoGalleryPql(this.getCharacterId()));
        final ArrayList list = new ArrayList<Object>(itemsAsList.size());
        final ArrayList list2 = new ArrayList<Object>(itemsAsList.size());
        for (final FalkorVideo falkorVideo : itemsAsList) {
            if (VideoType.SHOW.equals(falkorVideo.getType())) {
                list.add(falkorVideo);
            }
            else if (VideoType.MOVIE.equals(falkorVideo.getType())) {
                list2.add(falkorVideo);
            }
            else {
                Log.w("FalkorKidsCharacter", String.format("unexpected videoType=%s in getGallery", falkorVideo.getType()));
            }
        }
        Collections.sort((List<Object>)list, (Comparator<? super Object>)FalkorKidsCharacter.REVERSE_SORT_BY_YEAR);
        Collections.sort((List<Object>)list2, (Comparator<? super Object>)FalkorKidsCharacter.REVERSE_SORT_BY_YEAR);
        final ArrayList list3 = new ArrayList(itemsAsList.size());
        list3.addAll((Collection)list);
        list3.addAll((Collection)list2);
        return (List<Video>)list3;
    }
    
    @Override
    public String getGalleryRequestId() {
        final TrackableListSummary videoGallerySummary = this.getVideoGallerySummary();
        if (videoGallerySummary == null) {
            return null;
        }
        return videoGallerySummary.getRequestId();
    }
    
    @Override
    public int getGalleryTrackId() {
        final TrackableListSummary videoGallerySummary = this.getVideoGallerySummary();
        if (videoGallerySummary == null) {
            return 0;
        }
        return videoGallerySummary.getTrackId();
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.hiResHorzUrl;
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.mdxVertUrl;
    }
    
    @Override
    public String getHorzDispSmallUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getHorzDispSmallUrl();
    }
    
    @Override
    public String getHorzDispUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.horzDispUrl;
    }
    
    @Override
    public String getId() {
        return this.getCharacterId();
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.advisories != null) {
            set.add("advisories");
        }
        if (this.kidsSummary != null) {
            set.add("summary");
        }
        if (this.kidsDetail != null) {
            set.add("detail");
        }
        if (this.watchNext != null) {
            set.add("watchNext");
        }
        if (this.videoGallery != null) {
            set.add("gallery");
        }
        return set;
    }
    
    @Override
    public int getLogicalStart() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return 0;
        }
        return watchNextDetails.logicalStart;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if (Log.isLoggable()) {
            Log.v("FalkorKidsCharacter", "Creating leaf for key: " + s);
        }
        switch (s) {
            default: {
                return null;
            }
            case "advisories": {
                return this.advisories = new Video$Advisories();
            }
            case "summary": {
                return this.kidsSummary = new KidsCharacter$Summary();
            }
            case "detail": {
                return this.kidsDetail = new KidsCharacter$Detail();
            }
            case "kubrick": {
                return Undefined.getInstance();
            }
            case "rating": {
                return Undefined.getInstance();
            }
            case "watchNext": {
                return this.watchNext = new Ref();
            }
            case "gallery": {
                return this.videoGallery = new SummarizedList<Ref, TrackableListSummary>(Falkor$Creator.Ref, Falkor$Creator.TrackableListSummary);
            }
        }
    }
    
    @Override
    public String getParentId() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType()) || this.getWatchNextVideoAsEpisode() == null) {
            return null;
        }
        return this.getWatchNextVideoAsEpisode().getShowId();
    }
    
    @Override
    public String getParentTitle() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType()) || this.getWatchNextVideoAsEpisode() == null) {
            return null;
        }
        return this.getWatchNextVideoAsEpisode().getShowTitle();
    }
    
    @Override
    public Playable getPlayable() {
        return this;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        int bookmarkPosition;
        if (this.getWatchNextBookmark() == null) {
            bookmarkPosition = 0;
        }
        else {
            bookmarkPosition = this.getWatchNextBookmark().getBookmarkPosition();
        }
        return TimeUtils.computePlayPos(bookmarkPosition, this.getEndtime(), this.getRuntime());
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        if (this.getWatchNextBookmark() == null) {
            return 0L;
        }
        return this.getWatchNextBookmark().getLastModified();
    }
    
    @Override
    public String getPlayableId() {
        if (this.getWatchNextVideo() == null) {
            return null;
        }
        return this.getWatchNextVideo().getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (this.getWatchNextVideo() == null) {
            return null;
        }
        return this.getWatchNextVideo().getTitle();
    }
    
    @Override
    public int getRuntime() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return -1;
        }
        return watchNextDetails.runtime;
    }
    
    @Override
    public String getSeasonAbbrSeqLabel() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType())) {
            return null;
        }
        if (this.getWatchNextVideoAsEpisode() == null) {
            return "";
        }
        return this.getWatchNextVideoAsEpisode().getSeasonAbbrSeqLabel();
    }
    
    @Override
    public int getSeasonNumber() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType()) || this.getWatchNextVideoAsEpisode() == null) {
            return 0;
        }
        return this.getWatchNextVideoAsEpisode().getSeasonNumber();
    }
    
    @Override
    public String getStoryDispUrl() {
        throw new RuntimeException("Not implemented");
    }
    
    @Override
    public String getStoryUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.storyImgUrl;
    }
    
    @Override
    public String getSynopsis() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.synopsis;
    }
    
    @Override
    public String getTitle() {
        return this.getCharacterName();
    }
    
    @Override
    public String getTvCardUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getTvCardUrl();
    }
    
    @Override
    public VideoType getType() {
        if (this.kidsSummary == null) {
            return VideoType.UNKNOWN;
        }
        return this.kidsSummary.getType();
    }
    
    @Override
    public String getWatchNextDispUrl() {
        if (Log.isLoggable()) {
            Log.d("FalkorKidsCharacter", String.format("[%s %s], firstPlay:%b (watchedRecently:%b), S%d:E%d, pos:%d", this.getWatchNextType(), this.getPlayableId(), this.isFirstPlay(), this.getHasWatchedRecently(), this.getSeasonNumber(), this.getEpisodeNumber(), this.getPlayableBookmarkPosition()));
        }
        if (this.isFirstPlay()) {
            return this.getCharacterFullBodyUrl();
        }
        return this.getWatchNextSquareUrl();
    }
    
    public boolean hasWatched() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.hasWatched;
    }
    
    @Override
    public boolean isAdvisoryDisabled() {
        return false;
    }
    
    @Override
    public boolean isAgeProtected() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isAgeProtected;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isAutoPlayEnabled;
    }
    
    @Override
    public boolean isAvailableToStream() {
        final Playable playable = this.getPlayable();
        return playable != null && playable.isAvailableToStream();
    }
    
    @Override
    public boolean isExemptFromInterrupterLimit() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isExemptFromInterrupterLimit;
    }
    
    @Override
    public boolean isNSRE() {
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isNextPlayableEpisode;
    }
    
    @Override
    public boolean isOriginal() {
        return false;
    }
    
    @Override
    public boolean isPinProtected() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isPinProtected;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.getWatchNextVideo() != null && this.getWatchNextVideo().isEpisode();
    }
    
    @Override
    public boolean isPreRelease() {
        return false;
    }
    
    @Override
    public boolean isSupplementalVideo() {
        return false;
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {}
            case "advisories": {
                this.advisories = (Video$Advisories)o;
            }
            case "summary": {
                this.kidsSummary = (KidsCharacter$Summary)o;
            }
            case "detail": {
                this.kidsDetail = (KidsCharacter$Detail)o;
            }
            case "watchNext": {
                this.watchNext = (Ref)o;
            }
            case "gallery": {
                this.videoGallery = (SummarizedList<Ref, TrackableListSummary>)o;
            }
        }
    }
    
    @Override
    public String toString() {
        return "FalkorKidsCharacter [getType()=" + this.getType() + ", getCharacterId()=" + this.getCharacterId() + ", getCharacterTitle()=" + this.getCharacterName() + "]";
    }
}
