// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class EndSession extends BaseInvoke
{
    public static final String METHOD = "endSession";
    public static final String PROPERTY_sessionId = "sid";
    private static final String TARGET = "mdx";
    
    public EndSession(final int arguments) {
        super("mdx", "endSession");
        this.setArguments(arguments);
    }
    
    private void setArguments(final int n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("sid", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
