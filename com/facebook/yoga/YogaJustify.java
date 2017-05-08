// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaJustify
{
    CENTER(1), 
    FLEX_END(2), 
    FLEX_START(0), 
    SPACE_AROUND(4), 
    SPACE_BETWEEN(3);
    
    private int mIntValue;
    
    private YogaJustify(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
