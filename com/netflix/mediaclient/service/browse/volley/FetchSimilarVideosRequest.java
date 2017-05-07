// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.SearchVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public abstract class FetchSimilarVideosRequest extends FalcorVolleyWebClientRequest<SearchVideoList>
{
    private static final String RELATED_VIDEOS = "relatedVideos";
    private static final String SEARCH = "search";
    private static final String STANDARD = "standard";
    private static final String SUMMARY = "summary";
    private static final String TAG = "nf_fetch_sims_request";
    protected final String escapedId;
    protected final String escapedOriginalTerm;
    protected final int from;
    protected String pqlQuery;
    protected String pqlQuery1;
    protected final BrowseAgentCallback responseCallback;
    protected final int to;
    protected final SimilarRequestType type;
    protected final String unescapedId;
    protected final String unescapedOriginalTerm;
    
    private FetchSimilarVideosRequest(final Context context, final SimilarRequestType type, final String unescapedId, final int to, final BrowseAgentCallback responseCallback, final String unescapedOriginalTerm) {
        super(context);
        this.responseCallback = responseCallback;
        this.unescapedId = unescapedId;
        this.escapedId = StringUtils.escapeJsonChars(unescapedId);
        this.unescapedOriginalTerm = unescapedOriginalTerm;
        this.escapedOriginalTerm = StringUtils.escapeJsonChars(unescapedOriginalTerm);
        this.type = type;
        this.from = 0;
        this.to = to;
        this.createPqls();
        if (Log.isLoggable("nf_fetch_sims_request", 2)) {
            Log.v("nf_fetch_sims_request", "PQL = " + this.pqlQuery);
            Log.v("nf_fetch_sims_request", "PQL1 = " + this.pqlQuery1);
        }
    }
    
    private void createPqls() {
        this.pqlQuery = String.format("['search', '%s', '%s', 'standard', 'relatedVideos', '%s', {'from':%d, 'to':%d}, ['summary','searchTitle']]", this.type.keyName, this.escapedOriginalTerm, this.escapedId, this.from, this.to);
        this.pqlQuery1 = String.format("['search', '%s', '%s', 'standard', 'relatedVideos', '%s', 'summary']", this.type.keyName, this.escapedOriginalTerm, this.escapedId);
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameGet();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSimilarVideosFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final SearchVideoList list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSimilarVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected SearchVideoList parseFalcorResponse(String o) throws FalcorParseException, FalcorServerException {
        final com.netflix.mediaclient.service.webclient.model.SearchVideoList list = new com.netflix.mediaclient.service.webclient.model.SearchVideoList();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_fetch_sims_request", (String)o);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
            try {
                final JsonObject asJsonObject = dataObj.getAsJsonObject("search").getAsJsonObject(this.type.keyName).getAsJsonObject(this.unescapedOriginalTerm).getAsJsonObject("standard").getAsJsonObject("relatedVideos").getAsJsonObject(this.unescapedId);
                list.setVideoListTrackable(FalcorParseUtils.getPropertyObject(asJsonObject, "summary", SearchTrackableListSummary.class));
                for (int i = this.from; i <= this.to; ++i) {
                    o = Integer.toString(i);
                    if (asJsonObject.has((String)o)) {
                        o = asJsonObject.getAsJsonObject((String)o);
                        final SearchVideo searchVideo = new SearchVideo();
                        searchVideo.summary = FalcorParseUtils.getPropertyObject((JsonObject)o, "summary", Video.Summary.class);
                        searchVideo.searchTitle = FalcorParseUtils.getPropertyObject((JsonObject)o, "searchTitle", Video.SearchTitle.class);
                        ((ArrayList<SearchVideo>)list).add(searchVideo);
                        Log.v("nf_fetch_sims_request", "Found video: " + searchVideo.summary.getTitle());
                    }
                }
            }
            catch (Exception ex) {
                Log.v("nf_fetch_sims_request", "String response to parse = " + (String)o);
                throw new FalcorParseException("response missing expected json objects", ex);
            }
        }
        return list;
    }
    
    public static class FetchSimilarVideosForPersonRequest extends FetchSimilarVideosRequest
    {
        public FetchSimilarVideosForPersonRequest(final Context context, final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
            super(context, SimilarRequestType.PEOPLE, s, n, browseAgentCallback, s2, null);
        }
    }
    
    public static class FetchSimilarVideosForQuerySuggestionRequest extends FetchSimilarVideosRequest
    {
        public FetchSimilarVideosForQuerySuggestionRequest(final Context context, final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
            super(context, SimilarRequestType.QUERY_SUGGESTION, s, n, browseAgentCallback, s2, null);
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
