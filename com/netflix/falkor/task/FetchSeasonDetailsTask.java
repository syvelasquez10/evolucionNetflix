// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchSeasonDetailsTask extends CmpTask
{
    private final String seasonId;
    
    public FetchSeasonDetailsTask(final CachedModelProxy cachedModelProxy, final String seasonId, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.seasonId = seasonId;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("videos", this.seasonId, "detail"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onSeasonDetailsFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onSeasonDetailsFetched((SeasonDetails)this.modelProxy.getValue(PQL.create("seasons", this.seasonId)), CommonStatus.OK);
    }
}
