// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchAdvisoriesTask extends CmpTask
{
    private PQL pql;
    
    public FetchAdvisoriesTask(final CachedModelProxy cachedModelProxy, final String s, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.pql = PQL.create("videos", s, "advisories");
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(this.pql);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onAdvisoriesFetched(null, status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final FalkorVideo falkorVideo = (FalkorVideo)this.modelProxy.getVideo(this.pql);
        if (falkorVideo == null || StringUtils.isEmpty(falkorVideo.getId())) {
            browseAgentCallback.onAdvisoriesFetched(null, CommonStatus.INT_ERR_ADVIS_VIDEO_ID_NULL);
            return;
        }
        browseAgentCallback.onAdvisoriesFetched(falkorVideo.getAdvisories(), CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return true;
    }
}
