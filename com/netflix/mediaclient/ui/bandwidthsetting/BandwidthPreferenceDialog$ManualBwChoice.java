// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

public enum BandwidthPreferenceDialog$ManualBwChoice
{
    HIGH(3), 
    LOW(1), 
    MEDIUM(2), 
    OFF(0), 
    UNDEFINED(-1), 
    UNLIMITED(4);
    
    private final int id;
    
    private BandwidthPreferenceDialog$ManualBwChoice(final int id) {
        this.id = id;
    }
    
    public static BandwidthPreferenceDialog$ManualBwChoice create(final int n) {
        final BandwidthPreferenceDialog$ManualBwChoice[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final BandwidthPreferenceDialog$ManualBwChoice bandwidthPreferenceDialog$ManualBwChoice = values[i];
            if (bandwidthPreferenceDialog$ManualBwChoice.id == n) {
                return bandwidthPreferenceDialog$ManualBwChoice;
            }
        }
        return BandwidthPreferenceDialog$ManualBwChoice.UNDEFINED;
    }
    
    public int getValue() {
        return this.id;
    }
}
