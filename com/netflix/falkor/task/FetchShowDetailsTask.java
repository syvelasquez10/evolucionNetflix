// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.falkor.FalkorAgentStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collections;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchShowDetailsTask extends CmpTask
{
    private final String currentEpisodeId;
    private final boolean includeKubrick;
    private final boolean includeSeasonEpisodes;
    private final boolean includeSeasons;
    private PQL seasonsPql;
    private final String showId;
    
    public FetchShowDetailsTask(final CachedModelProxy cachedModelProxy, final String showId, final String currentEpisodeId, final boolean includeSeasons, final boolean includeKubrick, final boolean includeSeasonEpisodes, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.showId = showId;
        this.currentEpisodeId = currentEpisodeId;
        this.includeSeasons = includeSeasons;
        this.includeKubrick = includeKubrick;
        this.includeSeasonEpisodes = includeSeasonEpisodes;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        DPPrefetchABTestUtils.latchToPendingRequestsIfExists(this.showId);
        final List<String> singletonList = Collections.singletonList(this.showId);
        CmpUtils.buildShowDetailsPQL(list, singletonList, this.currentEpisodeId, this.includeSeasons, this.includeKubrick, this.includeSeasonEpisodes);
        this.seasonsPql = CmpUtils.getSeasonsPQL(singletonList);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        if (this.includeSeasons) {
            browseAgentCallback.onShowDetailsAndSeasonsFetched(null, null, status);
            return;
        }
        browseAgentCallback.onShowDetailsFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final ShowDetails showDetails = (ShowDetails)this.modelProxy.getVideo(PQL.create("shows", this.showId));
        if (this.includeSeasons) {
            browseAgentCallback.onShowDetailsAndSeasonsFetched(showDetails, this.modelProxy.getItemsAsList(this.seasonsPql), new FalkorAgentStatus(StatusCode.OK, this.isAllDataLocalToCache()));
            return;
        }
        browseAgentCallback.onShowDetailsFetched(showDetails, new FalkorAgentStatus(StatusCode.OK, this.isAllDataLocalToCache()));
    }
}
