// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class Buffering extends BaseMediaEvent
{
    private static final String ATTR_PERCENTAGE = "percentage";
    public static final String TYPE = "buffering";
    private int percentage;
    
    public Buffering(final JSONObject jsonObject) throws JSONException {
        super("buffering", jsonObject);
    }
    
    public int getPercentage() {
        return this.percentage;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.percentage = BaseNccpEvent.getInt(jsonObject, "percentage", 0);
    }
}
