// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

public class WhistleEngineThresholds$Threshold
{
    private int red;
    final /* synthetic */ WhistleEngineThresholds this$0;
    private int yellow;
    
    public WhistleEngineThresholds$Threshold(final WhistleEngineThresholds this$0) {
        this.this$0 = this$0;
        this.setYellow(0);
        this.setRed(0);
    }
    
    private void setRed(final int red) {
        this.red = red;
    }
    
    private void setYellow(final int yellow) {
        this.yellow = yellow;
    }
    
    public int getRed() {
        return this.red;
    }
    
    public int getYellow() {
        return this.yellow;
    }
}
