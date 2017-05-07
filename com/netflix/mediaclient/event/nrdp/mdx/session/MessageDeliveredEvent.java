// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.session;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class MessageDeliveredEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_transactionId = "transactionId";
    public static final Mdx$Events TYPE;
    private String pairingContext;
    private int transactionId;
    
    static {
        TYPE = Mdx$Events.mdx_session_messagedelivered;
    }
    
    public MessageDeliveredEvent(final JSONObject jsonObject) {
        super(MessageDeliveredEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public String getPairingContext() {
        return this.pairingContext;
    }
    
    public int getTransactionId() {
        return this.transactionId;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.transactionId = BaseNccpEvent.getInt(jsonObject, "transactionId", -1);
    }
}
