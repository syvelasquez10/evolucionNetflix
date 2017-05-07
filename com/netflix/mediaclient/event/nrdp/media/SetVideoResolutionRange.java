// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class SetVideoResolutionRange extends BaseMediaEvent
{
    public static final String ATTR_ERROR = "error";
    public static final String TYPE = "setvideoresolutionrange";
    private boolean error;
    
    public SetVideoResolutionRange(final JSONObject jsonObject) {
        super("setvideoresolutionrange", jsonObject);
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
