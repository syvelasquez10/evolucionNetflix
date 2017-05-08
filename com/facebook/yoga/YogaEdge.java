// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaEdge
{
    ALL(8), 
    BOTTOM(3), 
    END(5), 
    HORIZONTAL(6), 
    LEFT(0), 
    RIGHT(2), 
    START(4), 
    TOP(1), 
    VERTICAL(7);
    
    private int mIntValue;
    
    private YogaEdge(final int mIntValue) {
        this.mIntValue = mIntValue;
    }
    
    public static YogaEdge fromInt(final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unknown enum value: " + n);
            }
            case 0: {
                return YogaEdge.LEFT;
            }
            case 1: {
                return YogaEdge.TOP;
            }
            case 2: {
                return YogaEdge.RIGHT;
            }
            case 3: {
                return YogaEdge.BOTTOM;
            }
            case 4: {
                return YogaEdge.START;
            }
            case 5: {
                return YogaEdge.END;
            }
            case 6: {
                return YogaEdge.HORIZONTAL;
            }
            case 7: {
                return YogaEdge.VERTICAL;
            }
            case 8: {
                return YogaEdge.ALL;
            }
        }
    }
    
    public int intValue() {
        return this.mIntValue;
    }
}
