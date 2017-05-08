// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaPositionType
{
    ABSOLUTE(1), 
    RELATIVE(0);
    
    private int mIntValue;
    
    private YogaPositionType(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
