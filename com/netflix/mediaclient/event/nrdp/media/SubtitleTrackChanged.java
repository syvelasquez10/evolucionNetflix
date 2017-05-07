// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class SubtitleTrackChanged extends BaseMediaEvent
{
    private static final String ATTR_TRACK_INDEX = "trackIndex";
    public static final String TYPE = "subtitleTrackChanged";
    private int trackIndex;
    
    public SubtitleTrackChanged(final JSONObject jsonObject) {
        super("subtitleTrackChanged", jsonObject);
    }
    
    public int getTrackIndex() {
        return this.trackIndex;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.trackIndex = BaseNccpEvent.getInt(jsonObject, "trackIndex", -1);
    }
}
