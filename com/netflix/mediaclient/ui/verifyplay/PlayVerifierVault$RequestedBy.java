// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

public enum PlayVerifierVault$RequestedBy
{
    MDX("mdx"), 
    OFFLINE_DOWNLOAD("offline_download"), 
    PLAYER("player"), 
    PLAY_LAUNCHER("launcher"), 
    UNKNOWN("");
    
    private String value;
    
    private PlayVerifierVault$RequestedBy(final String value) {
        this.value = value;
    }
    
    public static PlayVerifierVault$RequestedBy create(final String s) {
        final PlayVerifierVault$RequestedBy[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PlayVerifierVault$RequestedBy playVerifierVault$RequestedBy = values[i];
            if (playVerifierVault$RequestedBy.value.equalsIgnoreCase(s)) {
                return playVerifierVault$RequestedBy;
            }
        }
        return PlayVerifierVault$RequestedBy.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
