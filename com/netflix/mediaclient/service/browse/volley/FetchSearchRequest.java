// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.SearchVideo;
import com.netflix.mediaclient.service.webclient.model.SearchSuggestion;
import com.netflix.mediaclient.service.webclient.model.SearchPerson;
import com.google.gson.JsonArray;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import java.util.Map;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.SearchResults;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.user.ProfileType;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSearchRequest extends FalcorVolleyWebClientRequest<ISearchResults>
{
    private static final String FIELD_PEOPLE = "people";
    private static final String FIELD_RESULT_TYPES_STRING = "['videos','people','suggestions']";
    private static final String FIELD_SEARCH = "search";
    private static final String FIELD_SUGGESTIONS = "suggestions";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchsearchrequest";
    private static final Set<String> VIDEO_TYPES_SET;
    private final int fromIndex;
    private final String pqlQuery;
    private final String pqlQuery1;
    private final ProfileType profileType;
    private final BrowseAgentCallback responseCallback;
    private final int toIndex;
    
    static {
        VIDEO_TYPES_SET = new HashSet<String>(Arrays.asList("shows", "movies", "seasons", "series", "episodes"));
    }
    
    public FetchSearchRequest(final Context context, final String s, final int fromIndex, final int n, final ProfileType profileType, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.fromIndex = fromIndex;
        this.toIndex = 50;
        this.profileType = profileType;
        final String escapeJsonChars = StringUtils.escapeJsonChars(s);
        this.pqlQuery = String.format("['search', %s, '%s', '%s', {'from':%d,'to':%d}, ['summary', 'searchTitle']]", "['videos','people','suggestions']", escapeJsonChars, profileType, fromIndex, 50);
        this.pqlQuery1 = String.format("['search', %s, '%s', '%s', 'summary']", "['videos','people','suggestions']", escapeJsonChars, profileType);
        if (Log.isLoggable("nf_service_browse_fetchsearchrequest", 2)) {
            Log.v("nf_service_browse_fetchsearchrequest", "PQL = " + this.pqlQuery + ", " + this.pqlQuery1);
        }
    }
    
    private void extractCategoryElements(final SearchResults.Builder builder, JsonObject asJsonObject, String string) {
        final Iterator<Map.Entry<String, JsonElement>> iterator = asJsonObject.entrySet().iterator();
        if (iterator.hasNext()) {
            asJsonObject = ((Map.Entry<String, JsonElement>)iterator.next()).getValue().getAsJsonObject().getAsJsonObject(this.profileType.toString());
            if (string.equals("videos")) {
                builder.setVideoListSummary(FalcorParseUtils.getPropertyObject(asJsonObject, "summary", SearchTrackableListSummary.class));
            }
            else if (string.equals("people")) {
                builder.setPeopleListSummary(FalcorParseUtils.getPropertyObject(asJsonObject, "summary", SearchTrackableListSummary.class));
            }
            else if (string.equals("suggestions")) {
                builder.setSuggestionsListSummary(FalcorParseUtils.getPropertyObject(asJsonObject, "summary", SearchTrackableListSummary.class));
            }
            for (int i = this.fromIndex; i <= this.toIndex; ++i) {
                string = Integer.toString(i);
                if (asJsonObject.has(string)) {
                    final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                    final JsonArray asJsonArray = asJsonObject2.getAsJsonArray("path");
                    if (asJsonArray == null || asJsonArray.size() == 0) {
                        Log.w("nf_service_browse_fetchsearchrequest", "Invalid search path: " + asJsonArray);
                    }
                    else {
                        final String asString = asJsonArray.get(0).getAsString();
                        if (FetchSearchRequest.VIDEO_TYPES_SET.contains(asString)) {
                            this.handleVideoResult(builder, asJsonObject2);
                        }
                        else if ("people".equals(asString)) {
                            this.handlePersonResult(builder, asJsonObject2);
                        }
                        else if ("suggestions".equals(asString)) {
                            this.handleSuggestionResult(builder, asJsonObject2);
                        }
                        else {
                            Log.w("nf_service_browse_fetchsearchrequest", "Unknown search result type: " + asString);
                        }
                    }
                }
            }
        }
    }
    
    private void handlePersonResult(final SearchResults.Builder builder, final JsonObject jsonObject) {
        new SearchPerson();
        final SearchPerson searchPerson = FalcorParseUtils.getPropertyObject(jsonObject, "searchTitle", SearchPerson.class);
        builder.addPerson(searchPerson);
        Log.v("nf_service_browse_fetchsearchrequest", "Found person: " + searchPerson);
    }
    
    private void handleSuggestionResult(final SearchResults.Builder builder, final JsonObject jsonObject) {
        new SearchSuggestion();
        final SearchSuggestion searchSuggestion = FalcorParseUtils.getPropertyObject(jsonObject, "searchTitle", SearchSuggestion.class);
        builder.addSuggestion(searchSuggestion);
        Log.v("nf_service_browse_fetchsearchrequest", "Found suggestion: " + searchSuggestion);
    }
    
    private void handleVideoResult(final SearchResults.Builder builder, final JsonObject jsonObject) {
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
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSearchResultsFetched(new SearchResults.Builder().getResults(), status);
        }
    }
    
    @Override
    protected void onSuccess(final ISearchResults searchResults) {
        if (this.responseCallback != null) {
            this.responseCallback.onSearchResultsFetched(searchResults, CommonStatus.OK);
        }
    }
    
    @Override
    protected ISearchResults parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        try {
            final SearchResults.Builder builder = new SearchResults.Builder();
            final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchsearchrequest", s);
            if (FalcorParseUtils.isEmpty(dataObj)) {
                return builder.getResults();
            }
            final JsonObject asJsonObject = dataObj.getAsJsonObject("search");
            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("videos");
            final JsonObject asJsonObject3 = asJsonObject.getAsJsonObject("people");
            final JsonObject asJsonObject4 = asJsonObject.getAsJsonObject("suggestions");
            if (asJsonObject2 != null) {
                this.extractCategoryElements(builder, asJsonObject2, "videos");
            }
            if (asJsonObject3 != null) {
                this.extractCategoryElements(builder, asJsonObject3, "people");
            }
            if (asJsonObject4 != null) {
                this.extractCategoryElements(builder, asJsonObject4, "suggestions");
            }
            return builder.getResults();
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchsearchrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
    }
}
