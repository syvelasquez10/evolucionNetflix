// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class LogBillboardActivityRequest extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "LogBillboardActivityRequest";
    private final String pqlQuery;
    
    public LogBillboardActivityRequest(final Context context, final Video video, final BrowseAgent$BillboardActivityType browseAgent$BillboardActivityType) {
        super(context);
        this.pqlQuery = String.format("['logBillboardActivity', '%s', '%s']", video.getId(), browseAgent$BillboardActivityType.getName());
        if (Log.isLoggable("LogBillboardActivityRequest", 2)) {
            Log.v("LogBillboardActivityRequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (Log.isLoggable("LogBillboardActivityRequest", 2)) {
            Log.v("LogBillboardActivityRequest", "onFailure, statusCode:" + status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        Log.v("LogBillboardActivityRequest", "onSuccess");
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        if (Log.isLoggable("LogBillboardActivityRequest", 2)) {
            Log.v("LogBillboardActivityRequest", "parseFalkorResponse: " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("LogBillboardActivityRequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("Empty value");
        }
        return dataObj.toString();
    }
}
