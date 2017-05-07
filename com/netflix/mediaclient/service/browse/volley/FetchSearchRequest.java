// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.SearchVideo;
import com.netflix.mediaclient.service.webclient.model.SearchSuggestion;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.SearchPerson;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.ProfileType;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSearchRequest extends FalcorVolleyWebClientRequest<SearchResults>
{
    private static final String FIELD_RESULT_TYPES_STRING = "videos,people,suggestions";
    private static final String FIELD_SEARCH = "search";
    private static final String TAG = "nf_service_browse_fetchsearchrequest";
    private static final Set<String> VIDEO_TYPES_SET;
    private final int fromIndex;
    private final String pqlQuery;
    private final String pqlQuery1;
    private final ProfileType profileType;
    private final BrowseAgentCallback responseCallback;
    private final String searchQuery;
    private final int toIndex;
    
    static {
        VIDEO_TYPES_SET = new HashSet<String>(Arrays.asList("shows", "movies", "seasons", "series", "episodes"));
    }
    
    public FetchSearchRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String searchQuery, final int fromIndex, final int toIndex, final ProfileType profileType, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.searchQuery = searchQuery;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.profileType = profileType;
        final String escapeJsonChars = StringUtils.escapeJsonChars(searchQuery);
        this.pqlQuery = String.format("['search', '%s', '%s', '%s', {'from':%d,'to':%d}, ['summary', 'searchTitle']]", "videos,people,suggestions", escapeJsonChars, profileType, fromIndex, toIndex);
        this.pqlQuery1 = String.format("['search', '%s', '%s', '%s', 'summary']", "videos,people,suggestions", escapeJsonChars, profileType);
        if (Log.isLoggable("nf_service_browse_fetchsearchrequest", 2)) {
            Log.v("nf_service_browse_fetchsearchrequest", "PQL = " + this.pqlQuery + " " + this.pqlQuery1);
        }
    }
    
    private void handlePersonResult(final com.netflix.mediaclient.service.webclient.model.SearchResults.Builder builder, final JsonObject jsonObject) {
        new SearchPerson();
        final SearchPerson searchPerson = FalcorParseUtils.getPropertyObject(jsonObject, "searchTitle", SearchPerson.class);
        builder.addPerson(searchPerson);
        Log.v("nf_service_browse_fetchsearchrequest", "Found person: " + searchPerson);
    }
    
    private void handleSuggestionResult(final com.netflix.mediaclient.service.webclient.model.SearchResults.Builder builder, final JsonObject jsonObject) {
        new SearchSuggestion();
        final SearchSuggestion searchSuggestion = FalcorParseUtils.getPropertyObject(jsonObject, "searchTitle", SearchSuggestion.class);
        builder.addSuggestion(searchSuggestion);
        Log.v("nf_service_browse_fetchsearchrequest", "Found suggestion: " + searchSuggestion);
    }
    
    private void handleVideoResult(final com.netflix.mediaclient.service.webclient.model.SearchResults.Builder builder, final JsonObject jsonObject) {
        final SearchVideo searchVideo = new SearchVideo();
        searchVideo.summary = FalcorParseUtils.getPropertyObject(jsonObject, "summary", Video.Summary.class);
        searchVideo.searchTitle = FalcorParseUtils.getPropertyObject(jsonObject, "searchTitle", Video.SearchTitle.class);
        builder.addVideo(searchVideo);
        Log.v("nf_service_browse_fetchsearchrequest", "Found video: " + searchVideo);
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameGet();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery, this.pqlQuery1 };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onSearchResultsFetched(new com.netflix.mediaclient.service.webclient.model.SearchResults.Builder().getResults(), n);
        }
    }
    
    @Override
    protected void onSuccess(final SearchResults searchResults) {
        if (this.responseCallback != null) {
            this.responseCallback.onSearchResultsFetched(searchResults, 0);
        }
    }
    
    @Override
    protected SearchResults parseFalcorResponse(String o) throws FalcorParseException, FalcorServerException {
        final com.netflix.mediaclient.service.webclient.model.SearchResults.Builder builder = new com.netflix.mediaclient.service.webclient.model.SearchResults.Builder();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchsearchrequest", (String)o);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            return builder.getResults();
        }
        JsonObject asJsonObject;
        while (true) {
            while (true) {
                JsonArray asJsonArray = null;
                Label_0187: {
                    try {
                        asJsonObject = dataObj.getAsJsonObject("search").getAsJsonObject("videos,people,suggestions").getAsJsonObject(this.searchQuery).getAsJsonObject(this.profileType.toString());
                        for (int i = this.fromIndex; i <= this.toIndex; ++i) {
                            o = Integer.toString(i);
                            if (asJsonObject.has((String)o)) {
                                o = asJsonObject.getAsJsonObject((String)o);
                                asJsonArray = ((JsonObject)o).getAsJsonArray("path");
                                if (asJsonArray != null && asJsonArray.size() != 0) {
                                    break Label_0187;
                                }
                                Log.w("nf_service_browse_fetchsearchrequest", "Invalid search path: " + asJsonArray);
                            }
                        }
                        break;
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchsearchrequest", "String response to parse = " + (String)o);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                }
                final String asString = asJsonArray.get(0).getAsString();
                if (FetchSearchRequest.VIDEO_TYPES_SET.contains(asString)) {
                    this.handleVideoResult(builder, (JsonObject)o);
                    continue;
                }
                if ("people".equals(asString)) {
                    this.handlePersonResult(builder, (JsonObject)o);
                    continue;
                }
                if ("suggestions".equals(asString)) {
                    this.handleSuggestionResult(builder, (JsonObject)o);
                    continue;
                }
                Log.w("nf_service_browse_fetchsearchrequest", "Unknown search result type: " + asString);
                continue;
            }
        }
        builder.setSummary(FalcorParseUtils.getPropertyObject(asJsonObject, "summary", TrackableListSummary.class));
        return builder.getResults();
    }
}
