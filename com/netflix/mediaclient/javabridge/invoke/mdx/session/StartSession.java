// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class StartSession extends BaseInvoke
{
    public static final String METHOD = "startSession";
    public static final String PROPERTY_pairingContext = "pairingContext";
    public static final String PROPERTY_xid = "xid";
    private static final String TARGET = "mdx";
    
    public StartSession(final String s) {
        super("mdx", "startSession");
        this.setArguments(s, TransactionId.nextTransactionId());
    }
    
    private void setArguments(final String ex, final long n) {
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "pairingContext";
            final JSONException ex2 = ex;
            jsonObject2.put(s, (Object)ex2);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "xid";
            final long n2 = n;
            jsonObject3.put(s2, n2);
            final StartSession startSession = this;
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = jsonObject4.toString();
            startSession.arguments = s3;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "pairingContext";
                final JSONException ex2 = ex;
                jsonObject2.put(s, (Object)ex2);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "xid";
                final long n2 = n;
                jsonObject3.put(s2, n2);
                final StartSession startSession = this;
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = jsonObject4.toString();
                startSession.arguments = s3;
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
