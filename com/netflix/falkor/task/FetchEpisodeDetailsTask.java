// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchEpisodeDetailsTask extends CmpTask
{
    private final String episodeId;
    private final String scene;
    
    public FetchEpisodeDetailsTask(final CachedModelProxy cachedModelProxy, final String episodeId, final String scene, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.episodeId = episodeId;
        this.scene = scene;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("episodes", this.episodeId, PQL.array("detail", "summary", "bookmark", "offlineAvailable", "rating")));
        if (StringUtils.isNotEmpty(this.scene)) {
            list.add(CmpUtils.buildScenePql(VideoType.EPISODE.getValue(), this.episodeId, this.scene));
        }
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onEpisodeDetailsFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final EpisodeDetails episodeDetails = (EpisodeDetails)this.modelProxy.getValue(PQL.create("episodes", this.episodeId));
        if (episodeDetails != null && episodeDetails instanceof FalkorVideo) {
            this.modelProxy.updateBookmarkIfExists(this.episodeId, ((FalkorVideo)episodeDetails).getBookmark());
        }
        browseAgentCallback.onEpisodeDetailsFetched(episodeDetails, CommonStatus.OK);
    }
}
