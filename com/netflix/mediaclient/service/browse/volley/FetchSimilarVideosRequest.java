// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSimilarVideosRequest extends FalcorVolleyWebClientRequest<VideoList>
{
    private static final String TAG = "nf_fetch_sims_request";
    private final int from;
    private final String id;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int to;
    private final SimilarRequestType type;
    
    private FetchSimilarVideosRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final SimilarRequestType type, final String id, final int to, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.id = id;
        final String escapeJsonChars = StringUtils.escapeJsonChars(id);
        this.type = type;
        this.from = 0;
        this.to = to;
        this.pqlQuery = String.format("['%s', '%s', 'relatedVideos', {'from':%d, 'to':%d}, 'summary']", type.keyName, escapeJsonChars, this.from, to);
        if (Log.isLoggable("nf_fetch_sims_request", 2)) {
            Log.v("nf_fetch_sims_request", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameGet();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onSimilarVideosFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final VideoList list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSimilarVideosFetched(list, 0);
        }
    }
    
    @Override
    protected VideoList parseFalcorResponse(String string) throws FalcorParseException, FalcorServerException {
        final com.netflix.mediaclient.service.webclient.model.VideoList list = new com.netflix.mediaclient.service.webclient.model.VideoList();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_fetch_sims_request", (String)string);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
            try {
                final JsonObject asJsonObject = dataObj.getAsJsonObject(this.type.keyName).getAsJsonObject(this.id).getAsJsonObject("relatedVideos");
                for (int i = this.from; i <= this.to; ++i) {
                    string = Integer.toString(i);
                    if (asJsonObject.has((String)string)) {
                        string = FalcorParseUtils.getPropertyObject(asJsonObject.getAsJsonObject((String)string), "summary", Video.Summary.class);
                        list.add((com.netflix.mediaclient.servicemgr.Video)string);
                        Log.v("nf_fetch_sims_request", "Found video: " + ((Video.Summary)string).getTitle());
                    }
                }
            }
            catch (Exception ex) {
                Log.v("nf_fetch_sims_request", "String response to parse = " + (String)string);
                throw new FalcorParseException("response missing expected json objects", ex);
            }
        }
        return list;
    }
    
    public static class FetchSimilarVideosForPersonRequest extends FetchSimilarVideosRequest
    {
        public FetchSimilarVideosForPersonRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
            super(context, configurationAgentInterface, SimilarRequestType.PEOPLE, s, n, browseAgentCallback, null);
        }
    }
    
    public static class FetchSimilarVideosForQuerySuggestionRequest extends FetchSimilarVideosRequest
    {
        public FetchSimilarVideosForQuerySuggestionRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
            super(context, configurationAgentInterface, SimilarRequestType.QUERY_SUGGESTION, s, n, browseAgentCallback, null);
        }
    }
    
    private enum SimilarRequestType
    {
        PEOPLE("people"), 
        QUERY_SUGGESTION("suggestions");
        
        public final String keyName;
        
        private SimilarRequestType(final String keyName) {
            this.keyName = keyName;
        }
    }
}
