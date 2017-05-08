// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum IBladeRunnerClient$ManifestRequestFlavor
{
    PREFETCH("PREFETCH"), 
    STANDARD("STANDARD"), 
    UNKNOWN("");
    
    private String value;
    
    private IBladeRunnerClient$ManifestRequestFlavor(final String value) {
        this.value = value;
    }
    
    public static IBladeRunnerClient$ManifestRequestFlavor create(final String s) {
        final IBladeRunnerClient$ManifestRequestFlavor[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IBladeRunnerClient$ManifestRequestFlavor bladeRunnerClient$ManifestRequestFlavor = values[i];
            if (bladeRunnerClient$ManifestRequestFlavor.value.equalsIgnoreCase(s)) {
                return bladeRunnerClient$ManifestRequestFlavor;
            }
        }
        return IBladeRunnerClient$ManifestRequestFlavor.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
