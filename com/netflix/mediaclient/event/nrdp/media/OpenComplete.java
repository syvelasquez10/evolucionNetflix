// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONObject;
import com.netflix.mediaclient.media.Watermark;

public class OpenComplete extends BaseMediaEvent
{
    private static final String ATTR_SESSIONID = "sessionid";
    private static final String ATTR_WATERMARK = "watermark";
    public static final String TYPE = "openComplete";
    private String sessionId;
    private Watermark watermark;
    
    public OpenComplete(final JSONObject jsonObject) {
        super("openComplete", jsonObject);
    }
    
    public String getSessionId() {
        return this.sessionId;
    }
    
    public Watermark getWatermark() {
        return this.watermark;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) {
        this.sessionId = BaseNccpEvent.getString(jsonObject, "sessionid", "");
        this.watermark = Watermark.createWatermark(jsonObject.optJSONObject("watermark"));
    }
    
    @Override
    public String toString() {
        return "OpenComplete{sessionId='" + this.sessionId + '\'' + ", watermark=" + this.watermark + "} " + super.toString();
    }
}
