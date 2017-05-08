// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import org.json.JSONObject;

public class BladerunnerParseUtils
{
    private static final String TAG = "nf_bladerunner";
    
    private static Status buildErrorStatusCode(final JSONObject jsonObject, final BladerunnerErrorStatus$BrRequestType bladerunnerErrorStatus$BrRequestType) {
        return new BladerunnerErrorStatus(jsonObject, bladerunnerErrorStatus$BrRequestType);
    }
    
    public static JSONObject getDataObj(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return jsonObject.optJSONObject("value");
    }
    
    public static JSONObject getJSONObject(final String s, final String s2, JSONObject dataObj) {
        dataObj = getDataObj(dataObj);
        if (dataObj == null) {
            Log.v(s, "null response ");
            return null;
        }
        return dataObj.optJSONObject(s2);
    }
    
    public static Status getStatus(final JSONObject jsonObject, final BladerunnerErrorStatus$BrRequestType bladerunnerErrorStatus$BrRequestType) {
        if (hasErrors(jsonObject)) {
            return buildErrorStatusCode(jsonObject, bladerunnerErrorStatus$BrRequestType);
        }
        return CommonStatus.OK;
    }
    
    public static boolean hasErrors(final JSONObject jsonObject) {
        Log.d("nf_bladerunner", "hasErrors: %b", BladerunnerErrorStatus.hasErrors(jsonObject));
        return BladerunnerErrorStatus.hasErrors(jsonObject);
    }
}
