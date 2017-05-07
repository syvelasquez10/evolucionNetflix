// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    private void setArguments(final String ex, final String s, final String s2, final String s3, final String s4, final int n) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s5 = "carrier";
            final JSONException ex2 = ex;
            jsonObject2.put(s5, (Object)ex2);
            final JSONObject jsonObject3 = jsonObject;
            final String s6 = "countryCode";
            final String s7 = s;
            jsonObject3.put(s6, (Object)s7);
            final JSONObject jsonObject4 = jsonObject;
            final String s8 = "networkCode";
            final String s9 = s2;
            jsonObject4.put(s8, (Object)s9);
            final JSONObject jsonObject5 = jsonObject;
            final String s10 = "networkType";
            final String s11 = s3;
            jsonObject5.put(s10, (Object)s11);
            final JSONObject jsonObject6 = jsonObject;
            final String s12 = "networkSpec";
            final String s13 = s4;
            jsonObject6.put(s12, (Object)s13);
            final JSONObject jsonObject7 = jsonObject;
            final String s14 = "lastBytesReceived";
            final int n2 = n;
            jsonObject7.put(s14, n2);
            final SetNetworkParameters setNetworkParameters = this;
            final JSONObject jsonObject8 = jsonObject;
            final String s15 = jsonObject8.toString();
            setNetworkParameters.arguments = s15;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s5 = "carrier";
                final JSONException ex2 = ex;
                jsonObject2.put(s5, (Object)ex2);
                final JSONObject jsonObject3 = jsonObject;
                final String s6 = "countryCode";
                final String s7 = s;
                jsonObject3.put(s6, (Object)s7);
                final JSONObject jsonObject4 = jsonObject;
                final String s8 = "networkCode";
                final String s9 = s2;
                jsonObject4.put(s8, (Object)s9);
                final JSONObject jsonObject5 = jsonObject;
                final String s10 = "networkType";
                final String s11 = s3;
                jsonObject5.put(s10, (Object)s11);
                final JSONObject jsonObject6 = jsonObject;
                final String s12 = "networkSpec";
                final String s13 = s4;
                jsonObject6.put(s12, (Object)s13);
                final JSONObject jsonObject7 = jsonObject;
                final String s14 = "lastBytesReceived";
                final int n2 = n;
                jsonObject7.put(s14, n2);
                final SetNetworkParameters setNetworkParameters = this;
                final JSONObject jsonObject8 = jsonObject;
                final String s15 = jsonObject8.toString();
                setNetworkParameters.arguments = s15;
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
