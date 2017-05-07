// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MdxTargetCapabilities
{
    protected static String PROPERTY_autoAdvanceMax;
    protected static String PROPERTY_is3dSupported;
    protected static String PROPERTY_is5dot1Supported;
    protected static String PROPERTY_isHdSupported;
    protected static String PROPERTY_volumeControl;
    protected static String PROPERTY_volumeStep;
    private int autoAdvanceMax;
    private boolean is3dSupported;
    private boolean is5dot1Supported;
    private boolean isHdSupported;
    private boolean volumeControl;
    private int volumeStep;
    
    static {
        MdxTargetCapabilities.PROPERTY_isHdSupported = "isHdSupported";
        MdxTargetCapabilities.PROPERTY_is5dot1Supported = "is5dot1Supported";
        MdxTargetCapabilities.PROPERTY_is3dSupported = "is3dSupported";
        MdxTargetCapabilities.PROPERTY_autoAdvanceMax = "autoAdvanceMax";
        MdxTargetCapabilities.PROPERTY_volumeControl = "volumeControl";
        MdxTargetCapabilities.PROPERTY_volumeStep = "volumeStep";
    }
    
    public MdxTargetCapabilities(final String s) throws JSONException {
        this(new JSONObject(s));
    }
    
    public MdxTargetCapabilities(final JSONObject jsonObject) throws JSONException {
        final boolean b = true;
        final String string = JsonUtils.getString(jsonObject, MdxTargetCapabilities.PROPERTY_is3dSupported, null);
        this.is3dSupported = (string != null && "true".equalsIgnoreCase(string));
        final String string2 = JsonUtils.getString(jsonObject, MdxTargetCapabilities.PROPERTY_isHdSupported, null);
        this.isHdSupported = (string2 != null && "true".equalsIgnoreCase(string2));
        final String string3 = JsonUtils.getString(jsonObject, MdxTargetCapabilities.PROPERTY_is5dot1Supported, null);
        this.is5dot1Supported = (string3 != null && "true".equalsIgnoreCase(string3) && b);
        this.autoAdvanceMax = JsonUtils.getInt(jsonObject, MdxTargetCapabilities.PROPERTY_autoAdvanceMax, 0);
        if (jsonObject.has(MdxTargetCapabilities.PROPERTY_volumeControl)) {
            this.volumeControl = jsonObject.getBoolean(MdxTargetCapabilities.PROPERTY_volumeControl);
        }
        if (jsonObject.has(MdxTargetCapabilities.PROPERTY_volumeStep)) {
            this.volumeStep = jsonObject.getInt(MdxTargetCapabilities.PROPERTY_volumeStep);
        }
    }
    
    public int getAutoAdvanceMax() {
        return this.autoAdvanceMax;
    }
    
    public int getVolumeStep() {
        return this.volumeStep;
    }
    
    public boolean is3dSupported() {
        return this.is3dSupported;
    }
    
    public boolean is5dot1Supported() {
        return this.is5dot1Supported;
    }
    
    public boolean isHdSupported() {
        return this.isHdSupported;
    }
    
    public boolean isVolumeControl() {
        return this.volumeControl;
    }
    
    @Override
    public String toString() {
        return "MdxTargetCapabilities [isHdSupported=" + this.isHdSupported + ", is5dot1Supported=" + this.is5dot1Supported + ", is3dSupported=" + this.is3dSupported + ", autoAdvanceMax=" + this.autoAdvanceMax + ", volumeControl=" + this.volumeControl + ", volumeStep=" + this.volumeStep + "]";
    }
}
