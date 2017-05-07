// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.StreamInfo;

public class NewStream extends BaseMediaEvent
{
    private static final String ATTR_START_PTS = "startPts";
    private static final String ATTR_STREAM_INFO = "streamInfo";
    public static final String TYPE = "newStream";
    private int startPts;
    private StreamInfo streamInfo;
    
    public NewStream(final JSONObject jsonObject) {
        super("newStream", jsonObject);
    }
    
    public int getStartPts() {
        return this.startPts;
    }
    
    public StreamInfo getStreamInfo() {
        return this.streamInfo;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.startPts = BaseNccpEvent.getInt(jsonObject, "startPts", 0);
        if (jsonObject.has("streamInfo")) {
            this.streamInfo = new StreamInfo(jsonObject.getJSONObject("streamInfo"));
        }
    }
}
