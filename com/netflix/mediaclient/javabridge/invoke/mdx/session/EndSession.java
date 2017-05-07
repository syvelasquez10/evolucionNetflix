// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "sid";
            final int n2 = n;
            jsonObject2.put(s, n2);
            final EndSession endSession = this;
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = jsonObject3.toString();
            endSession.arguments = s2;
            return;
        }
        catch (JSONException ex) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "sid";
                final int n2 = n;
                jsonObject2.put(s, n2);
                final EndSession endSession = this;
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = jsonObject3.toString();
                endSession.arguments = s2;
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
