// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.Iterator;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchPersonDetail extends CmpTask
{
    private final String actorId;
    PQL detailPQL;
    private final int maxItems;
    private final int maxStills;
    PQL performerStillsPQL;
    private final String videoId;
    
    public FetchPersonDetail(final CachedModelProxy cachedModelProxy, final String actorId, final BrowseAgentCallback browseAgentCallback, final String videoId) {
        super(cachedModelProxy, browseAgentCallback);
        this.maxItems = 5;
        this.maxStills = 7;
        this.actorId = actorId;
        this.videoId = videoId;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.performerStillsPQL = PQL.create("videos", this.videoId, "castStills", PQL.range(0, 6), "summary");
        list.add(this.detailPQL = PQL.create("people", this.actorId, "detail"));
        list.add(this.performerStillsPQL);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onPersonDetailFetched(null, null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorPerson falkorPerson = (FalkorPerson)this.modelProxy.getValue(this.detailPQL);
        final List<FalkorActorStill> itemsAsList = this.modelProxy.getItemsAsList(this.performerStillsPQL);
    Label_0104:
        while (true) {
            if (itemsAsList != null) {
                for (final FalkorActorStill falkorActorStill : itemsAsList) {
                    if (falkorActorStill.summary.videoId != null && this.videoId.compareTo(falkorActorStill.summary.videoId) == 0 && this.actorId.compareTo(falkorActorStill.summary.personId) == 0) {
                        break Label_0104;
                    }
                }
            }
            Label_0116: {
                break Label_0116;
                final FalkorActorStill falkorActorStill;
                browseAgentCallback.onPersonDetailFetched(falkorPerson, falkorActorStill, CommonStatus.OK);
                return;
            }
            FalkorActorStill falkorActorStill = null;
            continue Label_0104;
        }
    }
}
