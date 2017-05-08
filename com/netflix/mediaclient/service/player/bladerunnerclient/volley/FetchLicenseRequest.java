// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import org.json.JSONException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;

public class FetchLicenseRequest extends BladeRunnerRequest
{
    private static final String TAG = "nf_msl_volley_FetchLicenseRequest";
    private final FetchLicenseRequest$LicenseReqType licenseReqType;
    private final boolean mIsRefresh;
    private final String pqlQuery1;
    private final String requestParams;
    private final BladeRunnerWebCallback responseCallback;
    
    public FetchLicenseRequest(final FetchLicenseRequest$LicenseReqType licenseReqType, final String requestParams, final boolean mIsRefresh, final BladeRunnerWebCallback responseCallback) {
        this.licenseReqType = licenseReqType;
        this.requestParams = requestParams;
        this.responseCallback = responseCallback;
        this.mIsRefresh = mIsRefresh;
        this.pqlQuery1 = "['license']";
    }
    
    private void doCallback(final JSONObject jsonObject, final Status status) {
        if (FetchLicenseRequest$LicenseReqType.STREAMING == this.licenseReqType) {
            this.responseCallback.onLicenseFetched(jsonObject, status);
            return;
        }
        final OfflineLicenseResponse offlineLicenseResponse = new OfflineLicenseResponse(jsonObject);
        Log.d("nf_msl_volley_FetchLicenseRequest", "onLicenseFetched type:%s, licenseResponse: %s", this.licenseReqType, offlineLicenseResponse);
        this.responseCallback.onOfflineLicenseFetched(offlineLicenseResponse, status);
    }
    
    private BladerunnerErrorStatus$BrRequestType getTypeForError() {
        if (this.licenseReqType == FetchLicenseRequest$LicenseReqType.STREAMING) {
            return BladerunnerErrorStatus$BrRequestType.StreamingLicense;
        }
        if (this.mIsRefresh) {
            return BladerunnerErrorStatus$BrRequestType.OfflineLicenseRefresh;
        }
        return BladerunnerErrorStatus$BrRequestType.OfflineLicense;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        params.put("bladerunnerParams", this.requestParams);
        return params;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.doCallback(null, status);
            return;
        }
        Log.w("nf_msl_volley_FetchLicenseRequest", "callback null?");
    }
    
    @Override
    protected void onSuccess(JSONObject optJSONObject) {
        final JSONObject jsonObject = BladerunnerParseUtils.getJSONObject("nf_msl_volley_FetchLicenseRequest", "license", optJSONObject);
        optJSONObject = null;
        if (jsonObject != null) {
            optJSONObject = jsonObject.optJSONObject("result");
        }
        Status status2;
        final Status status = status2 = BladerunnerParseUtils.getStatus(jsonObject, this.getTypeForError());
        if (status.isSucces()) {
            status2 = status;
            if (!BladerunnerErrorStatus.hasLinksInPayload(optJSONObject)) {
                status2 = CommonStatus.BLADERUNNER_FAILURE;
            }
        }
        if (this.responseCallback != null) {
            this.doCallback(optJSONObject, status2);
            return;
        }
        Log.w("nf_msl_volley_FetchLicenseRequest", "callback null?");
    }
    
    @Override
    protected JSONObject parseFalkorResponse(final String s) {
        try {
            return new JSONObject(s);
        }
        catch (JSONException ex) {
            Log.e("nf_msl_volley_FetchLicenseRequest", "error parsing json", (Throwable)ex);
            return null;
        }
    }
}
