// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.pair;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class DeletePairing extends BaseInvoke
{
    private static final String METHOD = "dePair";
    private static final String PROPERTY_pairingContext = "pairingContext";
    private static final String TARGET = "mdx";
    
    public DeletePairing(final String arguments) {
        super("mdx", "dePair");
        this.setArguments(arguments);
    }
    
    private void setArguments(final String ex) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "pairingContext";
            final JSONException ex2 = ex;
            jsonObject2.put(s, (Object)ex2);
            final DeletePairing deletePairing = this;
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = jsonObject3.toString();
            deletePairing.arguments = s2;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "pairingContext";
                final JSONException ex2 = ex;
                jsonObject2.put(s, (Object)ex2);
                final DeletePairing deletePairing = this;
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = jsonObject3.toString();
                deletePairing.arguments = s2;
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
