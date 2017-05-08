// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum IPlayer$PlaybackType
{
    OfflinePlayback("OfflinePlayback"), 
    StreamingPlayback("StreamingPlayback"), 
    Unknown("Unknown");
    
    private String value;
    
    private IPlayer$PlaybackType(final String value) {
        this.value = value;
    }
    
    public static IPlayer$PlaybackType fromValue(final String s) {
        final IPlayer$PlaybackType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IPlayer$PlaybackType player$PlaybackType = values[i];
            if (player$PlaybackType.value.equalsIgnoreCase(s)) {
                return player$PlaybackType;
            }
        }
        return IPlayer$PlaybackType.Unknown;
    }
    
    public String getValue() {
        return this.value;
    }
}
