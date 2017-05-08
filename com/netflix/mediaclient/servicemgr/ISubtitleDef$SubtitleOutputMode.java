// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public enum ISubtitleDef$SubtitleOutputMode
{
    BINARY_IMAGE(3), 
    DATA_JSON(2), 
    DATA_XML(1);
    
    protected int mValue;
    
    private ISubtitleDef$SubtitleOutputMode(final int mValue) {
        this.mValue = mValue;
    }
    
    public final int getValue() {
        return this.mValue;
    }
}
