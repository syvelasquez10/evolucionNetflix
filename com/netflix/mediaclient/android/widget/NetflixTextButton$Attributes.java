// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.utils.UiUtils;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.support.v7.widget.AppCompatButton;
import com.netflix.android.widgetry.R$styleable;
import android.util.AttributeSet;
import android.content.res.TypedArray;
import com.netflix.android.widgetry.R$attr;
import android.view.ContextThemeWrapper;
import android.content.Context;
import android.content.res.ColorStateList;

class NetflixTextButton$Attributes
{
    final int animationDuration;
    final ColorStateList buttonColor;
    final int cornerRadius;
    final ColorStateList iconColor;
    final int iconSize;
    final int rippleColor;
    final ColorStateList strokeColor;
    final int strokeWidth;
    final ColorStateList textColor;
    
    NetflixTextButton$Attributes(Context obtainStyledAttributes, final int n) {
        this.animationDuration = obtainStyledAttributes.getResources().getInteger(17694720);
        obtainStyledAttributes = (Context)new ContextThemeWrapper(obtainStyledAttributes, n).obtainStyledAttributes(new int[] { R$attr.nb_strokeColor, R$attr.nb_buttonColor, R$attr.nb_textColor, R$attr.nb_rippleColor, R$attr.nb_cornerRadius, R$attr.nb_strokeWidth, R$attr.nb_iconSize, R$attr.nb_iconColor });
        try {
            this.strokeColor = getColorState((TypedArray)obtainStyledAttributes, 0);
            this.buttonColor = getColorState((TypedArray)obtainStyledAttributes, 1);
            this.textColor = getColorState((TypedArray)obtainStyledAttributes, 2);
            this.rippleColor = ((TypedArray)obtainStyledAttributes).getColor(3, this.buttonColor.getColorForState(new int[] { 16842919 }, 0));
            this.cornerRadius = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(4, 0);
            this.strokeWidth = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(5, 0);
            this.iconSize = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(6, 0);
            this.iconColor = getColorState((TypedArray)obtainStyledAttributes, 7);
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
        this.strokeColor = getColorState(obtainStyledAttributes, R$styleable.NetflixButton_nb_strokeColor);
        this.buttonColor = getColorState(obtainStyledAttributes, R$styleable.NetflixButton_nb_buttonColor);
        this.textColor = getColorState(obtainStyledAttributes, R$styleable.NetflixButton_nb_textColor);
        this.iconColor = getColorState(obtainStyledAttributes, R$styleable.NetflixButton_nb_iconColor, this.textColor);
        this.rippleColor = obtainStyledAttributes.getColor(R$styleable.NetflixButton_nb_rippleColor, this.buttonColor.getColorForState(new int[] { 16842919 }, 0));
        this.cornerRadius = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NetflixButton_nb_cornerRadius, 0);
        this.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NetflixButton_nb_strokeWidth, 0);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(R$styleable.NetflixButton_nb_iconSize, 0);
        obtainStyledAttributes.recycle();
    }
}
