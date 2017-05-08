// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ContextThemeWrapper;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.AppCompatButton;

public class NetflixTextButton extends AppCompatButton
{
    private NetflixTextButton$Attributes mAttributes;
    
    public NetflixTextButton(final Context context) {
        this(context, null);
    }
    
    public NetflixTextButton(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public NetflixTextButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setAttributes(new NetflixTextButton$Attributes(this.getContext(), set));
    }
    
    private static ColorStateList getColorState(final TypedArray typedArray, final int n) {
        ColorStateList list;
        if ((list = typedArray.getColorStateList(n)) == null) {
            list = ColorStateList.valueOf(0);
        }
        return list;
    }
    
    private void setAttributes(final NetflixTextButton$Attributes mAttributes) {
        this.mAttributes = mAttributes;
        if (!this.mAttributes.simulatePreL && Build$VERSION.SDK_INT >= 21) {
            this.setBackground((Drawable)new NetflixTextButton$NetflixButtonDrawableL(this.mAttributes));
        }
        else {
            this.setBackground((Drawable)new NetflixTextButton$NetflixButtonDrawablePreL(this.mAttributes));
        }
        this.setTextColor(this.mAttributes.textColor);
    }
    
    public void applyFrom(final int n) {
        this.setAttributes(new NetflixTextButton$Attributes((Context)new ContextThemeWrapper(this.getContext(), n), n));
    }
    
    protected void onFinishInflate() {
        Drawable drawable;
        if (this.getCompoundDrawablesRelative()[0] != null) {
            drawable = this.getCompoundDrawablesRelative()[0];
        }
        else {
            drawable = this.getCompoundDrawables()[0];
        }
        Drawable drawable2;
        if (this.getCompoundDrawablesRelative()[2] != null) {
            drawable2 = this.getCompoundDrawablesRelative()[2];
        }
        else {
            drawable2 = this.getCompoundDrawables()[2];
        }
        this.setCompoundDrawablesRelative(drawable, this.getCompoundDrawablesRelative()[1], drawable2, this.getCompoundDrawablesRelative()[3]);
        super.onFinishInflate();
    }
    
    public void setCompoundDrawables(final Drawable drawable, final Drawable drawable2, final Drawable drawable3, final Drawable drawable4) {
        if (this.mAttributes == null) {
            super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
            return;
        }
        super.setCompoundDrawables(ViewUtils.tintAndGet(drawable, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable2, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable3, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable4, this.mAttributes.textColor, this.mAttributes.iconSize));
    }
    
    public void setCompoundDrawablesRelative(final Drawable drawable, final Drawable drawable2, final Drawable drawable3, final Drawable drawable4) {
        if (this.mAttributes == null) {
            super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
            return;
        }
        super.setCompoundDrawablesRelative(ViewUtils.tintAndGet(drawable, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable2, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable3, this.mAttributes.textColor, this.mAttributes.iconSize), ViewUtils.tintAndGet(drawable4, this.mAttributes.textColor, this.mAttributes.iconSize));
    }
    
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(final int n, final int n2, final int n3, final int n4) {
        Drawable drawable = null;
        Drawable drawable2;
        if (n != 0) {
            drawable2 = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable2 = null;
        }
        Drawable drawable3;
        if (n2 != 0) {
            drawable3 = ContextCompat.getDrawable(this.getContext(), n2);
        }
        else {
            drawable3 = null;
        }
        Drawable drawable4;
        if (n3 != 0) {
            drawable4 = ContextCompat.getDrawable(this.getContext(), n3);
        }
        else {
            drawable4 = null;
        }
        if (n4 != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n4);
        }
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable);
    }
    
    public void setCompoundDrawablesWithIntrinsicBounds(final int n, final int n2, final int n3, final int n4) {
        Drawable drawable = null;
        Drawable drawable2;
        if (n != 0) {
            drawable2 = ContextCompat.getDrawable(this.getContext(), n);
        }
        else {
            drawable2 = null;
        }
        Drawable drawable3;
        if (n2 != 0) {
            drawable3 = ContextCompat.getDrawable(this.getContext(), n2);
        }
        else {
            drawable3 = null;
        }
        Drawable drawable4;
        if (n3 != 0) {
            drawable4 = ContextCompat.getDrawable(this.getContext(), n3);
        }
        else {
            drawable4 = null;
        }
        if (n4 != 0) {
            drawable = ContextCompat.getDrawable(this.getContext(), n4);
        }
        this.setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable3, drawable4, drawable);
    }
}
