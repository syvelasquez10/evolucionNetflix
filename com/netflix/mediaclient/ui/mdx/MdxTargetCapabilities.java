// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.ui.details.DeviceCapabilityProvider;

public class MdxTargetCapabilities implements DeviceCapabilityProvider
{
    protected static String PROPERTY_autoAdvanceMax;
    protected static String PROPERTY_is3dSupported;
    protected static String PROPERTY_is5dot1Supported;
    protected static String PROPERTY_isHdSupported;
    protected static String PROPERTY_isUltraHdSupported;
    protected static String PROPERTY_volumeControl;
    protected static String PROPERTY_volumeStep;
    private int autoAdvanceMax;
    private boolean is3dSupported;
    private boolean is5dot1Supported;
    private boolean isHdSupported;
    private boolean isUltraHdSupported;
    private boolean volumeControl;
    private int volumeStep;
    
    static {
        MdxTargetCapabilities.PROPERTY_isHdSupported = "isHdSupported";
        MdxTargetCapabilities.PROPERTY_is5dot1Supported = "is5dot1Supported";
        MdxTargetCapabilities.PROPERTY_is3dSupported = "is3dSupported";
        MdxTargetCapabilities.PROPERTY_autoAdvanceMax = "autoAdvanceMax";
        MdxTargetCapabilities.PROPERTY_volumeControl = "volumeControl";
        MdxTargetCapabilities.PROPERTY_volumeStep = "volumeStep";
        MdxTargetCapabilities.PROPERTY_isUltraHdSupported = "isUltraHdSupported";
    }
    
    public MdxTargetCapabilities(final String s) {
        this(new JSONObject(s));
    }
    
    public MdxTargetCapabilities(final JSONObject jsonObject) {
        this.is3dSupported = JsonUtils.getBoolean(jsonObject, MdxTargetCapabilities.PROPERTY_is3dSupported, false);
        this.isHdSupported = JsonUtils.getBoolean(jsonObject, MdxTargetCapabilities.PROPERTY_isHdSupported, false);
        this.is5dot1Supported = JsonUtils.getBoolean(jsonObject, MdxTargetCapabilities.PROPERTY_is5dot1Supported, false);
        this.isUltraHdSupported = JsonUtils.getBoolean(jsonObject, MdxTargetCapabilities.PROPERTY_isUltraHdSupported, false);
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
    
    @Override
    public boolean is3dSupported() {
        return this.is3dSupported;
    }
    
    @Override
    public boolean is5dot1Supported() {
        return this.is5dot1Supported;
    }
    
    @Override
    public boolean isHdSupported() {
        return this.isHdSupported;
    }
    
    @Override
    public boolean isUltraHdSupported() {
        return this.isUltraHdSupported;
    }
    
    public boolean isVolumeControl() {
        return this.volumeControl;
    }
    
    @Override
    public String toString() {
        return "MdxTargetCapabilities [isHdSupported=" + this.isHdSupported + ", is5dot1Supported=" + this.is5dot1Supported + ", is3dSupported=" + this.is3dSupported + ", autoAdvanceMax=" + this.autoAdvanceMax + ", volumeControl=" + this.volumeControl + ", volumeStep=" + this.volumeStep + ", isUltraHdSupported=" + this.isUltraHdSupported + "]";
    }
}
