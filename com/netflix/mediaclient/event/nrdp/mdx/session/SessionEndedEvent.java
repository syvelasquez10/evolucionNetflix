// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx.session;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class SessionEndedEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_sid = "sid";
    public static final Mdx.Events TYPE;
    private int sid;
    
    static {
        TYPE = Mdx.Events.mdx_session_sessionended;
    }
    
    public SessionEndedEvent(final JSONObject jsonObject) throws JSONException {
        super(SessionEndedEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public int getSid() {
        return this.sid;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.sid = BaseNccpEvent.getInt(jsonObject, "sid", -1);
    }
}
