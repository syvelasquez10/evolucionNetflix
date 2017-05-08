// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public final class CmpTaskWrapper extends CmpTask
{
    private final CmpTaskDetails taskDetails;
    
    public CmpTaskWrapper(final CachedModelProxy cachedModelProxy, final CmpTaskDetails taskDetails, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.taskDetails = taskDetails;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.taskDetails.buildPqlList(list);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        this.taskDetails.callbackForFailure(browseAgentCallback, status);
    }
    
    @Override
    protected void customHandleResponse(final JsonObject jsonObject) {
        this.taskDetails.customHandleResponse(jsonObject);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        this.taskDetails.fetchResultsAndCallbackForSuccess(this.modelProxy, browseAgentCallback, cachedModelProxy$GetResult);
    }
    
    @Override
    protected List<DataUtil$StringPair> getOptionalRequestParams() {
        return this.taskDetails.getOptionalRequestParams();
    }
    
    @Override
    protected boolean shouldCollapseMissingPql(final List<PQL> list) {
        return this.taskDetails.shouldCollapseMissingPql(list);
    }
    
    @Override
    protected boolean shouldCustomHandleResponse() {
        return this.taskDetails.shouldCustomHandleResponse();
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return this.taskDetails.shouldSkipCache();
    }
    
    @Override
    protected boolean shouldUseAuthorization() {
        return this.taskDetails.shouldUseAuthorization();
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.taskDetails.shouldUseCacheOnly();
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return this.taskDetails.shouldUseCallMethod();
    }
}
