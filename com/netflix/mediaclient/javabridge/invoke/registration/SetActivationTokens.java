// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.registration;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    private void setArguments(final String ex, final String s) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s2 = "NetflixId";
            final JSONException ex2 = ex;
            jsonObject2.put(s2, (Object)ex2);
            final JSONObject jsonObject3 = jsonObject;
            final String s3 = "SecureNetflixId";
            final String s4 = s;
            jsonObject3.put(s3, (Object)s4);
            final SetActivationTokens setActivationTokens = this;
            final JSONObject jsonObject4 = jsonObject;
            final String s5 = jsonObject4.toString();
            setActivationTokens.arguments = s5;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s2 = "NetflixId";
                final JSONException ex2 = ex;
                jsonObject2.put(s2, (Object)ex2);
                final JSONObject jsonObject3 = jsonObject;
                final String s3 = "SecureNetflixId";
                final String s4 = s;
                jsonObject3.put(s3, (Object)s4);
                final SetActivationTokens setActivationTokens = this;
                final JSONObject jsonObject4 = jsonObject;
                final String s5 = jsonObject4.toString();
                setActivationTokens.arguments = s5;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
}
