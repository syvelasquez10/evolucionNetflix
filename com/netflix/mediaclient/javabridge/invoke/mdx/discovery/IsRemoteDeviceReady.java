// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.discovery;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
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
    
    private void setArguments(final String s) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("usn", (Object)s);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
