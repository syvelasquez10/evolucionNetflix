// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder;

import android.graphics.Typeface;
import android.graphics.Paint;
import android.text.TextPaint;

class TextLayoutBuilder$ComparableTextPaint extends TextPaint
{
    private int mShadowColor;
    private float mShadowDx;
    private float mShadowDy;
    private float mShadowRadius;
    
    public TextLayoutBuilder$ComparableTextPaint() {
    }
    
    public TextLayoutBuilder$ComparableTextPaint(final int n) {
        super(n);
    }
    
    public TextLayoutBuilder$ComparableTextPaint(final Paint paint) {
        super(paint);
    }
    
    public int hashCode() {
        int n = 0;
        final Typeface typeface = this.getTypeface();
        final int color = this.getColor();
        final int floatToIntBits = Float.floatToIntBits(this.getTextSize());
        int hashCode;
        if (typeface != null) {
            hashCode = typeface.hashCode();
        }
        else {
            hashCode = 0;
        }
        int n3;
        final int n2 = n3 = (((((hashCode + ((color + 31) * 31 + floatToIntBits) * 31) * 31 + Float.floatToIntBits(this.mShadowDx)) * 31 + Float.floatToIntBits(this.mShadowDy)) * 31 + Float.floatToIntBits(this.mShadowRadius)) * 31 + this.mShadowColor) * 31 + this.linkColor;
        int n4;
        if (this.drawableState == null) {
            n4 = n2 * 31 + 0;
        }
        else {
            while (true) {
                n4 = n3;
                if (n >= this.drawableState.length) {
                    break;
                }
                n3 = n3 * 31 + this.drawableState[n];
                ++n;
            }
        }
        return n4;
    }
    
    public void setShadowLayer(final float mShadowRadius, final float mShadowDx, final float mShadowDy, final int mShadowColor) {
        super.setShadowLayer(this.mShadowRadius = mShadowRadius, this.mShadowDx = mShadowDx, this.mShadowDy = mShadowDy, this.mShadowColor = mShadowColor);
    }
}
