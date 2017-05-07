// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Season$Detail;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchSeasonDetailsRequest extends FalcorVolleyWebClientRequest<SeasonDetails>
{
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_fetchseasondetailsrequest";
    private final String mSeasonId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public FetchSeasonDetailsRequest(final Context context, final String mSeasonId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mSeasonId = mSeasonId;
        this.pqlQuery = String.format("['videos', '%s', 'detail']", this.mSeasonId);
        if (Log.isLoggable("nf_service_browse_fetchseasondetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchseasondetailsrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonDetailsFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final SeasonDetails seasonDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onSeasonDetailsFetched(seasonDetails, CommonStatus.OK);
        }
    }
    
    @Override
    protected SeasonDetails parseFalcorResponse(String s) {
        if (Log.isLoggable("nf_service_browse_fetchseasondetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchseasondetailsrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchseasondetailsrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("SeasonDetails empty!!!");
        }
        try {
            final JsonObject asJsonObject = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mSeasonId);
            s = (String)new com.netflix.mediaclient.service.webclient.model.SeasonDetails();
            ((com.netflix.mediaclient.service.webclient.model.SeasonDetails)s).detail = FalcorParseUtils.getPropertyObject(asJsonObject, "detail", Season$Detail.class);
            return (SeasonDetails)s;
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchseasondetailsrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex);
        }
    }
}
