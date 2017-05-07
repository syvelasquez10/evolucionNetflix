// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.proxy.nrdp.media.StreamInfo;

public class StreamSelected extends BaseMediaEvent
{
    private static final String ATTR_REBUFFER = "rebuffer";
    private static final String ATTR_START_PTS = "startPts";
    private static final String ATTR_STREAM_INFO = "streamInfo";
    public static final String TYPE = "streamSelected";
    private int rebuffer;
    private int startPts;
    private StreamInfo streamInfo;
    
    public StreamSelected(final JSONObject jsonObject) throws JSONException {
        super("streamSelected", jsonObject);
    }
    
    public int getRebuffer() {
        return this.rebuffer;
    }
    
    public int getStartPts() {
        return this.startPts;
    }
    
    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.startPts = BaseNccpEvent.getInt(jsonObject, "startPts", 0);
        this.rebuffer = BaseNccpEvent.getInt(jsonObject, "rebuffer", 0);
        if (jsonObject.has("streamInfo")) {
            this.streamInfo = new StreamInfo(jsonObject.getJSONObject("streamInfo"));
        }
    }
}
