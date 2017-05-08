// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum IBladeRunnerClient$ManifestType
{
    OFFLINE("offline"), 
    STANDARD("standard"), 
    UNKNOWN("");
    
    private String value;
    
    private IBladeRunnerClient$ManifestType(final String value) {
        this.value = value;
    }
    
    public static IBladeRunnerClient$ManifestType create(final String s) {
        final IBladeRunnerClient$ManifestType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IBladeRunnerClient$ManifestType bladeRunnerClient$ManifestType = values[i];
            if (bladeRunnerClient$ManifestType.value.equalsIgnoreCase(s)) {
                return bladeRunnerClient$ManifestType;
            }
        }
        return IBladeRunnerClient$ManifestType.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
