// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.LoLoMoSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchLoLoMoSummaryRequest extends FalcorVolleyWebClientRequest<LoLoMo>
{
    private static final String FIELD_GENRE_LOLOMO = "genreLolomo";
    private static final String FIELD_TOP_GENRE = "topGenre";
    private static final String TAG = "nf_service_browse_fetchlolomosummaryrequest";
    private final String lolomoCategoryId;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public FetchLoLoMoSummaryRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final String lolomoCategoryId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.lolomoCategoryId = lolomoCategoryId;
        this.lolomoId = BrowseAgent.getGenreLoLoMoId(hardCache, lolomoCategoryId);
        this.lolomoIdInCache = (this.lolomoId != null);
        if (this.lolomoIdInCache) {
            this.pqlQuery = "['genreLolomo','" + this.lolomoId + "','summary']";
        }
        else {
            this.pqlQuery = "['topGenre','" + lolomoCategoryId + "','summary']";
        }
        if (Log.isLoggable("nf_service_browse_fetchlolomosummaryrequest", 2)) {
            Log.v("nf_service_browse_fetchlolomosummaryrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoLoMoSummaryFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final LoLoMo loLoMo) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoLoMoSummaryFetched(loLoMo, 0);
        }
    }
    
    @Override
    protected LoLoMo parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchlolomosummaryrequest", 2)) {
            Log.v("nf_service_browse_fetchlolomosummaryrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchlolomosummaryrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException(String.format("lolomo %s summary empty!!!", this.lolomoCategoryId));
        }
        try {
            if (this.lolomoIdInCache) {
                s = (String)dataObj.getAsJsonObject("genreLolomo").getAsJsonObject(this.lolomoId);
            }
            else {
                s = (String)dataObj.getAsJsonObject("topGenre").getAsJsonObject(this.lolomoCategoryId);
            }
            s = (String)FalcorParseUtils.getPropertyObject((JsonObject)s, "summary", LoLoMoSummary.class);
            ((LoLoMoSummary)s).setId(this.lolomoCategoryId);
            return (LoLoMo)s;
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchlolomosummaryrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
    }
}
