// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateVideoBitrate extends BaseMediaEvent
{
    private static final String ATTR_BPS = "bitsPerSecond";
    public static final String TYPE = "updateVideoBitrate";
    private int bitsPerSecond;
    
    public UpdateVideoBitrate(final JSONObject jsonObject) throws JSONException {
        super("updateVideoBitrate", jsonObject);
    }
    
    public int getBitsPerSecond() {
        return this.bitsPerSecond;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.bitsPerSecond = BaseNccpEvent.getInt(jsonObject, "bitsPerSecond", -1);
    }
}
