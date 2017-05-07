// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetLanguage extends BaseInvoke
{
    private static final String METHOD = "setLanguage";
    private static final String PROPERTY_LANGUAGE = "language";
    private static final String TARGET = "android";
    
    public SetLanguage(final String arguments) {
        super("android", "setLanguage");
        this.setArguments(arguments);
    }
    
    private void setArguments(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("language", (Object)s);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
