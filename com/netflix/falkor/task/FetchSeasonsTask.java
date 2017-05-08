// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchSeasonsTask extends CmpTask
{
    private final int fromSeason;
    private final String showId;
    private final int toSeason;
    
    public FetchSeasonsTask(final CachedModelProxy cachedModelProxy, final String showId, final int fromSeason, final int toSeason, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.showId = showId;
        this.fromSeason = fromSeason;
        this.toSeason = toSeason;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("shows", this.showId, "seasons", "summary"));
        list.add(PQL.create("shows", this.showId, "seasons", PQL.range(this.fromSeason, this.toSeason), "detail"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onSeasonsFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onSeasonsFetched(this.modelProxy.getItemsAsList(cachedModelProxy$GetResult.pqls), CommonStatus.OK);
    }
}
