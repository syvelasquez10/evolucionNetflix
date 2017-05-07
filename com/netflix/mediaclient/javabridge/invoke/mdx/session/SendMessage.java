// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.javabridge.ui.mdxcontroller.TransactionId;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SendMessage extends BaseInvoke
{
    public static final String METHOD = "sendMessage";
    public static final String PROPERTY_messageName = "messageName";
    public static final String PROPERTY_messageObject = "messageObject";
    public static final String PROPERTY_sessionId = "sessionId";
    public static final String PROPERTY_xid = "xid";
    private static final String TARGET = "mdx";
    private long xid;
    
    public SendMessage(final int n, final String s, final JSONObject jsonObject) {
        super("mdx", "sendMessage");
        this.xid = TransactionId.nextTransactionId();
        this.setArguments(n, s, jsonObject);
    }
    
    private void setArguments(final int n, final String s, final JSONObject jsonObject) {
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("sessionId", n);
            jsonObject2.put("messageName", (Object)s);
            jsonObject2.put("messageObject", (Object)jsonObject);
            jsonObject2.put("xid", this.xid);
            this.arguments = jsonObject2.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
    
    public long getXid() {
        return this.xid;
    }
}
