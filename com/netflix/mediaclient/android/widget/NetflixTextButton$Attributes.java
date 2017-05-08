// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.util.ViewUtils;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.widget.AppCompatButton;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import android.view.ContextThemeWrapper;
import android.content.Context;
import android.content.res.ColorStateList;

class NetflixTextButton$Attributes
{
    final int animationDuration;
    final ColorStateList buttonColor;
    final int cornerRadius;
    final int iconSize;
    final int rippleColor;
    final boolean simulatePreL;
    final ColorStateList strokeColor;
    final int strokeWidth;
    final ColorStateList textColor;
    
    NetflixTextButton$Attributes(Context obtainStyledAttributes, final int n) {
        this.animationDuration = obtainStyledAttributes.getResources().getInteger(17694720);
        obtainStyledAttributes = (Context)new ContextThemeWrapper(obtainStyledAttributes, n).obtainStyledAttributes(new int[] { 2130772275, 2130772277, 2130772278, 2130772279, 2130772280, 2130772276, 2130772281, 2130772282 });
        try {
            this.strokeColor = getColorState((TypedArray)obtainStyledAttributes, 0);
            this.buttonColor = getColorState((TypedArray)obtainStyledAttributes, 1);
            this.textColor = getColorState((TypedArray)obtainStyledAttributes, 2);
            this.rippleColor = ((TypedArray)obtainStyledAttributes).getColor(3, this.buttonColor.getColorForState(new int[] { 16842919 }, 0));
            this.cornerRadius = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(4, 0);
            this.strokeWidth = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(5, 0);
            this.simulatePreL = ((TypedArray)obtainStyledAttributes).getBoolean(6, false);
            this.iconSize = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(7, 0);
        }
        finally {
            if (obtainStyledAttributes != null) {
                ((TypedArray)obtainStyledAttributes).recycle();
            }
        }
    }
    
    NetflixTextButton$Attributes(final Context context, final AttributeSet set) {
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(set, R$styleable.NetflixButton);
        this.animationDuration = context.getResources().getInteger(17694720);
        this.strokeColor = getColorState(obtainStyledAttributes, 0);
        this.buttonColor = getColorState(obtainStyledAttributes, 2);
        this.textColor = getColorState(obtainStyledAttributes, 3);
        this.rippleColor = obtainStyledAttributes.getColor(4, this.buttonColor.getColorForState(new int[] { 16842919 }, 0));
        this.cornerRadius = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.simulatePreL = obtainStyledAttributes.getBoolean(6, false);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(7, 0);
        obtainStyledAttributes.recycle();
    }
}
