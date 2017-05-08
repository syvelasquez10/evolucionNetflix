// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.Button;

public class NetflixTextButton extends Button
{
    private final NetflixTextButton$Attributes mAttributes;
    
    public NetflixTextButton(final Context context) {
        this(context, null);
    }
    
    public NetflixTextButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NetflixTextButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mAttributes = new NetflixTextButton$Attributes(this.getContext(), set);
        if (!this.mAttributes.simulatePreL && Build$VERSION.SDK_INT >= 21) {
            this.setBackground((Drawable)new NetflixTextButton$NetflixButtonDrawableL(this.mAttributes));
        }
        else {
            this.setBackground((Drawable)new NetflixTextButton$NetflixButtonDrawablePreL(this.mAttributes));
        }
        this.setTextColor(this.mAttributes.textColor);
    }
    
    private static ColorStateList getColorState(final TypedArray typedArray, final int n) {
        ColorStateList list;
        if ((list = typedArray.getColorStateList(n)) == null) {
            list = ColorStateList.valueOf(0);
        }
        return list;
    }
}
