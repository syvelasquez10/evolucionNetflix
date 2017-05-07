// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.model.branches.Video$SearchTitle;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.service.webclient.model.SearchVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.SearchVideoList;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public abstract class FetchSimilarVideosRequest extends FalkorVolleyWebClientRequest<SearchVideoListProvider>
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
    protected final FetchSimilarVideosRequest$SimilarRequestType type;
    protected final String unescapedId;
    protected final String unescapedOriginalTerm;
    
    private FetchSimilarVideosRequest(final Context context, final FetchSimilarVideosRequest$SimilarRequestType type, final String unescapedId, final int to, final BrowseAgentCallback responseCallback, final String unescapedOriginalTerm) {
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
    
    private void checkForNullTrackables(final SearchVideoList list) {
    }
    
    private void createPqls() {
        this.pqlQuery = String.format("['search', '%s', '%s', 'standard', 'relatedVideos', '%s', {'from':%d, 'to':%d}, ['summary','searchTitle']]", this.type.keyName, this.escapedOriginalTerm, this.escapedId, this.from, this.to);
        this.pqlQuery1 = String.format("['search', '%s', '%s', 'standard', 'relatedVideos', '%s', 'summary']", this.type.keyName, this.escapedOriginalTerm, this.escapedId);
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
    protected void onSuccess(final SearchVideoListProvider searchVideoListProvider) {
        if (this.responseCallback != null) {
            this.responseCallback.onSimilarVideosFetched(searchVideoListProvider, CommonStatus.OK);
        }
    }
    
    @Override
    protected SearchVideoListProvider parseFalkorResponse(String videosList) {
        final SearchVideoList list = new SearchVideoList();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_fetch_sims_request", videosList);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            this.checkForNullTrackables(list);
            return list;
        }
        try {
            final JsonObject asJsonObject = dataObj.getAsJsonObject("search").getAsJsonObject(this.type.keyName).getAsJsonObject(this.unescapedOriginalTerm).getAsJsonObject("standard").getAsJsonObject("relatedVideos").getAsJsonObject(this.unescapedId);
            list.setVideoListTrackable(FalkorParseUtils.getPropertyObject(asJsonObject, "summary", SearchTrackableListSummary.class));
            videosList = (String)list.getVideosList();
            for (int i = this.from; i <= this.to; ++i) {
                final String string = Integer.toString(i);
                if (asJsonObject.has(string)) {
                    final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                    final SearchVideo searchVideo = new SearchVideo();
                    searchVideo.summary = FalkorParseUtils.getPropertyObject(asJsonObject2, "summary", Video$Summary.class);
                    searchVideo.searchTitle = FalkorParseUtils.getPropertyObject(asJsonObject2, "searchTitle", Video$SearchTitle.class);
                    ((List<SearchVideo>)videosList).add(searchVideo);
                    Log.v("nf_fetch_sims_request", "Found video: " + searchVideo.summary.getTitle());
                }
            }
        }
        catch (Exception ex) {
            Log.v("nf_fetch_sims_request", "String response to parse = " + videosList);
            throw new FalkorParseException("response missing expected json objects", ex);
        }
        this.checkForNullTrackables(list);
        return list;
    }
}
