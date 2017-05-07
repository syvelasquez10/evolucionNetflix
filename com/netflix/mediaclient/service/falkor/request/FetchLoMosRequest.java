// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor.request;

import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collection;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorWebClientRequest;

public class FetchLoMosRequest extends FalkorWebClientRequest<List<LoMo>>
{
    public FetchLoMosRequest(final Context context, final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult, final BrowseAgentCallback browseAgentCallback) {
        super(context, cachedModelProxy, getResult, browseAgentCallback);
    }
    
    @Override
    protected List<LoMo> extractResultsFromCache(final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult) {
        return (List<LoMo>)cachedModelProxy.getLomos(getResult.pqls);
    }
    
    @Override
    protected void onFailure(final Status status) {
        this.getCallback().onLoMosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void onSuccess(final List<LoMo> list) {
        this.getCallback().onLoMosFetched(list, CommonStatus.OK);
    }
}
