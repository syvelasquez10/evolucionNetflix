// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.falkor.CachedModelProxy;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.PQL;
import java.util.List;

public interface CmpTaskDetails
{
    void buildPqlList(final List<PQL> p0);
    
    void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
    
    void customHandleResponse(final JsonObject p0);
    
    void fetchResultsAndCallbackForSuccess(final CachedModelProxy p0, final BrowseAgentCallback p1, final CachedModelProxy$GetResult p2);
    
    List<DataUtil$StringPair> getOptionalRequestParams();
    
    boolean shouldCollapseMissingPql(final List<PQL> p0);
    
    boolean shouldCustomHandleResponse();
    
    boolean shouldSkipCache();
    
    boolean shouldUseAuthorization();
    
    boolean shouldUseCacheOnly();
    
    boolean shouldUseCallMethod();
}
