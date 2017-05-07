// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class Skip extends BaseMediaEvent
{
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_PTS = "pts";
    public static final String TYPE = "skip";
    private boolean error;
    private int pts;
    
    public Skip(final JSONObject jsonObject) throws JSONException {
        super("skip", jsonObject);
        this.error = false;
    }
    
    public int getPts() {
        return this.pts;
    }
    
    public boolean isError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.pts = BaseNccpEvent.getInt(jsonObject, "pts", 0);
        this.error = BaseNccpEvent.getBoolean(jsonObject, "error", false);
    }
}
