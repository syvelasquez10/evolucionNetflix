// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.LoLoMoSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
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
    
    public FetchLoLoMoSummaryRequest(final Context context, final BrowseWebClientCache browseWebClientCache, final String lolomoCategoryId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.lolomoCategoryId = lolomoCategoryId;
        this.lolomoId = browseWebClientCache.getGenreLoLoMoId(lolomoCategoryId);
        this.lolomoIdInCache = (this.lolomoId != null);
        if (this.lolomoIdInCache) {
            this.pqlQuery = String.format("['genreLolomo','%s', 'summary']", this.lolomoId);
        }
        else {
            this.pqlQuery = String.format("['topGenre','%s', 'summary']", lolomoCategoryId);
        }
        if (Log.isLoggable("nf_service_browse_fetchlolomosummaryrequest", 2)) {
            Log.v("nf_service_browse_fetchlolomosummaryrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoLoMoSummaryFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final LoLoMo loLoMo) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoLoMoSummaryFetched(loLoMo, CommonStatus.OK);
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
