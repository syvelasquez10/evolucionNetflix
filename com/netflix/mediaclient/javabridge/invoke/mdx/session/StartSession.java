// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
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
    
    private void setArguments(final String s, final long n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("pairingContext", (Object)s);
            jsonObject.put("xid", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
