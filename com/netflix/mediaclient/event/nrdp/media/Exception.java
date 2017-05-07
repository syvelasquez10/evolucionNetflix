// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class Exception extends BaseMediaEvent
{
    private static final String ATTR_EXCEPTION = "exception";
    public static final String TYPE = "exception";
    private String exception;
    
    public Exception(final JSONObject jsonObject) {
        super("exception", jsonObject);
    }
    
    public String getException() {
        return this.exception;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.exception = BaseNccpEvent.getString(jsonObject, "exception", null);
    }
}
