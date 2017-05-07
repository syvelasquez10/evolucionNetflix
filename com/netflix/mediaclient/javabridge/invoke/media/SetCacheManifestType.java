// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetCacheManifestType extends BaseInvoke
{
    private static final String METHOD = "setCacheManifestType";
    private static final String TARGET = "media";
    
    public SetCacheManifestType(final int n) {
        super("media", "setCacheManifestType");
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
