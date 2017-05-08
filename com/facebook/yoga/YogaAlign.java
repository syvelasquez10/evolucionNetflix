// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaAlign
{
    AUTO(0), 
    CENTER(2), 
    FLEX_END(3), 
    FLEX_START(1), 
    STRETCH(4);
    
    private int mIntValue;
    
    private YogaAlign(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
