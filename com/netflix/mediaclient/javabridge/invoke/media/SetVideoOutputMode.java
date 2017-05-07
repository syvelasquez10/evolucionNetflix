// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoOutputMode extends BaseInvoke
{
    private static final String METHOD = "setVideoOutputMode";
    private static final String PROPERTY_mode = "mode";
    private static final String TARGET = "media";
    
    public SetVideoOutputMode(final boolean arguments) {
        super("media", "setVideoOutputMode");
        this.setArguments(arguments);
    }
    
    private void setArguments(final boolean b) {
        try {
            final JSONObject jsonObject = new JSONObject();
            String s;
            if (b) {
                s = "internal";
            }
            else {
                s = "external";
            }
            jsonObject.put("mode", (Object)s);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
