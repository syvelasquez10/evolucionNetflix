// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class LogBillboardActivityRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String TAG = "LogBillboardActivityRequest";
    private final String pqlQuery;
    
    public LogBillboardActivityRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        super(context, configurationAgentInterface);
        this.pqlQuery = String.format("['logBillboardActivity', '%s', '%s']", video.getId(), billboardActivityType.getName());
        if (Log.isLoggable("LogBillboardActivityRequest", 2)) {
            Log.v("LogBillboardActivityRequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameGet();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        Log.v("LogBillboardActivityRequest", "onFailure, statusCode:" + n);
    }
    
    @Override
    protected void onSuccess(final String s) {
        Log.v("LogBillboardActivityRequest", "onSuccess");
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("LogBillboardActivityRequest", 2)) {
            Log.v("LogBillboardActivityRequest", "parseFalcorResponse: " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("LogBillboardActivityRequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("Empty value");
        }
        return dataObj.toString();
    }
}
