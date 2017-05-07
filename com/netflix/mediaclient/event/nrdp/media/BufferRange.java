// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class BufferRange extends BaseMediaEvent
{
    private static final String ATTR_CURRENT_PTS = "currentPts";
    private static final String ATTR_END_PTS = "endPts";
    private static final String ATTR_START_PTS = "startPts";
    private static final String ATTR_bandwidth = "bandwidth";
    private static final String ATTR_rebuffer = "rebuffer";
    public static final String TYPE = "bufferrange";
    private int bandwidth;
    private int currentPts;
    private int endPts;
    private int rebuffer;
    private int startPts;
    
    public BufferRange(final JSONObject jsonObject) throws JSONException {
        super("bufferrange", jsonObject);
    }
    
    public int getBandwidth() {
        return this.bandwidth;
    }
    
    public int getCurrentPts() {
        return this.currentPts;
    }
    
    public int getEndPts() {
        return this.endPts;
    }
    
    public int getRebuffer() {
        return this.rebuffer;
    }
    
    public int getStartPts() {
        return this.startPts;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.startPts = BaseNccpEvent.getInt(jsonObject, "startPts", 0);
        this.endPts = BaseNccpEvent.getInt(jsonObject, "endPts", 0);
        this.currentPts = BaseNccpEvent.getInt(jsonObject, "currentPts", 0);
        this.bandwidth = BaseNccpEvent.getInt(jsonObject, "bandwidth", 0);
        this.rebuffer = BaseNccpEvent.getInt(jsonObject, "rebuffer", 0);
    }
}
