// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.discovery;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class IsRemoteDeviceReady extends BaseInvoke
{
    private static final String METHOD = "isRemoteDeviceReady";
    private static final String PROPERTY_usn = "usn";
    private static final String TARGET = "mdx";
    
    public IsRemoteDeviceReady(final String arguments) {
        super("mdx", "isRemoteDeviceReady");
        this.setArguments(arguments);
    }
    
    private void setArguments(final String ex) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "usn";
            final JSONException ex2 = ex;
            jsonObject2.put(s, (Object)ex2);
            final IsRemoteDeviceReady isRemoteDeviceReady = this;
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = jsonObject3.toString();
            isRemoteDeviceReady.arguments = s2;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "usn";
                final JSONException ex2 = ex;
                jsonObject2.put(s, (Object)ex2);
                final IsRemoteDeviceReady isRemoteDeviceReady = this;
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = jsonObject3.toString();
                isRemoteDeviceReady.arguments = s2;
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
