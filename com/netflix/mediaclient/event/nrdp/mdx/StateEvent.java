// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class StateEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_status = "status";
    public static final Mdx.Events TYPE;
    private static final String VALUE_status_NOT_READY = "NOT_READY";
    private static final String VALUE_status_READY = "READY";
    private boolean ready;
    
    static {
        TYPE = Mdx.Events.mdx_mdxstate;
    }
    
    public StateEvent(final JSONObject jsonObject) throws JSONException {
        super(StateEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    public boolean isReady() {
        return this.ready;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.ready = "READY".equalsIgnoreCase(BaseNccpEvent.getString(jsonObject, "status", "NOT_READY"));
    }
}
