// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.google.gson.annotations.SerializedName;

public enum BandwidthLogging$SettingType
{
    DATA_SAVING("enableDataSaving"), 
    HD_PLAYBACK("enableHDPlayback"), 
    UNKNOWN("");
    
    @SerializedName("value")
    private String value;
    
    private BandwidthLogging$SettingType(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
