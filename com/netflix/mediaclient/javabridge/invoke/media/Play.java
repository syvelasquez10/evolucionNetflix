// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public final class Play extends BaseInvoke
{
    private static final String METHOD = "play";
    private static final String PROPERTY_TIME = "ms";
    private static final String TARGET = "media";
    
    public Play() {
        super("media", "play");
    }
    
    public Play(final long arguments) {
        super("media", "play");
        this.setArguments(arguments);
    }
    
    private void setArguments(final long n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("ms", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
