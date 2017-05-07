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
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorWebClientRequest;

public class FetchVideosRequest extends FalkorWebClientRequest<List<Video>>
{
    public FetchVideosRequest(final Context context, final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult, final BrowseAgentCallback browseAgentCallback) {
        super(context, cachedModelProxy, getResult, browseAgentCallback);
    }
    
    @Override
    protected List<Video> extractResultsFromCache(final CachedModelProxy<?> cachedModelProxy, final CachedModelProxy.GetResult getResult) {
        return (List<Video>)cachedModelProxy.getVideos(getResult.pqls);
    }
    
    @Override
    protected void onFailure(final Status status) {
        this.getCallback().onVideosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void onSuccess(final List<Video> list) {
        this.getCallback().onVideosFetched(list, CommonStatus.OK);
    }
}
