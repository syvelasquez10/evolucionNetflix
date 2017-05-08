// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.ColorFilter;

public class DrawableProperties
{
    private int mAlpha;
    private ColorFilter mColorFilter;
    private int mDither;
    private int mFilterBitmap;
    private boolean mIsSetColorFilter;
    
    public DrawableProperties() {
        this.mAlpha = -1;
        this.mIsSetColorFilter = false;
        this.mColorFilter = null;
        this.mDither = -1;
        this.mFilterBitmap = -1;
    }
    
    public void applyTo(final Drawable drawable) {
        final boolean b = true;
        if (drawable != null) {
            if (this.mAlpha != -1) {
                drawable.setAlpha(this.mAlpha);
            }
            if (this.mIsSetColorFilter) {
                drawable.setColorFilter(this.mColorFilter);
            }
            if (this.mDither != -1) {
                drawable.setDither(this.mDither != 0);
            }
            if (this.mFilterBitmap != -1) {
                drawable.setFilterBitmap(this.mFilterBitmap != 0 && b);
            }
        }
    }
    
    public void setAlpha(final int mAlpha) {
        this.mAlpha = mAlpha;
    }
    
    public void setColorFilter(final ColorFilter mColorFilter) {
        this.mColorFilter = mColorFilter;
        this.mIsSetColorFilter = true;
    }
    
    public void setDither(final boolean b) {
        int mDither;
        if (b) {
            mDither = 1;
        }
        else {
            mDither = 0;
        }
        this.mDither = mDither;
    }
    
    public void setFilterBitmap(final boolean b) {
        int mFilterBitmap;
        if (b) {
            mFilterBitmap = 1;
        }
        else {
            mFilterBitmap = 0;
        }
        this.mFilterBitmap = mFilterBitmap;
    }
}
