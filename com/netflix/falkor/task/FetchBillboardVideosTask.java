// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.text.TextUtils;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchBillboardVideosTask extends CmpTask
{
    private final int fromVideo;
    private final int toVideo;
    private final boolean useCacheOnly;
    
    public FetchBillboardVideosTask(final CachedModelProxy cachedModelProxy, final int fromVideo, final int toVideo, final boolean useCacheOnly, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.useCacheOnly = useCacheOnly;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        CmpUtils.buildBillboardPql(list, this.fromVideo, this.toVideo);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onBBVideosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<Billboard> itemsAsList = (List<Billboard>)this.modelProxy.getItemsAsList(PQL.create("lolomo", "billboard", "videoEvidence", PQL.range(this.toVideo), new String[] { "summary", "detail", "inQueue" }));
        final List<BillboardSummary> itemsAsList2 = (List<BillboardSummary>)this.modelProxy.getItemsAsList(PQL.create("lolomo", "billboard", "billboardData", PQL.range(this.toVideo), "billboardSummary"));
        for (int i = 0; i < itemsAsList.size(); ++i) {
            BillboardSummary billboardSummary = null;
            final FalkorVideo falkorVideo = (FalkorVideo)itemsAsList.get(i);
            if (i < itemsAsList2.size()) {
                billboardSummary = itemsAsList2.get(i);
            }
            if (billboardSummary != null) {
                falkorVideo.set("billboardSummary", (Object)billboardSummary);
                if (!TextUtils.isEmpty((CharSequence)billboardSummary.getMotionUrl())) {
                    itemsAsList.clear();
                    itemsAsList.add((Billboard)falkorVideo);
                    break;
                }
            }
        }
        browseAgentCallback.onBBVideosFetched(itemsAsList, CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.useCacheOnly;
    }
}
