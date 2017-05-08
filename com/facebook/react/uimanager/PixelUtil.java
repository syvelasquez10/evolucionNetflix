// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.util.TypedValue;

public class PixelUtil
{
    public static float toDIPFromPixel(final float n) {
        return n / DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }
    
    public static float toPixelFromDIP(final double n) {
        return toPixelFromDIP((float)n);
    }
    
    public static float toPixelFromDIP(final float n) {
        return TypedValue.applyDimension(1, n, DisplayMetricsHolder.getWindowDisplayMetrics());
    }
    
    public static float toPixelFromSP(final float n) {
        return TypedValue.applyDimension(2, n, DisplayMetricsHolder.getWindowDisplayMetrics());
    }
}
