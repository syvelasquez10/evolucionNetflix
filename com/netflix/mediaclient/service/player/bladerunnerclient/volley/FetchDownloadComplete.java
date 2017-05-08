// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.service.player.bladerunnerclient.OfflineLicenseResponse;
import com.netflix.mediaclient.android.app.CommonStatus;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;

public class FetchDownloadComplete extends FetchLinkRequest
{
    public FetchDownloadComplete(final String s, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        super(s, bladeRunnerWebCallback);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onDownloadComplete(status, null);
            return;
        }
        Log.d("nf_bladerunner", "no callback for link");
    }
    
    @Override
    protected void onSuccess(JSONObject jsonObject) {
        String link = null;
        jsonObject = BladerunnerParseUtils.getJSONObject("nf_bladerunner", "link", jsonObject);
        JSONObject optJSONObject;
        if (jsonObject != null) {
            optJSONObject = jsonObject.optJSONObject("result");
        }
        else {
            optJSONObject = null;
        }
        Status status2;
        final Status status = status2 = BladerunnerParseUtils.getStatus(jsonObject, BladerunnerErrorStatus$BrRequestType.OfflineDownloadComplete);
        if (status.isSucces()) {
            status2 = status;
            if (!BladerunnerErrorStatus.hasLinksInPayload(optJSONObject)) {
                status2 = CommonStatus.BLADERUNNER_FAILURE;
            }
        }
        JSONObject optJSONObject2;
        if (optJSONObject != null) {
            optJSONObject2 = optJSONObject.optJSONObject("links");
        }
        else {
            optJSONObject2 = null;
        }
        if (optJSONObject2 != null) {
            link = OfflineLicenseResponse.getLink(optJSONObject2, OfflineLicenseResponse.LINK_REFRESH);
        }
        if (this.responseCallback != null) {
            this.responseCallback.onDownloadComplete(status2, link);
            return;
        }
        Log.d("nf_bladerunner", "no callback for link");
    }
}
