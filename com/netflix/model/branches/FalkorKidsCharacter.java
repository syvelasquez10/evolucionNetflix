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
import java.util.Collections;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.Video;
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
import java.util.Comparator;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.BasicVideo;
import com.netflix.model.BaseFalkorObject;

public class FalkorKidsCharacter extends BaseFalkorObject implements BasicVideo, Playable, KidsCharacterDetails, FalkorObject
{
    private static final Comparator<FalkorVideo> REVERSE_SORT_BY_YEAR;
    private static final String TAG = "FalkorKidsCharacter";
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
    
    private String getCharacterSquareUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getSquareUrl();
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
        return this.getWatchNextVideo().getSquareUrl();
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
    public String getBifUrl() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        if (watchNextDetails == null) {
            return null;
        }
        return watchNextDetails.bifUrl;
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
    public String getCharacterName() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getTitle();
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
    public List<Video> getGallery() {
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
    public int getSeasonNumber() {
        if (!VideoType.EPISODE.equals(this.getWatchNextType()) || this.getWatchNextVideoAsEpisode() == null) {
            return 0;
        }
        return this.getWatchNextVideoAsEpisode().getSeasonNumber();
    }
    
    @Override
    public String getSquareUrl() {
        if (this.kidsSummary == null) {
            return null;
        }
        return this.kidsSummary.getSquareUrl();
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
            return this.getCharacterSquareUrl();
        }
        return this.getWatchNextSquareUrl();
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
    public boolean isNextPlayableEpisode() {
        final Video$Detail watchNextDetails = this.getWatchNextDetails();
        return watchNextDetails != null && watchNextDetails.isNextPlayableEpisode;
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
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {}
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
