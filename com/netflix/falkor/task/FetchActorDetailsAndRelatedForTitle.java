// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.MementoVideoSwatch;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchActorDetailsAndRelatedForTitle extends CmpTask
{
    private final int maxItemsDetails;
    private final int maxItemsSwatches;
    private PQL mementoSwatchPQL;
    private PQL performerStillsPQL;
    private PQL performersPQL;
    private final String videoId;
    
    public FetchActorDetailsAndRelatedForTitle(final CachedModelProxy cachedModelProxy, final String videoId, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.maxItemsDetails = 7;
        this.maxItemsSwatches = 5;
        this.videoId = videoId;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.performerStillsPQL = PQL.create("videos", this.videoId, "castStills", PQL.range(0, 6), "summary");
        this.performersPQL = PQL.create("videos", this.videoId, "cast", PQL.range(0, 6), "detail");
        this.mementoSwatchPQL = PQL.create("videos", this.videoId, "mementoVideoSwatches", PQL.range(0, 4));
        list.add(this.performerStillsPQL);
        list.add(this.performersPQL);
        list.add(this.mementoSwatchPQL);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onActorDetailsAndRelatedFetched(null, null, status, null);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<FalkorActorStill> itemsAsList = this.modelProxy.getItemsAsList(this.performerStillsPQL);
        final List<FalkorPerson> itemsAsList2 = this.modelProxy.getItemsAsList(this.performersPQL);
        final List<MementoVideoSwatch> mementoAsSwatchAsList = this.modelProxy.getMementoAsSwatchAsList(this.mementoSwatchPQL);
        if (browseAgentCallback != null) {
            browseAgentCallback.onActorDetailsAndRelatedFetched(itemsAsList2, mementoAsSwatchAsList, CommonStatus.OK, itemsAsList);
        }
    }
}
