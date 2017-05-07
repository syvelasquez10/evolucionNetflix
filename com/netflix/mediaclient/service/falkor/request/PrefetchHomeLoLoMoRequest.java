// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor.request;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorWebClientRequest;

public class PrefetchHomeLoLoMoRequest extends FalkorWebClientRequest<String>
{
    private static final String TAG = "PrefetchHomeLoLoMoRequest";
    
    public PrefetchHomeLoLoMoRequest(final Context context, final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult, final BrowseAgentCallback browseAgentCallback) {
        super(context, cachedModelProxy, getResult, browseAgentCallback);
    }
    
    @Override
    protected String extractResultsFromCache(final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult) {
        return "";
    }
    
    @Override
    protected void onFailure(final Status status) {
        Log.d("PrefetchHomeLoLoMoRequest", "prefetch finished onFailure");
        this.getCallback().onLoLoMoPrefetched(status);
    }
    
    @Override
    protected void onSuccess(final String s) {
        Log.d("PrefetchHomeLoLoMoRequest", "prefetch finished onSuccess");
        this.getCallback().onLoLoMoPrefetched(CommonStatus.OK);
    }
}
