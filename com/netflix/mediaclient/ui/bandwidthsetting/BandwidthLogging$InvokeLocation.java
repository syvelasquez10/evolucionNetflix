// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.google.gson.annotations.SerializedName;

public enum BandwidthLogging$InvokeLocation
{
    FROM_PLAYER("player"), 
    FROM_SETTINGS("settings"), 
    UNKNOWN("");
    
    @SerializedName("value")
    private String value;
    
    private BandwidthLogging$InvokeLocation(final String value) {
        this.value = value;
    }
    
    public static BandwidthLogging$InvokeLocation create(final String s) {
        final BandwidthLogging$InvokeLocation[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final BandwidthLogging$InvokeLocation bandwidthLogging$InvokeLocation = values[i];
            if (bandwidthLogging$InvokeLocation.value.equalsIgnoreCase(s)) {
                return bandwidthLogging$InvokeLocation;
            }
        }
        return BandwidthLogging$InvokeLocation.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
