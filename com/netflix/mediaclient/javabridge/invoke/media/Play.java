// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "ms";
            final long n2 = n;
            jsonObject2.put(s, n2);
            final Play play = this;
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = jsonObject3.toString();
            play.arguments = s2;
            return;
        }
        catch (JSONException ex) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "ms";
                final long n2 = n;
                jsonObject2.put(s, n2);
                final Play play = this;
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = jsonObject3.toString();
                play.arguments = s2;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)jsonObject);
            }
            catch (JSONException jsonObject) {
                continue;
            }
            break;
        }
    }
}
