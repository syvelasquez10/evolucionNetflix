// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.netflix.mediaclient.util.DataUtil;
import android.content.Context;
import java.util.List;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;

public abstract class FalkorWebClientRequest<T> extends FalcorVolleyWebClientRequest<T>
{
    private final BrowseAgentCallback callback;
    private final CachedModelProxy<?> cmp;
    private final List<String> pqls;
    private final CachedModelProxy.GetResult result;
    
    protected FalkorWebClientRequest(final Context context, final CachedModelProxy<?> cmp, final CachedModelProxy.GetResult result, final BrowseAgentCallback callback) {
        super(context);
        this.cmp = cmp;
        this.result = result;
        this.callback = callback;
        this.pqls = DataUtil.createStringListFromList(result.missingPqls);
    }
    
    protected T extractResultsFromCache(final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult) {
        return null;
    }
    
    protected BrowseAgentCallback getCallback() {
        return this.callback;
    }
    
    @Override
    protected final List<String> getPQLQueries() {
        return this.pqls;
    }
    
    @Override
    protected final T parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        this.cmp.merge(s);
        return this.extractResultsFromCache(this.cmp, this.result);
    }
    
    @Override
    protected boolean shouldMaterializeRequest() {
        return true;
    }
}
