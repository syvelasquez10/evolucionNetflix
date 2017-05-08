// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class LinksParamBuilder
{
    protected static final String TAG = "nf_msl_volley_bladerunner";
    JSONObject mLink;
    
    String build() {
        return this.getRequestObject(this.getParams()).toString();
    }
    
    protected JSONObject getParams() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("clientTime", TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.e("nf_msl_volley_bladerunner", (Throwable)ex, "error creating params", new Object[0]);
            return jsonObject;
        }
    }
    
    protected JSONObject getRequestObject(JSONObject jsonObject) {
        jsonObject = new JSONObject();
        try {
            jsonObject.put("version", 2);
            if (this.mLink != null) {
                jsonObject.put("method", (Object)this.mLink.optString("rel"));
                jsonObject.put("url", (Object)this.mLink.optString("href"));
            }
            jsonObject.putOpt("params", (Object)this.getParams());
            return jsonObject;
        }
        catch (JSONException ex) {
            Log.e("nf_msl_volley_bladerunner", (Throwable)ex, "error creating request object", new Object[0]);
            return jsonObject;
        }
    }
    
    LinksParamBuilder setLink(final JSONObject mLink) {
        this.mLink = mLink;
        return this;
    }
}
