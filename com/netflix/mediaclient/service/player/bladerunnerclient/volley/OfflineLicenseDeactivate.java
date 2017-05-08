// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;

public class OfflineLicenseDeactivate extends FetchLinkRequest
{
    public OfflineLicenseDeactivate(final String s, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        super(s, bladeRunnerWebCallback);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onLicenseDeactivated(status, null);
            return;
        }
        Log.d("nf_bladerunner", "no callback for link");
    }
    
    @Override
    protected void onSuccess(JSONObject jsonObject) {
        jsonObject = BladerunnerParseUtils.getJSONObject("nf_bladerunner", "link", jsonObject);
        if (jsonObject != null) {
            jsonObject.optJSONObject("result");
        }
        final Status status = BladerunnerParseUtils.getStatus(jsonObject, BladerunnerErrorStatus$BrRequestType.OfflineLicenseDelete);
        if (this.responseCallback != null) {
            this.responseCallback.onLicenseDeactivated(status, null);
            return;
        }
        Log.d("nf_bladerunner", "no callback for licenseDeactivate");
    }
}
