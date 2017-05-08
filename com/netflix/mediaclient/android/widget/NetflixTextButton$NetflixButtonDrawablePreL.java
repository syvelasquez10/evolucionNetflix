// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

class NetflixTextButton$NetflixButtonDrawablePreL extends StateListDrawable
{
    public NetflixTextButton$NetflixButtonDrawablePreL(final NetflixTextButton$Attributes netflixTextButton$Attributes) {
        this.setEnterFadeDuration(netflixTextButton$Attributes.animationDuration);
        this.setExitFadeDuration(netflixTextButton$Attributes.animationDuration);
        this.addState(new int[] { -16842910 }, createRoundRectangleDrawable(netflixTextButton$Attributes.buttonColor.getColorForState(new int[] { -16842910 }, 0), netflixTextButton$Attributes.strokeColor.getColorForState(new int[] { -16842910 }, 0), netflixTextButton$Attributes.strokeWidth, netflixTextButton$Attributes.cornerRadius));
        this.addState(new int[] { 16842919 }, createRoundRectangleDrawable(netflixTextButton$Attributes.buttonColor.getColorForState(new int[] { 16842910, 16842919 }, 0), netflixTextButton$Attributes.strokeColor.getColorForState(new int[] { 16842910, 16842919 }, 0), netflixTextButton$Attributes.strokeWidth, netflixTextButton$Attributes.cornerRadius));
        this.addState(new int[0], createRoundRectangleDrawable(netflixTextButton$Attributes.buttonColor.getColorForState(new int[] { 16842910, -16842919 }, 0), netflixTextButton$Attributes.strokeColor.getColorForState(new int[] { 16842910, -16842919 }, 0), netflixTextButton$Attributes.strokeWidth, netflixTextButton$Attributes.cornerRadius));
    }
    
    private static Drawable createRoundRectangleDrawable(final int color, final int n, final int n2, final int n3) {
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        if (n2 > 0) {
            gradientDrawable.setStroke(n2, n);
        }
        gradientDrawable.setCornerRadius((float)n3);
        return (Drawable)gradientDrawable;
    }
}
