// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetNetworkParameters extends BaseInvoke
{
    private static final String METHOD = "setNetworkParameters";
    private static final String PROPERTY_carrier = "carrier";
    private static final String PROPERTY_countryCode = "countryCode";
    private static final String PROPERTY_lastBytesReceived = "lastBytesReceived";
    private static final String PROPERTY_networkCode = "networkCode";
    private static final String PROPERTY_networkSpec = "networkSpec";
    private static final String PROPERTY_networkType = "networkType";
    private static final String TARGET = "media";
    
    public SetNetworkParameters(final String s, final String s2, final String s3, final String s4, final String s5, final int n) {
        super("media", "setNetworkParameters");
        this.setArguments(s, s2, s3, s4, s5, n);
    }
    
    private void setArguments(final String s, final String s2, final String s3, final String s4, final String s5, final int n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("carrier", (Object)s);
            jsonObject.put("countryCode", (Object)s2);
            jsonObject.put("networkCode", (Object)s3);
            jsonObject.put("networkType", (Object)s4);
            jsonObject.put("networkSpec", (Object)s5);
            jsonObject.put("lastBytesReceived", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
