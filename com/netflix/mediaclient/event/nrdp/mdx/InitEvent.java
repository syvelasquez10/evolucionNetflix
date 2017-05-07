// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx;

import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public class InitEvent extends JsonBaseNccpEvent
{
    public static final Mdx.Events TYPE;
    
    static {
        TYPE = Mdx.Events.mdx_init;
    }
    
    public InitEvent(final JSONObject jsonObject) throws JSONException {
        super(InitEvent.TYPE.getName(), jsonObject);
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
    }
}
