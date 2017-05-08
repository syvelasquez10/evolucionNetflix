// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.msl.volley.FalkorMSLVolleyRequest;
import com.netflix.mediaclient.Log;
import com.netflix.msl.client.ApiHttpWrapper;
import org.json.JSONObject;
import com.netflix.mediaclient.service.msl.volley.ApiFalkorMSLVolleyRequest;

public abstract class BladeRunnerRequest extends ApiFalkorMSLVolleyRequest<JSONObject>
{
    private static final String TAG = "nf_msl_volley_BladeRunnerRequest";
    
    @Override
    protected JSONObject parseApiResponse(final ApiHttpWrapper apiHttpWrapper) {
        final String dataAsString = apiHttpWrapper.getDataAsString();
        if (Log.isLoggable()) {
            Log.dumpVerbose("nf_msl_volley_BladeRunnerRequest", "parseFalkorResponse " + apiHttpWrapper);
        }
        if (FalkorMSLVolleyRequest.isNotAuthorized(dataAsString)) {
            Log.e("nf_msl_volley_BladeRunnerRequest", "User is not authorized, trying recovery...");
            ErrorLoggingManager.logHandledException("MSL: User is not authorized, trying recovery");
            throw new FalkorException(dataAsString);
        }
        return this.parseFalkorResponse(dataAsString);
    }
}
