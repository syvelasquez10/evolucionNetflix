// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public enum IMedia$SubtitleOutputMode
{
    DATA_JSON(2), 
    DATA_XML(1), 
    EVENTS(0);
    
    protected int mValue;
    
    private IMedia$SubtitleOutputMode(final int mValue) {
        this.mValue = mValue;
    }
    
    public final int getValue() {
        return this.mValue;
    }
}