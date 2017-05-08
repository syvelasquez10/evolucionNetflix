// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchNonMemberVideosTask extends CmpTask
{
    private final int toVideo;
    private final boolean useCacheOnly;
    
    public FetchNonMemberVideosTask(final CachedModelProxy cachedModelProxy, final int toVideo, final boolean useCacheOnly, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.toVideo = toVideo;
        this.useCacheOnly = useCacheOnly;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("nonMemberVideos", PQL.range(this.toVideo), PQL.array("summary", "detail")));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        if (browseAgentCallback != null) {
            browseAgentCallback.onVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        if (this.useCacheOnly && browseAgentCallback != null) {
            browseAgentCallback.onVideosFetched(this.modelProxy.getItemsAsList(cachedModelProxy$GetResult.pqls), CommonStatus.OK);
            return;
        }
        PreAppAgent.informNonMemberVideosUpdated(this.modelProxy.getContext());
    }
    
    @Override
    protected boolean shouldUseAuthorization() {
        return false;
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.useCacheOnly;
    }
}
