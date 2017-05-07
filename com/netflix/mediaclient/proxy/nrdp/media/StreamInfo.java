// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.proxy.nrdp.media;

import org.json.JSONException;
import org.json.JSONObject;

public class StreamInfo
{
    public static final int AUDIO_STREAM = 0;
    public static final int TIMEDTEXT_STREAM = 2;
    public static final int UNKNOWN_STREAM = -1;
    public static final int VIDEO_STREAM = 1;
    private int bitsPerSecond;
    private int displayAspectRatioX;
    private int displayAspectRatioY;
    private int frameHeight;
    private int frameWidth;
    private boolean isHighDefinition;
    private boolean isSuperHighDefinition;
    private int streamType;
    
    public StreamInfo(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            throw new IllegalArgumentException("JSON is null!");
        }
        this.populate(jsonObject);
    }
    
    private void populate(final JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("streamType")) {
            this.streamType = jsonObject.getInt("streamType");
        }
        if (jsonObject.has("bitsPerSecond")) {
            this.bitsPerSecond = jsonObject.getInt("bitsPerSecond");
        }
        if (jsonObject.has("isHighDefinition")) {
            this.isHighDefinition = jsonObject.getBoolean("isHighDefinition");
        }
        if (jsonObject.has("isSuperHighDefinition")) {
            this.isSuperHighDefinition = jsonObject.getBoolean("isSuperHighDefinition");
        }
        if (jsonObject.has("frameWidth")) {
            this.frameWidth = jsonObject.getInt("frameWidth");
        }
        if (jsonObject.has("frameHeight")) {
            this.frameHeight = jsonObject.getInt("frameHeight");
        }
        if (jsonObject.has("displayAspectRatioX")) {
            this.displayAspectRatioX = jsonObject.getInt("displayAspectRatioX");
        }
        if (jsonObject.has("displayAspectRatioY")) {
            this.displayAspectRatioY = jsonObject.getInt("displayAspectRatioY");
        }
    }
    
    public int getBitsPerSecond() {
        return this.bitsPerSecond;
    }
    
    public int getDisplayAspectRatioX() {
        return this.displayAspectRatioX;
    }
    
    public int getDisplayAspectRatioY() {
        return this.displayAspectRatioY;
    }
    
    public int getFrameHeight() {
        return this.frameHeight;
    }
    
    public int getFrameWidth() {
        return this.frameWidth;
    }
    
    public int getStreamType() {
        return this.streamType;
    }
    
    public boolean isHighDefinition() {
        return this.isHighDefinition;
    }
    
    public boolean isSuperHighDefinition() {
        return this.isSuperHighDefinition;
    }
    
    @Override
    public String toString() {
        return "StreamInfo [streamType=" + this.streamType + ", bitsPerSecond=" + this.bitsPerSecond + ", isHighDefinition=" + this.isHighDefinition + ", isSuperHighDefinition=" + this.isSuperHighDefinition + ", frameWidth=" + this.frameWidth + ", frameHeight=" + this.frameHeight + ", displayAspectRatioX=" + this.displayAspectRatioX + ", displayAspectRatioY=" + this.displayAspectRatioY + "]";
    }
}
