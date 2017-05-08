// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.shapes.Shape;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.Drawable;
import android.content.res.ColorStateList;
import android.annotation.TargetApi;
import android.graphics.drawable.RippleDrawable;

@TargetApi(21)
class NetflixTextButton$NetflixButtonDrawableL extends RippleDrawable
{
    public NetflixTextButton$NetflixButtonDrawableL(final NetflixTextButton$Attributes netflixTextButton$Attributes) {
        super(color(netflixTextButton$Attributes), content(netflixTextButton$Attributes), mask(netflixTextButton$Attributes));
    }
    
    private static ColorStateList color(final NetflixTextButton$Attributes netflixTextButton$Attributes) {
        return ColorStateList.valueOf(netflixTextButton$Attributes.rippleColor);
    }
    
    private static Drawable content(final NetflixTextButton$Attributes netflixTextButton$Attributes) {
        final GradientDrawable gradientDrawable = new GradientDrawable();
        if (netflixTextButton$Attributes.strokeWidth > 0) {
            gradientDrawable.setStroke(netflixTextButton$Attributes.strokeWidth, netflixTextButton$Attributes.strokeColor);
        }
        gradientDrawable.setColor(netflixTextButton$Attributes.buttonColor);
        gradientDrawable.setCornerRadius((float)netflixTextButton$Attributes.cornerRadius);
        return (Drawable)gradientDrawable;
    }
    
    private static Drawable mask(final NetflixTextButton$Attributes netflixTextButton$Attributes) {
        final ShapeDrawable shapeDrawable = new ShapeDrawable();
        final float n = netflixTextButton$Attributes.cornerRadius;
        shapeDrawable.setShape((Shape)new RoundRectShape(new float[] { n, n, n, n, n, n, n, n }, (RectF)null, (float[])null));
        return (Drawable)shapeDrawable;
    }
}
