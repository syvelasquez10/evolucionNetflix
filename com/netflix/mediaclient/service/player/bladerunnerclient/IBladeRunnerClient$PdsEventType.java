// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum IBladeRunnerClient$PdsEventType
{
    KEEP_ALIVE("keepAlive"), 
    PAUSE("pause"), 
    RESUME("resume"), 
    START("start"), 
    STOP("stop"), 
    UNKNOWN("");
    
    private String value;
    
    private IBladeRunnerClient$PdsEventType(final String value) {
        this.value = value;
    }
    
    public static IBladeRunnerClient$PdsEventType create(final String s) {
        final IBladeRunnerClient$PdsEventType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IBladeRunnerClient$PdsEventType bladeRunnerClient$PdsEventType = values[i];
            if (bladeRunnerClient$PdsEventType.value.equalsIgnoreCase(s)) {
                return bladeRunnerClient$PdsEventType;
            }
        }
        return IBladeRunnerClient$PdsEventType.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
