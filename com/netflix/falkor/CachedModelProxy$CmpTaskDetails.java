// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;

public interface CachedModelProxy$CmpTaskDetails
{
    void buildPqlList(final List<PQL> p0);
    
    void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
    
    void fetchResultsAndCallbackForSuccess(final CachedModelProxy p0, final BrowseAgentCallback p1, final CachedModelProxy$GetResult p2);
    
    List<DataUtil$StringPair> getOptionalRequestParams();
    
    boolean shouldCollapseMissingPql();
    
    boolean shouldSkipCache();
    
    boolean shouldUseAuthorization();
    
    boolean shouldUseCacheOnly();
    
    boolean shouldUseCallMethod();
}
