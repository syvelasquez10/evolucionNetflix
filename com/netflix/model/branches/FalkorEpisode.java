// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.Video$Detail;
import com.netflix.falkor.PQL;
import android.util.Log;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.leafs.Episode$Detail;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.Playable;

public class FalkorEpisode extends FalkorVideo implements Playable, EpisodeDetails, FalkorObject
{
    private static final String TAG = "FalkorEpisode";
    public Episode$Detail episodeDetail;
    
    public FalkorEpisode(final ModelProxy<? extends BranchNode> modelProxy) {
        super(modelProxy);
    }
    
    private FalkorVideo getShow() {
        final String showId = this.getShowId();
        if (showId == null) {
            Log.w("FalkorEpisode", "Can't get show because show ID is null");
            return null;
        }
        return (FalkorVideo)this.getModelProxy().getValue(PQL.create("shows", showId, "summary"));
    }
    
    @Override
    public boolean canBeSharedOnFacebook() {
        final FalkorVideo show = this.getShow();
        return show != null && show.canBeSharedOnFacebook();
    }
    
    @Override
    public String getAvailabilityDateMessage() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getAvailabilityDateMessage();
    }
    
    @Override
    public int getBookmarkPosition() {
        if (this.bookmark == null) {
            return 0;
        }
        return this.bookmark.getBookmarkPosition();
    }
    
    @Override
    public String getCatalogIdUrl() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowRestUrl();
    }
    
    @Override
    protected Episode$Detail getDetail() {
        return this.episodeDetail;
    }
    
    @Override
    public String getEpisodeIdUrl() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.restUrl;
    }
    
    @Override
    public int getEpisodeNumber() {
        if (this.episodeDetail == null) {
            return -1;
        }
        return this.episodeDetail.getEpisodeNumber();
    }
    
    @Override
    public String getId() {
        if (super.getId() != null) {
            return super.getId();
        }
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getId();
    }
    
    @Override
    public String getNextEpisodeId() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getNextEpisodeId();
    }
    
    @Override
    public String getNextEpisodeTitle() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getNextEpisodeTitle();
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("detail".equals(s)) {
            return this.episodeDetail = new Episode$Detail();
        }
        return super.getOrCreate(s);
    }
    
    @Override
    public String getParentId() {
        return this.getShowId();
    }
    
    @Override
    public String getParentTitle() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowTitle();
    }
    
    @Override
    public String getPlayableId() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getId();
    }
    
    @Override
    public String getPlayableTitle() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getTitle();
    }
    
    @Override
    public String getSeasonId() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getSeasonId();
    }
    
    @Override
    public int getSeasonNumber() {
        if (this.episodeDetail == null) {
            return -1;
        }
        return this.episodeDetail.getSeasonNumber();
    }
    
    @Override
    public String getShowId() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowId();
    }
    
    public String getShowTitle() {
        if (this.episodeDetail == null) {
            return null;
        }
        return this.episodeDetail.getShowTitle();
    }
    
    @Override
    public VideoType getType() {
        return VideoType.EPISODE;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.episodeDetail != null && this.episodeDetail.isAutoPlayEnabled();
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.episodeDetail != null && this.episodeDetail.isNextPlayableEpisode();
    }
    
    @Override
    public boolean isPinProtected() {
        return this.episodeDetail != null && this.episodeDetail.isPinProtected();
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return true;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("detail".equals(s)) {
            this.episodeDetail = (Episode$Detail)o;
            return;
        }
        super.set(s, o);
    }
}
