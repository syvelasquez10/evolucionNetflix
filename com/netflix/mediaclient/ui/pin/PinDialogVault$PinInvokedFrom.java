// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.pin;

public enum PinDialogVault$PinInvokedFrom
{
    MDX("mdx"), 
    PLAYER("player"), 
    PLAY_LAUNCHER("launcher"), 
    UNKNOWN("");
    
    private String value;
    
    private PinDialogVault$PinInvokedFrom(final String value) {
        this.value = value;
    }
    
    public static PinDialogVault$PinInvokedFrom create(final String s) {
        final PinDialogVault$PinInvokedFrom[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PinDialogVault$PinInvokedFrom pinDialogVault$PinInvokedFrom = values[i];
            if (pinDialogVault$PinInvokedFrom.value.equalsIgnoreCase(s)) {
                return pinDialogVault$PinInvokedFrom;
            }
        }
        return PinDialogVault$PinInvokedFrom.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
