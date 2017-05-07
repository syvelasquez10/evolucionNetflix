// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class Error extends BaseMediaEvent
{
    private static final String ATTR_ERROR = "error";
    public static final String TYPE = "error";
    private int error;
    
    public Error(final JSONObject jsonObject) throws JSONException {
        super("error", jsonObject);
    }
    
    public int getError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.error = BaseNccpEvent.getInt(jsonObject, "error", 0);
    }
}
