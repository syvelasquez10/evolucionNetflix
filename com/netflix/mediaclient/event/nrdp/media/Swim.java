// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class Swim extends BaseMediaEvent
{
    private static final String ATTR_CURRENT_PTS = "currentPts";
    private static final String ATTR_END_PTS = "endPts";
    private static final String ATTR_ERROR = "error";
    private static final String ATTR_PTS = "pts";
    private static final String ATTR_START_PTS = "startPts";
    public static final String TYPE = "swim";
    private int currentPts;
    private int endPts;
    private boolean error;
    private int pts;
    private int startPts;
    
    public Swim(final JSONObject jsonObject) throws JSONException {
        super("swim", jsonObject);
        this.error = false;
    }
    
    public int getCurrentPts() {
        return this.currentPts;
    }
    
    public int getEndPts() {
        return this.endPts;
    }
    
    public int getPts() {
        return this.pts;
    }
    
    public int getStartPts() {
        return this.startPts;
    }
    
    public boolean isError() {
        return this.error;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.startPts = BaseNccpEvent.getInt(jsonObject, "startPts", 0);
        this.endPts = BaseNccpEvent.getInt(jsonObject, "endPts", 0);
        this.currentPts = BaseNccpEvent.getInt(jsonObject, "currentPts", 0);
        this.pts = BaseNccpEvent.getInt(jsonObject, "pts", 0);
        this.error = BaseNccpEvent.getBoolean(jsonObject, "error", false);
    }
}
