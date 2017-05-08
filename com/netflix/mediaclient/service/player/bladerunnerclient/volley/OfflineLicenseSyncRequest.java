// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import com.netflix.mediaclient.util.NumberUtils;
import java.util.HashMap;
import com.netflix.mediaclient.android.app.CommonStatus;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.player.bladerunnerclient.BladeRunnerWebCallback;

public class OfflineLicenseSyncRequest extends FetchLinkRequest
{
    public OfflineLicenseSyncRequest(final String s, final BladeRunnerWebCallback bladeRunnerWebCallback) {
        super(s, bladeRunnerWebCallback);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onSyncLicenseDone(Collections.emptyMap(), status);
            return;
        }
        Log.d("nf_bladerunner", "no callback for OfflineLicenseSyncRequest");
    }
    
    @Override
    protected void onSuccess(JSONObject status) {
        Log.i("nf_bladerunner", "parsing license sync response");
        final Object ok = CommonStatus.OK;
        final HashMap<String, ClientActionFromLase> hashMap = new HashMap<String, ClientActionFromLase>();
        final JSONObject jsonObject = BladerunnerParseUtils.getJSONObject("nf_bladerunner", "link", status);
        JSONObject optJSONObject;
        if (jsonObject != null) {
            status = (JSONObject)BladerunnerParseUtils.getStatus(jsonObject, BladerunnerErrorStatus$BrRequestType.SyncLicense);
            optJSONObject = jsonObject.optJSONObject("result");
        }
        else {
            status = (JSONObject)ok;
            optJSONObject = null;
        }
        JSONObject optJSONObject2;
        if (optJSONObject != null) {
            optJSONObject2 = optJSONObject.optJSONObject("actions");
        }
        else {
            optJSONObject2 = null;
        }
        if (optJSONObject2 != null) {
            try {
                final Iterator keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    final String string = optJSONObject2.getString(s);
                    Log.i("nf_bladerunner", "OfflineLicenseSyncRequest response movieId=%s action=%s", s, string);
                    hashMap.put(s, ClientActionFromLase.create(NumberUtils.toIntegerSafely(string, ClientActionFromLase.NO_ACTION.getValue())));
                }
            }
            catch (JSONException ex) {
                Log.e("nf_bladerunner", "JSONException", (Throwable)ex);
            }
        }
        if (this.responseCallback != null) {
            this.responseCallback.onSyncLicenseDone(hashMap, (Status)status);
            return;
        }
        Log.d("nf_bladerunner", "no callback for OfflineLicenseSyncRequest");
    }
}
