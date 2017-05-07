// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import org.json.JSONException;
import org.json.JSONObject;

public class GenericMediaEvent extends BaseMediaEvent
{
    public static final String bufferingComplete = "bufferingComplete";
    public static final String endOfStream = "endOfStream";
    public static final String openComplete = "openComplete";
    public static final String underflow = "underflow";
    
    public GenericMediaEvent(final String s) {
        super(s);
    }
    
    @Override
    protected String getJSON() {
        return "{}";
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
    }
}
