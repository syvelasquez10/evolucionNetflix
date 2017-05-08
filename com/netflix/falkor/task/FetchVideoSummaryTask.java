// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.model.leafs.Video$Summary;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchVideoSummaryTask extends CmpTask
{
    private final PQL pql;
    
    public FetchVideoSummaryTask(final CachedModelProxy cachedModelProxy, final String s, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.pql = PQL.create("videos", s, "summary");
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(this.pql);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onVideoSummaryFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorVideo falkorVideo = (FalkorVideo)this.modelProxy.getVideo(this.pql);
        if (falkorVideo == null || StringUtils.isEmpty(falkorVideo.getId())) {
            final StringBuilder append = new StringBuilder().append("SPY-7519: FetchVideoSummaryTask got weird movie id: ");
            String id;
            if (falkorVideo == null) {
                id = "null";
            }
            else {
                id = falkorVideo.getId();
            }
            final String string = append.append(id).toString();
            this.modelProxy.logHandledException(string);
            Log.e("CachedModelProxy", string);
            browseAgentCallback.onVideoSummaryFetched(null, CommonStatus.INT_ERR_CMP_RESP_NULL);
            return;
        }
        browseAgentCallback.onVideoSummaryFetched(falkorVideo.getSummary(), CommonStatus.OK);
    }
}
