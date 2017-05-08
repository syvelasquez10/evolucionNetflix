// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.Iterator;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.falkor.PQL;
import java.util.List;

public class FetchEpisodesTask extends CmpTask
{
    private static final List<PQL> FETCH_EPISODES_LEAF_TYPES;
    private final int fromEpisode;
    private final String id;
    private final int toEpisode;
    private final VideoType type;
    
    static {
        FETCH_EPISODES_LEAF_TYPES = CmpUtils.FETCH_EPISODES_LEAF_TYPES;
    }
    
    public FetchEpisodesTask(final CachedModelProxy cachedModelProxy, final String id, final VideoType type, final int fromEpisode, final int toEpisode, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.id = id;
        this.type = type;
        this.fromEpisode = fromEpisode;
        this.toEpisode = toEpisode;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create(this.type.getValue(), this.id, "episodes", PQL.range(this.fromEpisode, this.toEpisode), FetchEpisodesTask.FETCH_EPISODES_LEAF_TYPES));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onEpisodesFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<EpisodeDetails> itemsAsList = this.modelProxy.getItemsAsList(cachedModelProxy$GetResult.pqls);
        if (itemsAsList != null) {
            for (final EpisodeDetails episodeDetails : itemsAsList) {
                if (episodeDetails != null && episodeDetails instanceof FalkorVideo) {
                    final FalkorVideo falkorVideo = (FalkorVideo)episodeDetails;
                    this.modelProxy.updateBookmarkIfExists(falkorVideo.getPlayable().getPlayableId(), falkorVideo.getBookmark());
                }
            }
        }
        browseAgentCallback.onEpisodesFetched(itemsAsList, CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldCollapseMissingPql() {
        return true;
    }
}
