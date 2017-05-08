// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable$Callback;
import android.graphics.drawable.Drawable;

public class DrawableUtils
{
    public static void copyProperties(final Drawable drawable, final Drawable drawable2) {
        if (drawable2 == null || drawable == null || drawable == drawable2) {
            return;
        }
        drawable.setBounds(drawable2.getBounds());
        drawable.setChangingConfigurations(drawable2.getChangingConfigurations());
        drawable.setLevel(drawable2.getLevel());
        drawable.setVisible(drawable2.isVisible(), false);
        drawable.setState(drawable2.getState());
    }
    
    public static int getOpacityFromColor(int n) {
        n >>>= 24;
        if (n == 255) {
            return -1;
        }
        if (n == 0) {
            return -2;
        }
        return -3;
    }
    
    public static int multiplyColorAlpha(final int n, final int n2) {
        if (n2 == 255) {
            return n;
        }
        if (n2 == 0) {
            return n & 0xFFFFFF;
        }
        return ((n2 >> 7) + n2) * (n >>> 24) >> 8 << 24 | (n & 0xFFFFFF);
    }
    
    public static void setCallbacks(final Drawable drawable, final Drawable$Callback callback, final TransformCallback transformCallback) {
        if (drawable != null) {
            drawable.setCallback(callback);
            if (drawable instanceof TransformAwareDrawable) {
                ((TransformAwareDrawable)drawable).setTransformCallback(transformCallback);
            }
        }
    }
    
    public static void setDrawableProperties(final Drawable drawable, final DrawableProperties drawableProperties) {
        if (drawable == null || drawableProperties == null) {
            return;
        }
        drawableProperties.applyTo(drawable);
    }
}
