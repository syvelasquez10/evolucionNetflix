// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.image;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;

public class ImageResizeMode
{
    public static ScalingUtils$ScaleType defaultValue() {
        return ScalingUtils$ScaleType.CENTER_CROP;
    }
    
    public static ScalingUtils$ScaleType toScaleType(final String s) {
        if ("contain".equals(s)) {
            return ScalingUtils$ScaleType.FIT_CENTER;
        }
        if ("cover".equals(s)) {
            return ScalingUtils$ScaleType.CENTER_CROP;
        }
        if ("stretch".equals(s)) {
            return ScalingUtils$ScaleType.FIT_XY;
        }
        if ("center".equals(s)) {
            return ScalingUtils$ScaleType.CENTER_INSIDE;
        }
        if (s == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize mode: '" + s + "'");
    }
}
