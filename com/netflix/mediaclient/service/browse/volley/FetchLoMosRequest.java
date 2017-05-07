// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.LoMoType;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Collections;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.servicemgr.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchLoMosRequest extends FalcorVolleyWebClientRequest<List<LoMo>>
{
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String TAG = "nf_service_browse_fetchlomosrequest";
    private final int fromLoMo;
    private final HardCache hardCache;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toLoMo;
    
    public FetchLoMosRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final int fromLoMo, final int toLoMo, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.fromLoMo = fromLoMo;
        this.toLoMo = toLoMo;
        this.hardCache = hardCache;
        this.lolomoId = BrowseAgent.getLoLoMoId(hardCache);
        this.lolomoIdInCache = (this.lolomoId != null);
        if (this.lolomoIdInCache) {
            this.pqlQuery = "['lolomos','" + this.lolomoId + "',{'from':" + fromLoMo + ",'to':" + toLoMo + "},'summary']";
        }
        else {
            this.pqlQuery = "['lolomo'" + ",{'from':" + fromLoMo + ",'to':" + toLoMo + "},'summary']";
        }
        if (Log.isLoggable("nf_service_browse_fetchlomosrequest", 2)) {
            Log.v("nf_service_browse_fetchlomosrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoMosFetched(Collections.emptyList(), n);
        }
    }
    
    @Override
    protected void onSuccess(final List<LoMo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoMosFetched(list, 0);
        }
    }
    
    @Override
    protected List<LoMo> parseFalcorResponse(String o) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchlomosrequest", 2)) {}
        final ArrayList<ListOfMoviesSummary> list = (ArrayList<ListOfMoviesSummary>)new ArrayList<LoMo>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchlomosrequest", (String)o);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
            try {
                if (this.lolomoIdInCache) {
                    o = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId);
                }
                else {
                    o = dataObj.getAsJsonObject("lolomo");
                }
                if (!this.lolomoIdInCache) {
                    PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.hardCache, (JsonObject)o);
                }
                for (int i = this.fromLoMo; i <= this.toLoMo; ++i) {
                    final String string = Integer.toString(i);
                    if (((JsonObject)o).has(string)) {
                        final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(((JsonObject)o).getAsJsonObject(string), "summary", ListOfMoviesSummary.class);
                        if (listOfMoviesSummary != null) {
                            listOfMoviesSummary.setListPos(i);
                        }
                        list.add(listOfMoviesSummary);
                        if (!this.lolomoIdInCache) {
                            if (listOfMoviesSummary.getType() == LoMoType.CONTINUE_WATCHING) {
                                PrefetchHomeLoLoMoRequest.putCWIdsInCache(this.hardCache, listOfMoviesSummary, string);
                            }
                            if (listOfMoviesSummary.getType() == LoMoType.INSTANT_QUEUE) {
                                PrefetchHomeLoLoMoRequest.putIQIdsInCache(this.hardCache, listOfMoviesSummary, string);
                            }
                        }
                    }
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_fetchlomosrequest", "String response to parse = " + (String)o);
                throw new FalcorParseException("response missing expected json objects", ex);
            }
        }
        return (List<LoMo>)list;
    }
}
