// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Collections;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.servicemgr.Genre;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchGenresRequest extends FalcorVolleyWebClientRequest<List<Genre>>
{
    private static final String FIELD_GENRE_LOLOMO = "genreLolomo";
    private static final String FIELD_TOP_GENRE = "topGenre";
    private static final String TAG = "nf_service_browse_fetchgenresrequest";
    private final int fromGenre;
    private final String genreId;
    private final HardCache hardCache;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toGenre;
    
    public FetchGenresRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final String genreId, final int fromGenre, final int toGenre, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.genreId = genreId;
        this.fromGenre = fromGenre;
        this.toGenre = toGenre;
        this.hardCache = hardCache;
        this.lolomoId = BrowseAgent.getGenreLoLoMoId(hardCache, genreId);
        this.lolomoIdInCache = (this.lolomoId != null);
        if (this.lolomoIdInCache) {
            this.pqlQuery = "['genreLolomo','" + this.lolomoId + "',{'from':" + fromGenre + ",'to':" + toGenre + "},'summary']";
        }
        else {
            this.pqlQuery = "['topGenre','" + genreId + "',{'from':" + fromGenre + ",'to':" + toGenre + "},'summary']";
        }
        if (Log.isLoggable("nf_service_browse_fetchgenresrequest", 2)) {
            Log.v("nf_service_browse_fetchgenresrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onGenresFetched(Collections.emptyList(), n);
        }
    }
    
    @Override
    protected void onSuccess(final List<Genre> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onGenresFetched(list, 0);
        }
    }
    
    @Override
    protected List<Genre> parseFalcorResponse(String o) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchgenresrequest", 2)) {}
        final ArrayList<ListOfMoviesSummary> list = (ArrayList<ListOfMoviesSummary>)new ArrayList<Genre>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchgenresrequest", (String)o);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
            try {
                if (this.lolomoIdInCache) {
                    o = dataObj.getAsJsonObject("genreLolomo").getAsJsonObject(this.lolomoId);
                }
                else {
                    o = dataObj.getAsJsonObject("topGenre").getAsJsonObject(this.genreId);
                }
                if (!this.lolomoIdInCache) {
                    PrefetchGenreLoLoMoRequest.putGenreLoLoMoIdInBrowseCache(this.hardCache, this.genreId, (JsonObject)o);
                }
                for (int i = this.fromGenre; i <= this.toGenre; ++i) {
                    final String string = Integer.toString(i);
                    if (((JsonObject)o).has(string)) {
                        final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(((JsonObject)o).getAsJsonObject(string), "summary", ListOfMoviesSummary.class);
                        if (listOfMoviesSummary != null) {
                            listOfMoviesSummary.setListPos(i);
                        }
                        list.add(listOfMoviesSummary);
                    }
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_fetchgenresrequest", "String response to parse = " + (String)o);
                throw new FalcorParseException("response missing expected json objects", ex);
            }
        }
        return (List<Genre>)list;
    }
}
