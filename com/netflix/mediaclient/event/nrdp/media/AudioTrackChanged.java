// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class AudioTrackChanged extends BaseMediaEvent
{
    private static final String ATTR_TRACK_INDEX = "trackIndex";
    public static final String TYPE = "audioTrackChanged";
    private int trackIndex;
    
    public AudioTrackChanged(final JSONObject jsonObject) {
        super("audioTrackChanged", jsonObject);
    }
    
    public int getTrackIndex() {
        return this.trackIndex;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.trackIndex = BaseNccpEvent.getInt(jsonObject, "trackIndex", -1);
    }
}
