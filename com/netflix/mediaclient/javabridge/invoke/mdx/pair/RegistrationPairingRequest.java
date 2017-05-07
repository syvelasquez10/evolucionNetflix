// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.pair;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class RegistrationPairingRequest extends BaseInvoke
{
    private static final String METHOD = "regPairRequest";
    private static final String PROPERTY_pin = "pin";
    private static final String PROPERTY_remotedevice = "remotedevice";
    private static final String TARGET = "mdx";
    
    public RegistrationPairingRequest(final String s, final String s2) {
        super("mdx", "regPairRequest");
        this.setArguments(s, s2);
    }
    
    private void setArguments(final String s, final String s2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("remotedevice", (Object)s);
            if (s2 != null) {
                jsonObject.put("pin", (Object)s2);
            }
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
