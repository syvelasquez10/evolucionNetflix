// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class SetVideoBitrateRange extends BaseMediaEvent
{
    private static final String ATTR_ERROR = "error";
    public static final String TYPE = "setvideobitraterange";
    private boolean error;
    
    public SetVideoBitrateRange(final JSONObject jsonObject) {
        super("setvideobitraterange", jsonObject);
        this.error = false;
    }
    
    public boolean isError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.error = BaseNccpEvent.getBoolean(jsonObject, "error", false);
    }
}
