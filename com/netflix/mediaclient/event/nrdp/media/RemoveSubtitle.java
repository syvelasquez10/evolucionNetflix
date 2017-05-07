// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class RemoveSubtitle extends BaseMediaEvent
{
    private static final String ATTR_REMOVE_SUBTITLE = "subtitleID";
    public static final String TYPE = "removeSubtitle";
    private int subtitleID;
    
    public RemoveSubtitle(final JSONObject jsonObject) {
        super("removeSubtitle", jsonObject);
    }
    
    public int getSubtitleID() {
        return this.subtitleID;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.subtitleID = BaseNccpEvent.getInt(jsonObject, "subtitleID", -1);
    }
}
