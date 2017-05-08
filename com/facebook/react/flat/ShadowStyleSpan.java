// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

final class ShadowStyleSpan extends CharacterStyle
{
    static final ShadowStyleSpan INSTANCE;
    private int mColor;
    private float mDx;
    private float mDy;
    private boolean mFrozen;
    private float mRadius;
    
    static {
        INSTANCE = new ShadowStyleSpan(0.0f, 0.0f, 0.0f, 0, true);
    }
    
    private ShadowStyleSpan(final float mDx, final float mDy, final float mRadius, final int mColor, final boolean mFrozen) {
        this.mDx = mDx;
        this.mDy = mDy;
        this.mRadius = mRadius;
        this.mColor = mColor;
        this.mFrozen = mFrozen;
    }
    
    void freeze() {
        this.mFrozen = true;
    }
    
    public int getColor() {
        return this.mColor;
    }
    
    public float getRadius() {
        return this.mRadius;
    }
    
    boolean isFrozen() {
        return this.mFrozen;
    }
    
    ShadowStyleSpan mutableCopy() {
        return new ShadowStyleSpan(this.mDx, this.mDy, this.mRadius, this.mColor, false);
    }
    
    public boolean offsetMatches(final float n, final float n2) {
        return this.mDx == n && this.mDy == n2;
    }
    
    public void setColor(final int mColor) {
        this.mColor = mColor;
    }
    
    public void setOffset(final float mDx, final float mDy) {
        this.mDx = mDx;
        this.mDy = mDy;
    }
    
    public void setRadius(final float mRadius) {
        this.mRadius = mRadius;
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        textPaint.setShadowLayer(this.mRadius, this.mDx, this.mDy, this.mColor);
    }
}
