// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

public enum TitlePageIndicator$LinePosition
{
    Bottom(0), 
    Top(1);
    
    public final int value;
    
    private TitlePageIndicator$LinePosition(final int value) {
        this.value = value;
    }
    
    public static TitlePageIndicator$LinePosition fromValue(final int n) {
        final TitlePageIndicator$LinePosition[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final TitlePageIndicator$LinePosition titlePageIndicator$LinePosition = values[i];
            if (titlePageIndicator$LinePosition.value == n) {
                return titlePageIndicator$LinePosition;
            }
        }
        return null;
    }
}
