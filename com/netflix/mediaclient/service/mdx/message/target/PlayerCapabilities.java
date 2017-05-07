// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.message.target;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.service.mdx.message.MdxMessage;

public final class PlayerCapabilities extends MdxMessage
{
    private static final String PROPERTY_autoAdvanceMax = "autoAdvanceMax";
    private static final String PROPERTY_is3dSupported = "is3dSupported";
    private static final String PROPERTY_is5dot1Supported = "is5dot1Supported";
    private static final String PROPERTY_isHdSupported = "isHdSupported";
    private static final String PROPERTY_volumeControl = "volumeControl";
    private static final String PROPERTY_volumeStep = "volumeStep";
    private int mAutoAdvanceMax;
    private boolean mIs3dSupported;
    private boolean mIs5dot1Supported;
    private boolean mIsHdSupported;
    private boolean mVolumeControl;
    private int mVolumeStep;
    
    public PlayerCapabilities(final JSONObject mJson) {
        final boolean b = true;
        super("PLAYER_CAPABILITIES");
        this.mJson = mJson;
        final String string = JsonUtils.getString(mJson, "is3dSupported", null);
        this.mIs3dSupported = (string != null && "true".equalsIgnoreCase(string));
        final String string2 = JsonUtils.getString(mJson, "isHdSupported", null);
        this.mIsHdSupported = (string2 != null && "true".equalsIgnoreCase(string2));
        final String string3 = JsonUtils.getString(mJson, "is5dot1Supported", null);
        this.mIs5dot1Supported = (string3 != null && "true".equalsIgnoreCase(string3) && b);
        this.mAutoAdvanceMax = JsonUtils.getInt(mJson, "autoAdvanceMax", 0);
        if (mJson.has("volumeControl")) {
            this.mVolumeControl = mJson.getBoolean("volumeControl");
        }
        if (mJson.has("volumeStep")) {
            this.mVolumeStep = mJson.getInt("volumeStep");
        }
    }
    
    public int getAutoAdvanceMax() {
        return this.mAutoAdvanceMax;
    }
    
    public int getVolumeStep() {
        return this.mVolumeStep;
    }
    
    public boolean is3dSupported() {
        return this.mIs3dSupported;
    }
    
    public boolean is5dot1Supported() {
        return this.mIs5dot1Supported;
    }
    
    public boolean isHdSupported() {
        return this.mIsHdSupported;
    }
    
    public boolean isVolumeControl() {
        return this.mVolumeControl;
    }
    
    @Override
    public String toString() {
        return "PlayerCapabilities [isHdSupported=" + this.mIsHdSupported + ", is5dot1Supported=" + this.mIs5dot1Supported + ", is3dSupported=" + this.mIs3dSupported + ", autoAdvanceMax=" + this.mAutoAdvanceMax;
    }
}
