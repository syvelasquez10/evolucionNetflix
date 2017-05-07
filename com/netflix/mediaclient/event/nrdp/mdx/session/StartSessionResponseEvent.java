// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.session;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class StartSessionResponseEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_pairingContext = "pairingContext";
    private static final String ATTR_sid = "sid";
    public static final Mdx.Events TYPE;
    private String pairingContext;
    private int sid;
    
    static {
        TYPE = Mdx.Events.mdx_session_startSessionResponse;
    }
    
    public StartSessionResponseEvent(final JSONObject jsonObject) throws JSONException {
        super(StartSessionResponseEvent.TYPE.getName(), jsonObject);
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
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.pairingContext = BaseNccpEvent.getUrlDecodedString(jsonObject, "pairingContext", null);
        this.sid = BaseNccpEvent.getInt(jsonObject, "sid", Integer.MIN_VALUE);
    }
}
