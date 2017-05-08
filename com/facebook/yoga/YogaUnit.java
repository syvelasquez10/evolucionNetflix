// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaUnit
{
    PERCENT(2), 
    PIXEL(1), 
    UNDEFINED(0);
    
    private int mIntValue;
    
    private YogaUnit(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public static YogaUnit fromInt(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown enum value: " + n);
            }
            case 0: {
                return YogaUnit.UNDEFINED;
            }
            case 1: {
                return YogaUnit.PIXEL;
            }
            case 2: {
                return YogaUnit.PERCENT;
            }
        }
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
