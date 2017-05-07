// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetStreamingBuffer extends BaseInvoke
{
    private static final String METHOD = "setStreamingBuffer";
    private static final String PROPERTY_maxBufferLen = "maxBufferLen";
    private static final String PROPERTY_minBufferLen = "minBufferLen";
    private static final String PROPERTY_powerSaving = "powerSaving";
    private static final String TARGET = "media";
    
    public SetStreamingBuffer(final boolean b, final int n, final int n2) {
        super("media", "setStreamingBuffer");
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("powerSaving", b);
            jsonObject.put("maxBufferLen", n);
            jsonObject.put("minBufferLen", n2);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
