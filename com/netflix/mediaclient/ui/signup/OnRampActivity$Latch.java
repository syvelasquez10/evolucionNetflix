// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

public class OnRampActivity$Latch
{
    private boolean onRampSelectionMade;
    
    public boolean getValueAndLatch() {
        final boolean onRampSelectionMade = this.onRampSelectionMade;
        this.onRampSelectionMade = false;
        return onRampSelectionMade;
    }
}
