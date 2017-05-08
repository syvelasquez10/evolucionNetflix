// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public class FetchCwVideosTask extends CmpTask
{
    private final int fromVideo;
    private final CmpTaskMode taskMode;
    private final int toVideo;
    
    public FetchCwVideosTask(final CachedModelProxy cachedModelProxy, final int fromVideo, final int toVideo, final CmpTaskMode taskMode, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.taskMode = taskMode;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        CmpUtils.buildCwPql(list, this.modelProxy.getCurrLolomoId(), this.fromVideo, this.toVideo);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onCWVideosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<CWVideo> itemsAsList = this.modelProxy.getItemsAsList(PQL.create("lolomo", "continueWatching", PQL.range(this.fromVideo, this.toVideo), "summary"));
        browseAgentCallback.onCWVideosFetched(itemsAsList, CommonStatus.OK);
        this.modelProxy.onCWVideosFetched(itemsAsList);
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return this.taskMode == CmpTaskMode.FROM_NETWORK;
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.taskMode == CmpTaskMode.FROM_CACHE_ONLY;
    }
}
