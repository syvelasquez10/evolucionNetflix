// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class VerifyPinRequest extends FalkorVolleyWebClientRequest<Boolean>
{
    private static final String FIELD_IS_PIN_VALID = "isPinValid";
    private static final String FIELD_USER = "user";
    private static final String FIELD_VERIFY_PIN = "verifyPin";
    private static final String FIELD_VERIFY_PREVIEW_PIN = "verifyPreviewPin";
    private static final String TAG = "nf_pin";
    private final String enteredPin;
    private String mPinPath;
    private final String pqlQuery1;
    private final UserAgentWebCallback responseCallback;
    
    protected VerifyPinRequest(final Context context, final String enteredPin, final PinVerifier$PinType pinVerifier$PinType, final String s, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.enteredPin = enteredPin;
        boolean b;
        if (PinVerifier$PinType.MATURITY_PIN == pinVerifier$PinType) {
            b = true;
        }
        else {
            b = false;
        }
        String mPinPath;
        if (b) {
            mPinPath = "verifyPin";
        }
        else {
            mPinPath = "verifyPreviewPin";
        }
        this.mPinPath = mPinPath;
        String pqlQuery1;
        if (b) {
            pqlQuery1 = String.format("['user', '%s', '%s']", this.mPinPath, enteredPin);
        }
        else {
            pqlQuery1 = String.format("['user', '%s', '%s', '%s']", this.mPinPath, enteredPin, s);
        }
        this.pqlQuery1 = pqlQuery1;
        if (Log.isLoggable()) {
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
            this.responseCallback.onVerified(false, status);
        }
    }
    
    @Override
    protected void onSuccess(final Boolean b) {
        if (this.responseCallback != null) {
            this.responseCallback.onVerified(b, CommonStatus.OK);
        }
    }
    
    @Override
    protected Boolean parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_pin", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_pin", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorException("verifyPinResponse empty!!!");
        }
        try {
            return dataObj.getAsJsonObject("user").getAsJsonObject(this.mPinPath).getAsJsonObject(this.enteredPin).get("isPinValid").getAsBoolean();
        }
        catch (Exception ex) {
            Log.v("nf_pin", "String response to parse = " + s);
            throw new FalkorException("response missing user json objects", ex);
        }
    }
}
