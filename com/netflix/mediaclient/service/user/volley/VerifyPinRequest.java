// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class VerifyPinRequest extends FalcorVolleyWebClientRequest<Boolean>
{
    private static final String FIELD_IS_PIN_VALID = "isPinValid";
    private static final String FIELD_USER = "user";
    private static final String FIELD_VERIFY_PIN = "verifyPin";
    private static final String TAG = "nf_pin";
    private final String enteredPin;
    private final String pqlQuery1;
    private final UserAgentWebCallback responseCallback;
    
    protected VerifyPinRequest(final Context context, final String enteredPin, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.enteredPin = enteredPin;
        this.pqlQuery1 = String.format("['user', 'verifyPin', '%s']", enteredPin);
        if (Log.isLoggable("nf_pin", 2)) {
            Log.v("nf_pin", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onPinVerified(false, status);
        }
    }
    
    @Override
    protected void onSuccess(final Boolean b) {
        if (this.responseCallback != null) {
            this.responseCallback.onPinVerified(b, CommonStatus.OK);
        }
    }
    
    @Override
    protected Boolean parseFalcorResponse(final String s) {
        if (Log.isLoggable("nf_pin", 2)) {
            Log.v("nf_pin", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_pin", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("verifyPinResponse empty!!!");
        }
        try {
            return dataObj.getAsJsonObject("user").getAsJsonObject("verifyPin").getAsJsonObject(this.enteredPin).get("isPinValid").getAsBoolean();
        }
        catch (Exception ex) {
            Log.v("nf_pin", "String response to parse = " + s);
            throw new FalcorParseException("response missing user json objects", ex);
        }
    }
}
