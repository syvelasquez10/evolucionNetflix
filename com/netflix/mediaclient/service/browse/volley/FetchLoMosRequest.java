// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchLoMosRequest extends FalkorVolleyWebClientRequest<List<LoMo>>
{
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String TAG = "nf_service_browse_fetchlomosrequest";
    private final BrowseWebClientCache browseCache;
    private final int fromLoMo;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toLoMo;
    
    public FetchLoMosRequest(final Context context, final BrowseWebClientCache browseCache, final int fromLoMo, final int toLoMo, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.fromLoMo = fromLoMo;
        this.toLoMo = toLoMo;
        this.browseCache = browseCache;
        this.lolomoId = browseCache.getLoLoMoId();
        this.lolomoIdInCache = (this.lolomoId != null);
        if (this.lolomoIdInCache) {
            this.pqlQuery = String.format("['lolomos', '%s',  {'from':%d,'to':%d}, 'summary']", this.lolomoId, fromLoMo, toLoMo);
        }
        else {
            this.pqlQuery = String.format("['lolomo', {'from':%d,'to':%d}, 'summary']", fromLoMo, toLoMo);
        }
        if (Log.isLoggable("nf_service_browse_fetchlomosrequest", 2)) {
            Log.v("nf_service_browse_fetchlomosrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoMosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<LoMo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoMosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<LoMo> parseFalkorResponse(String o) {
        if (Log.isLoggable("nf_service_browse_fetchlomosrequest", 2)) {}
        final ArrayList<ListOfMoviesSummary> list = new ArrayList<ListOfMoviesSummary>();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchlomosrequest", (String)o);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return (List<LoMo>)list;
        }
        try {
            if (this.lolomoIdInCache) {
                o = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId);
            }
            else {
                o = dataObj.getAsJsonObject("lolomo");
            }
            if (!this.lolomoIdInCache) {
                PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.browseCache, (JsonObject)o);
            }
            for (int i = this.fromLoMo; i <= this.toLoMo; ++i) {
                final String string = Integer.toString(i);
                if (((JsonObject)o).has(string)) {
                    final ListOfMoviesSummary listOfMoviesSummary = FalkorParseUtils.getPropertyObject(((JsonObject)o).getAsJsonObject(string), "summary", ListOfMoviesSummary.class);
                    if (listOfMoviesSummary != null) {
                        listOfMoviesSummary.setListPos(i);
                    }
                    list.add(listOfMoviesSummary);
                    if (!this.lolomoIdInCache) {
                        if (listOfMoviesSummary.getType() == LoMoType.CONTINUE_WATCHING) {
                            this.browseCache.putCWIdsInCache(listOfMoviesSummary, string);
                        }
                        if (listOfMoviesSummary.getType() == LoMoType.INSTANT_QUEUE) {
                            this.browseCache.putIQIdsInCache(listOfMoviesSummary, string);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchlomosrequest", "String response to parse = " + (String)o);
            throw new FalkorParseException("response missing expected json objects", ex);
        }
        return (List<LoMo>)list;
    }
}
