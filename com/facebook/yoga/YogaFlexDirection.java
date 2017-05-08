// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaFlexDirection
{
    COLUMN(0), 
    COLUMN_REVERSE(1), 
    ROW(2), 
    ROW_REVERSE(3);
    
    private int mIntValue;
    
    private YogaFlexDirection(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
