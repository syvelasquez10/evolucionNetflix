// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

public enum IBladeRunnerClient$OfflineRefreshInvoke
{
    MAINTENANCE(1), 
    UNKNOWN(2), 
    USER(0);
    
    private int value;
    
    private IBladeRunnerClient$OfflineRefreshInvoke(final int value) {
        this.value = value;
    }
    
    public static IBladeRunnerClient$OfflineRefreshInvoke create(final int n) {
        final IBladeRunnerClient$OfflineRefreshInvoke[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final IBladeRunnerClient$OfflineRefreshInvoke bladeRunnerClient$OfflineRefreshInvoke = values[i];
            if (bladeRunnerClient$OfflineRefreshInvoke.getValue() == n) {
                return bladeRunnerClient$OfflineRefreshInvoke;
            }
        }
        return IBladeRunnerClient$OfflineRefreshInvoke.UNKNOWN;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public boolean isUserIntiiated() {
        return IBladeRunnerClient$OfflineRefreshInvoke.MAINTENANCE.getValue() == this.value;
    }
}
