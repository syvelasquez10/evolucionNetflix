// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;

public abstract class RefreshLomoTask extends CmpTask
{
    private final String lolomoId;
    protected final String lomoId;
    protected final String lomoIndex;
    
    public RefreshLomoTask(final CachedModelProxy cachedModelProxy, final String lolomoId, final String lomoId, final String lomoIndex) {
        super(cachedModelProxy, null);
        this.lolomoId = lolomoId;
        this.lomoId = lomoId;
        this.lomoIndex = lomoIndex;
    }
    
    private void doDebugValidation() {
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.doDebugValidation();
        list.add(PQL.create("lolomos", this.lolomoId, "refreshList"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        Log.d("CachedModelProxy", "RefreshLomoTask finished onFailure statusCode=" + status);
        this.notifyOfRefresh();
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        Log.d("CachedModelProxy", "RefreshLomoTask finished onSuccess");
        this.notifyOfRefresh();
    }
    
    protected void notifyOfRefresh() {
    }
    
    @Override
    protected boolean shouldUseCallMethod() {
        return true;
    }
}
