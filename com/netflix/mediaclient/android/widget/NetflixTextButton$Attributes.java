// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.widget.Button;
import android.content.res.TypedArray;
import com.netflix.mediaclient.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.content.res.ColorStateList;

class NetflixTextButton$Attributes
{
    final int animationDuration;
    final ColorStateList buttonColor;
    final int cornerRadius;
    final int rippleColor;
    final boolean simulatePreL;
    final ColorStateList strokeColor;
    final int strokeWidth;
    final ColorStateList textColor;
    
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
        obtainStyledAttributes.recycle();
    }
}
