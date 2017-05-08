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
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.model.leafs.OnRampEligibility;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class DoOnRampEligibilityActionRequest extends FalkorVolleyWebClientRequest<OnRampEligibility>
{
    private static final String TAG = "DoOnRampEligibilityActionRequest";
    OnRampEligibility$Action action;
    private final UserAgentWebCallback responseCallback;
    
    public DoOnRampEligibilityActionRequest(final Context context, final OnRampEligibility$Action action, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.action = action;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['onramp','" + this.action.toString().toLowerCase() + "']");
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onOnRampEligibilityAction(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final OnRampEligibility onRampEligibility) {
        if (this.responseCallback != null) {
            this.responseCallback.onOnRampEligibilityAction(onRampEligibility, CommonStatus.OK);
        }
    }
    
    @Override
    protected OnRampEligibility parseFalkorResponse(String string) {
        if (Log.isLoggable()) {
            Log.v("DoOnRampEligibilityActionRequest", "String response to parse = " + string);
        }
        try {
            return new OnRampEligibility((JsonElement)new JsonParser().parse(string).getAsJsonObject().getAsJsonObject("value").getAsJsonObject("onramp").getAsJsonObject(this.action.toString().toLowerCase()));
        }
        catch (Exception ex) {
            string = "DoOnRampEligibilityActionRequest got exception trying to parse JSON: " + ex + " ... JSON -> " + string;
            Log.w("DoOnRampEligibilityActionRequest", string);
            ErrorLoggingManager.logHandledException(string);
            return null;
        }
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
