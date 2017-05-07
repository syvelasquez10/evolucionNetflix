// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.session;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class MessageEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_msgObject = "msgObject";
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_sid = "sid";
    private static final String ATTR_transactionId = "transactionId";
    private static final String ATTR_type = "type";
    public static final Mdx$Events TYPE;
    private JSONObject msgObject;
    private String pairingContext;
    private int sid;
    private int transactionId;
    private String type;
    
    static {
        TYPE = Mdx$Events.mdx_session_message;
    }
    
    public MessageEvent(final JSONObject jsonObject) {
        super(MessageEvent.TYPE.getName(), jsonObject);
    }
    
    public JSONObject getMsgObject() {
        return this.msgObject;
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
    public String getType() {
        return this.type;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.transactionId = BaseNccpEvent.getInt(jsonObject, "transactionId", -1);
        this.type = BaseNccpEvent.getString(jsonObject, "type", null);
        this.sid = BaseNccpEvent.getInt(jsonObject, "sid", -1);
        this.msgObject = BaseNccpEvent.getJSONObject(jsonObject, "msgObject", null);
    }
}
