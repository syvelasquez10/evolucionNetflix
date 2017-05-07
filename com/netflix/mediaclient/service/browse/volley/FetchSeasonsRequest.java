// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchSeasonsRequest extends FalkorVolleyWebClientRequest<List<SeasonDetails>>
{
    private static final String FIELD_SEASONS = "seasons";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchseasonsrequest";
    private final int fromSeason;
    private final String mShowId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toSeason;
    
    public FetchSeasonsRequest(final Context context, final String mShowId, final int fromSeason, final int toSeason, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mShowId = mShowId;
        this.fromSeason = fromSeason;
        this.toSeason = toSeason;
        this.pqlQuery = String.format("['videos', '%s', 'seasons', {'from':%d,'to':%d}, 'detail']", this.mShowId, fromSeason, toSeason);
        if (Log.isLoggable("nf_service_browse_fetchseasonsrequest", 2)) {
            Log.v("nf_service_browse_fetchseasonsrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonsFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<SeasonDetails> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonsFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<SeasonDetails> parseFalkorResponse(final String s) {
        if (Log.isLoggable("nf_service_browse_fetchseasonsrequest", 2)) {}
        final ArrayList<SeasonDetails> list = new ArrayList<SeasonDetails>();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchseasonsrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return list;
        }
        try {
            FalkorParseUtils.buildSeasonsList("nf_service_browse_fetchseasonsrequest", dataObj.getAsJsonObject("videos").getAsJsonObject(this.mShowId).getAsJsonObject("seasons"), list, this.fromSeason, this.toSeason);
            return list;
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchseasonsrequest", "String response to parse = " + s);
            throw new FalkorParseException("response missing expected json objects", ex);
        }
    }
}
