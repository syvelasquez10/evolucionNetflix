// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.session;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class MessagingErrorEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_errorCode = "errorCode";
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_sid = "sid";
    private static final String ATTR_transactionId = "transactionId";
    public static final Mdx.Events TYPE;
    private int errorCode;
    private String pairingContext;
    private int sid;
    private int transactionId;
    
    static {
        TYPE = Mdx.Events.mdx_session_messagingerror;
    }
    
    public MessagingErrorEvent(final JSONObject jsonObject) throws JSONException {
        super(MessagingErrorEvent.TYPE.getName(), jsonObject);
    }
    
    public String getError() {
        return String.valueOf(this.errorCode);
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public String getPairingContext() {
        return this.pairingContext;
    }
    
    public int getSid() {
        return this.sid;
    }
    
    public int getTransactionId() {
        return this.transactionId;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.transactionId = BaseNccpEvent.getInt(jsonObject, "transactionId", -1);
        this.sid = BaseNccpEvent.getInt(jsonObject, "sid", -1);
        this.errorCode = BaseNccpEvent.getInt(jsonObject, "errorCode", -1);
    }
}
