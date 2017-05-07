// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class Statechanged extends BaseMediaEvent
{
    private static final String ATTR_STATE = "state";
    public static final String TYPE = "statechanged";
    private int state;
    
    public Statechanged(final JSONObject jsonObject) {
        super("statechanged", jsonObject);
    }
    
    public int getState() {
        return this.state;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.state = BaseNccpEvent.getInt(jsonObject, "state", -1);
    }
}
