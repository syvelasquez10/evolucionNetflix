// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchLoMosTask extends CmpTask
{
    private final int fromLomo;
    private final int toLomo;
    
    public FetchLoMosTask(final CachedModelProxy cachedModelProxy, final int fromLomo, final int toLomo, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.fromLomo = fromLomo;
        this.toLomo = toLomo;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("lolomo", PQL.range(this.fromLomo, this.toLomo), "summary"));
        list.add(PQL.create("lolomo", PQL.range(this.fromLomo, this.toLomo), PQL.range(LomoConfig.computeNumVideosToFetchPerBatch(this.modelProxy.getContext(), LoMoType.STANDARD) - 1), "summary"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onLoMosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onLoMosFetched(this.modelProxy.getLists(cachedModelProxy$GetResult.pqls), CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldCollapseMissingPql() {
        return true;
    }
}
