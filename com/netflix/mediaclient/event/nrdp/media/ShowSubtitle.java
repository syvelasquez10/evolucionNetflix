// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;

public class ShowSubtitle extends BaseMediaEvent
{
    private static final String ATTR_REMOVE_SUBTITLE = "subtitleID";
    private static final String ATTR_RESERVED = "reserved";
    private static final String ATTR_TEXT = "text";
    public static final String TYPE = "showSubtitle";
    private int reserved;
    private int subtitleID;
    private String text;
    
    public ShowSubtitle(final JSONObject jsonObject) {
        super("showSubtitle", jsonObject);
    }
    
    public int getReserved() {
        return this.reserved;
    }
    
    public int getSubtitleID() {
        return this.subtitleID;
    }
    
    public String getText() {
        return this.text;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.subtitleID = BaseNccpEvent.getInt(jsonObject, "subtitleID", -1);
        this.reserved = BaseNccpEvent.getInt(jsonObject, "reserved", -1);
        this.text = BaseNccpEvent.getString(jsonObject, "text", null);
    }
}
