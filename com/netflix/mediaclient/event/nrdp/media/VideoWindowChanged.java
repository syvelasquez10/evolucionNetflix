// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp.media;

import com.netflix.mediaclient.event.nrdp.BaseNccpEvent;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoWindowChanged extends BaseMediaEvent
{
    private static final String ATTR_HEIGHT = "height";
    private static final String ATTR_WIDTH = "width";
    private static final String ATTR_X = "x";
    private static final String ATTR_Y = "y";
    public static final String TYPE = "videoWindowChanged";
    private int height;
    private int width;
    private int x;
    private int y;
    
    public VideoWindowChanged(final JSONObject jsonObject) throws JSONException {
        super("videoWindowChanged", jsonObject);
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    @Override
    protected void populate(final JSONObject jsonObject) throws JSONException {
        this.x = BaseNccpEvent.getInt(jsonObject, "x", 0);
        this.y = BaseNccpEvent.getInt(jsonObject, "y", 0);
        this.width = BaseNccpEvent.getInt(jsonObject, "width", 0);
        this.height = BaseNccpEvent.getInt(jsonObject, "height", 0);
    }
}
