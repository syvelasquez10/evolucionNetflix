// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum IBladeRunnerClient$LicenseRequestFlavor
{
    LIMITED("limited"), 
    OFFLINE("offline"), 
    STANDARD("standard"), 
    UNKNOWN("");
    
    private String value;
    
    private IBladeRunnerClient$LicenseRequestFlavor(final String value) {
        this.value = value;
    }
    
    public static IBladeRunnerClient$LicenseRequestFlavor create(final String s) {
        final IBladeRunnerClient$LicenseRequestFlavor[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IBladeRunnerClient$LicenseRequestFlavor bladeRunnerClient$LicenseRequestFlavor = values[i];
            if (bladeRunnerClient$LicenseRequestFlavor.value.equalsIgnoreCase(s)) {
                return bladeRunnerClient$LicenseRequestFlavor;
            }
        }
        return IBladeRunnerClient$LicenseRequestFlavor.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
