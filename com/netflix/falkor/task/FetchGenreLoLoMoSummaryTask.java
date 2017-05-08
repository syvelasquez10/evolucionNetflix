// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.leafs.LoLoMoSummary;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchGenreLoLoMoSummaryTask extends CmpTask
{
    private final String category;
    
    public FetchGenreLoLoMoSummaryTask(final CachedModelProxy cachedModelProxy, final String category, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.category = category;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("topGenres", this.category, "summary"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onLoLoMoSummaryFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final LoLoMoSummary loLoMoSummary = (LoLoMoSummary)this.modelProxy.getValue(PQL.create("topGenres", this.category, "summary"));
        loLoMoSummary.setGenreId(this.category);
        browseAgentCallback.onLoLoMoSummaryFetched(loLoMoSummary, CommonStatus.OK);
    }
}
