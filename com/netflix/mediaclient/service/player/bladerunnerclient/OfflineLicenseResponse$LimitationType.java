// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum OfflineLicenseResponse$LimitationType
{
    Download("download"), 
    License("license"), 
    UNKNOWN(""), 
    Unlimited("unlimited");
    
    private String mValue;
    
    private OfflineLicenseResponse$LimitationType(final String mValue) {
        this.mValue = mValue;
    }
    
    public static OfflineLicenseResponse$LimitationType create(final String s) {
        final OfflineLicenseResponse$LimitationType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final OfflineLicenseResponse$LimitationType offlineLicenseResponse$LimitationType = values[i];
            if (offlineLicenseResponse$LimitationType.mValue.equalsIgnoreCase(s)) {
                return offlineLicenseResponse$LimitationType;
            }
        }
        return OfflineLicenseResponse$LimitationType.UNKNOWN;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
