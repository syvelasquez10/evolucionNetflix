// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class SubtitleData extends BaseMediaEvent
{
    private static final String ATTR_DATA = "data";
    public static final String TYPE = "subtitledata";
    private String mXml;
    
    public SubtitleData(final JSONObject jsonObject) throws JSONException {
        super("subtitledata", jsonObject);
    }
    
    public String getXml() {
        return this.mXml;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.mXml = BaseNccpEvent.getString(jsonObject, "data", null);
    }
}
