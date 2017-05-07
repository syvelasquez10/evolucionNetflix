// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import android.content.Context;

public class FetchSimilarVideosRequest$FetchSimilarVideosForQuerySuggestionRequest extends FetchSimilarVideosRequest
{
    public FetchSimilarVideosRequest$FetchSimilarVideosForQuerySuggestionRequest(final Context context, final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        super(context, FetchSimilarVideosRequest$SimilarRequestType.QUERY_SUGGESTION, s, n, browseAgentCallback, s2, null);
    }
}
