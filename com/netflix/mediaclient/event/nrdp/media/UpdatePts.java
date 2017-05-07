// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class UpdatePts extends BaseMediaEvent
{
    private static final String ATTR_PTS = "pts";
    public static final String TYPE = "updatePts";
    private int pts;
    
    public UpdatePts(final JSONObject jsonObject) {
        super("updatePts", jsonObject);
    }
    
    public int getPts() {
        return this.pts;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.pts = BaseNccpEvent.getInt(jsonObject, "pts", 0);
    }
}
