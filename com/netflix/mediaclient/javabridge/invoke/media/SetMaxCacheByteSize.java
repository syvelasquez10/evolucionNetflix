// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetMaxCacheByteSize extends BaseInvoke
{
    private static final String METHOD = "setMaxCacheByteSize";
    private static final String TARGET = "media";
    
    public SetMaxCacheByteSize(final int n) {
        super("media", "setMaxCacheByteSize");
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("size", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
