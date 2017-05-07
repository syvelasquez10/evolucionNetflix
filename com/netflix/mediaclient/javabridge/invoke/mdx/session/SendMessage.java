// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.mdx.session;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
    
    private void setArguments(final int n, final String ex, final JSONObject jsonObject) {
        JSONObject jsonObject2 = null;
        try {
            final JSONObject jsonObject3;
            jsonObject2 = (jsonObject3 = new JSONObject());
            final String s = "sessionId";
            final int n2 = n;
            jsonObject3.put(s, n2);
            final JSONObject jsonObject4 = jsonObject2;
            final String s2 = "messageName";
            final JSONException ex2 = ex;
            jsonObject4.put(s2, (Object)ex2);
            final JSONObject jsonObject5 = jsonObject2;
            final String s3 = "messageObject";
            final JSONObject jsonObject6 = jsonObject;
            jsonObject5.put(s3, (Object)jsonObject6);
            final JSONObject jsonObject7 = jsonObject2;
            final String s4 = "xid";
            final SendMessage sendMessage = this;
            final long n3 = sendMessage.xid;
            jsonObject7.put(s4, n3);
            final SendMessage sendMessage2 = this;
            final JSONObject jsonObject8 = jsonObject2;
            final String s5 = jsonObject8.toString();
            sendMessage2.arguments = s5;
            return;
        }
        catch (JSONException ex3) {}
        while (true) {
            try {
                final JSONObject jsonObject3 = jsonObject2;
                final String s = "sessionId";
                final int n2 = n;
                jsonObject3.put(s, n2);
                final JSONObject jsonObject4 = jsonObject2;
                final String s2 = "messageName";
                final JSONException ex2 = ex;
                jsonObject4.put(s2, (Object)ex2);
                final JSONObject jsonObject5 = jsonObject2;
                final String s3 = "messageObject";
                final JSONObject jsonObject6 = jsonObject;
                jsonObject5.put(s3, (Object)jsonObject6);
                final JSONObject jsonObject7 = jsonObject2;
                final String s4 = "xid";
                final SendMessage sendMessage = this;
                final long n3 = sendMessage.xid;
                jsonObject7.put(s4, n3);
                final SendMessage sendMessage2 = this;
                final JSONObject jsonObject8 = jsonObject2;
                final String s5 = jsonObject8.toString();
                sendMessage2.arguments = s5;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public long getXid() {
        return this.xid;
    }
}
