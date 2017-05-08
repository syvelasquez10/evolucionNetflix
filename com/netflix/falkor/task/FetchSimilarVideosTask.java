// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.model.branches.SearchVideoListWrapper;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.falkor.PQL;

public class FetchSimilarVideosTask extends CmpTask
{
    private final String id;
    private final int numSims;
    private final String query;
    private PQL simsListPql;
    private PQL simsSummaryPql;
    private final Falkor$SimilarRequestType type;
    
    public FetchSimilarVideosTask(final CachedModelProxy cachedModelProxy, final Falkor$SimilarRequestType type, final String id, final int numSims, final String query, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.type = type;
        this.id = id;
        this.numSims = numSims;
        this.query = query;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.simsListPql = PQL.create("search", this.type.value, this.query, "standard", "relatedVideos", this.id, PQL.range(this.numSims), PQL.array("summary", "searchTitle"));
        this.simsSummaryPql = PQL.create("search", this.type.value, this.query, "standard", "relatedVideos", this.id, "summary");
        list.add(this.simsListPql);
        list.add(this.simsSummaryPql);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onSimilarVideosFetched((SearchVideoListProvider)new SearchVideoListWrapper((List)null, (SearchTrackable)null), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        browseAgentCallback.onSimilarVideosFetched((SearchVideoListProvider)new SearchVideoListWrapper((List)this.modelProxy.getItemsAsList(this.simsListPql), (SearchTrackable)this.modelProxy.getValue(this.simsSummaryPql)), CommonStatus.OK);
    }
}
