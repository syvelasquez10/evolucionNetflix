// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import org.json.JSONException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;
import org.json.JSONObject;
import com.netflix.mediaclient.service.msl.volley.ApiFalkorMSLVolleyRequest;

public class FetchLinkRequest extends ApiFalkorMSLVolleyRequest<JSONObject>
{
    protected static final String TAG = "nf_bladerunner";
    private final String pqlQuery1;
    private final String requestParams;
    protected final BladeRunnerWebCallback responseCallback;
    
    public FetchLinkRequest(final String requestParams, final BladeRunnerWebCallback responseCallback) {
        this.requestParams = requestParams;
        this.responseCallback = responseCallback;
        this.pqlQuery1 = "['link']";
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
            this.responseCallback.onLinkDone(null, status);
            return;
        }
        Log.d("nf_bladerunner", "no callback for link");
    }
    
    @Override
    protected void onSuccess(final JSONObject jsonObject) {
        final JSONObject jsonObject2 = BladerunnerParseUtils.getJSONObject("nf_bladerunner", "link", jsonObject);
        final JSONObject jsonObject3 = null;
        Status status = CommonStatus.NOT_VALID;
        JSONObject optJSONObject = jsonObject3;
        if (jsonObject2 != null) {
            final JSONObject optJSONObject2 = jsonObject2.optJSONObject("result");
            status = BladerunnerParseUtils.getStatus(jsonObject2, BladerunnerErrorStatus$BrRequestType.OfflineLink);
            optJSONObject = jsonObject3;
            if (optJSONObject2 != null) {
                optJSONObject = optJSONObject2.optJSONObject("links");
                status = status;
            }
        }
        if (this.responseCallback != null) {
            this.responseCallback.onLinkDone(optJSONObject, status);
            return;
        }
        Log.d("nf_bladerunner", "no callback for link");
    }
    
    @Override
    protected JSONObject parseFalkorResponse(final String s) {
        Log.dumpVerbose("nf_bladerunner", "parseFalkorResponse " + s);
        try {
            return new JSONObject(s);
        }
        catch (JSONException ex) {
            Log.e("nf_bladerunner", "error parsing json", (Throwable)ex);
            return null;
        }
    }
}
