// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfGenreSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Collections;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.GenreList;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchGenreListsRequest extends FalcorVolleyWebClientRequest<List<GenreList>>
{
    private static final String FIELD_GENRELIST = "genreList";
    private static final String TAG = "nf_service_browse_fetchgenrelistrequest";
    private final String pqlQuery;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    
    public FetchGenreListsRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final int n, final int n2, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.pqlQuery = new StringBuilder("['genreList']").toString();
        if (Log.isLoggable("nf_service_browse_fetchgenrelistrequest", 2)) {
            Log.v("nf_service_browse_fetchgenrelistrequest", "PQL = " + this.pqlQuery);
        }
        this.rStart = System.nanoTime();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onGenreListsFetched(Collections.emptyList(), n);
        }
    }
    
    @Override
    protected void onSuccess(final List<GenreList> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onGenreListsFetched(list, 0);
        }
    }
    
    @Override
    protected List<GenreList> parseFalcorResponse(String value) throws FalcorParseException, FalcorServerException {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_fetchgenrelistrequest", String.format("genreList request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_fetchgenrelistrequest", 2)) {
            Log.v("nf_service_browse_fetchgenrelistrequest", "String response to parse = " + value);
        }
        final ArrayList<ListOfGenreSummary> list = (ArrayList<ListOfGenreSummary>)new ArrayList<GenreList>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchgenrelistrequest", value);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("GenreLists empty!!!");
        }
        try {
            final JsonArray asJsonArray = dataObj.getAsJsonArray("genreList");
            for (int i = 0; i < asJsonArray.size(); ++i) {
                value = (String)asJsonArray.get(i);
                list.add(FalcorParseUtils.getGson().fromJson((JsonElement)value, ListOfGenreSummary.class));
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchgenrelistrequest", "String response to parse = " + value);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_fetchgenrelistrequest", String.format(" genreList success - took %d ms ", this.rDurationInMs));
        return (List<GenreList>)list;
    }
}
