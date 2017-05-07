// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.registration;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetActivationTokens extends BaseInvoke
{
    private static final String METHOD = "setActivationTokens";
    private static final String PROPERTY_NetflixId = "NetflixId";
    private static final String PROPERTY_SecureNetflixId = "SecureNetflixId";
    private static final String TARGET = "registration";
    
    public SetActivationTokens(final String s, final String s2) {
        super("registration", "setActivationTokens");
        this.setArguments(s, s2);
    }
    
    private void setArguments(final String s, final String s2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("NetflixId", (Object)s);
            jsonObject.put("SecureNetflixId", (Object)s2);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
