// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.model.survey.Survey;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchSurveyRequest extends FalkorVolleyWebClientRequest<Survey>
{
    private static final String TAG = "FetchSurveyRequest";
    private final UserAgentWebCallback responseCallback;
    
    public FetchSurveyRequest(final Context context, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['survey','get']");
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSurveyFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final Survey survey) {
        if (this.responseCallback != null) {
            this.responseCallback.onSurveyFetched(survey, CommonStatus.OK);
        }
    }
    
    @Override
    protected Survey parseFalkorResponse(String string) {
        if (Log.isLoggable()) {
            Log.v("FetchSurveyRequest", "String response to parse = " + string);
        }
        try {
            return new Survey((JsonElement)new JsonParser().parse(string).getAsJsonObject().getAsJsonObject("value").getAsJsonObject("survey").getAsJsonObject("get"));
        }
        catch (Exception ex) {
            string = "FetchSurveyTask got exception trying to parse JSON: " + ex + " ... JSON -> " + string;
            Log.w("FetchSurveyRequest", string);
            ErrorLoggingManager.logHandledException(string);
            return null;
        }
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
