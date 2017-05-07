// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.verifyplay;

public enum PlayVerifierVault$PlayInvokedFrom
{
    MDX("mdx"), 
    PLAYER("player"), 
    PLAY_LAUNCHER("launcher"), 
    UNKNOWN("");
    
    private String value;
    
    private PlayVerifierVault$PlayInvokedFrom(final String value) {
        this.value = value;
    }
    
    public static PlayVerifierVault$PlayInvokedFrom create(final String s) {
        final PlayVerifierVault$PlayInvokedFrom[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PlayVerifierVault$PlayInvokedFrom playVerifierVault$PlayInvokedFrom = values[i];
            if (playVerifierVault$PlayInvokedFrom.value.equalsIgnoreCase(s)) {
                return playVerifierVault$PlayInvokedFrom;
            }
        }
        return PlayVerifierVault$PlayInvokedFrom.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
