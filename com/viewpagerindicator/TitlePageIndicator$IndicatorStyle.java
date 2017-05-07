// 
// Decompiled by Procyon v0.5.30
// 

package com.viewpagerindicator;

public enum TitlePageIndicator$IndicatorStyle
{
    None(0), 
    Triangle(1), 
    Underline(2);
    
    public final int value;
    
    private TitlePageIndicator$IndicatorStyle(final int value) {
        this.value = value;
    }
    
    public static TitlePageIndicator$IndicatorStyle fromValue(final int n) {
        final TitlePageIndicator$IndicatorStyle[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final TitlePageIndicator$IndicatorStyle titlePageIndicator$IndicatorStyle = values[i];
            if (titlePageIndicator$IndicatorStyle.value == n) {
                return titlePageIndicator$IndicatorStyle;
            }
        }
        return null;
    }
}
