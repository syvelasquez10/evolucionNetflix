// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.mdx;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.ui.Mdx$Events;
import com.netflix.mediaclient.event.nrdp.JsonBaseNccpEvent;

public final class TargetRestartingEvent extends JsonBaseNccpEvent
{
    private static final String ATTR_duration = "duration";
    private static final String ATTR_uuid = "uuid";
    public static final Mdx$Events TYPE;
    private int duration;
    private String uuid;
    
    static {
        TYPE = Mdx$Events.mdx_targetrestarting;
    }
    
    public TargetRestartingEvent(final JSONObject jsonObject) {
        super(TargetRestartingEvent.TYPE.getName(), jsonObject);
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    public String getFromUuid() {
        return this.uuid;
    }
    
    @Override
    public String getObject() {
        return "nrdp.mdx";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.uuid = BaseNccpEvent.getString(jsonObject, "uuid", null);
        this.duration = BaseNccpEvent.getInt(jsonObject, "duration", -1);
    }
}
