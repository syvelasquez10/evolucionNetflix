// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

public class ShadowStyleSpan extends CharacterStyle
{
    private final int mColor;
    private final float mDx;
    private final float mDy;
    private final float mRadius;
    
    public ShadowStyleSpan(final float mDx, final float mDy, final float mRadius, final int mColor) {
        this.mDx = mDx;
        this.mDy = mDy;
        this.mRadius = mRadius;
        this.mColor = mColor;
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        textPaint.setShadowLayer(this.mRadius, this.mDx, this.mDy, this.mColor);
    }
}
