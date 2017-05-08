// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

final class FontStylingSpan extends MetricAffectingSpan
{
    static final FontStylingSpan INSTANCE;
    private int mBackgroundColor;
    private String mFontFamily;
    private int mFontSize;
    private int mFontStyle;
    private int mFontWeight;
    private boolean mFrozen;
    private boolean mHasStrikeThrough;
    private boolean mHasUnderline;
    private double mTextColor;
    
    static {
        INSTANCE = new FontStylingSpan(-1.6777216E7, 0, -1, -1, -1, false, false, null, true);
    }
    
    FontStylingSpan() {
    }
    
    private FontStylingSpan(final double mTextColor, final int mBackgroundColor, final int mFontSize, final int mFontStyle, final int mFontWeight, final boolean mHasUnderline, final boolean mHasStrikeThrough, final String mFontFamily, final boolean mFrozen) {
        this.mTextColor = mTextColor;
        this.mBackgroundColor = mBackgroundColor;
        this.mFontSize = mFontSize;
        this.mFontStyle = mFontStyle;
        this.mFontWeight = mFontWeight;
        this.mHasUnderline = mHasUnderline;
        this.mHasStrikeThrough = mHasStrikeThrough;
        this.mFontFamily = mFontFamily;
        this.mFrozen = mFrozen;
    }
    
    private int getNewStyle(int n) {
        int n2 = n;
        if (this.mFontStyle != -1) {
            n2 = ((n & 0xFFFFFFFD) | this.mFontStyle);
        }
        n = n2;
        if (this.mFontWeight != -1) {
            n = ((n2 & 0xFFFFFFFE) | this.mFontWeight);
        }
        return n;
    }
    
    private void updateTypeface(final TextPaint textPaint) {
        final Typeface typeface = textPaint.getTypeface();
        int style;
        if (typeface == null) {
            style = 0;
        }
        else {
            style = typeface.getStyle();
        }
        final int newStyle = this.getNewStyle(style);
        if (style == newStyle && this.mFontFamily == null) {
            return;
        }
        Typeface typeface2;
        if (this.mFontFamily != null) {
            typeface2 = TypefaceCache.getTypeface(this.mFontFamily, newStyle);
        }
        else {
            typeface2 = TypefaceCache.getTypeface(typeface, newStyle);
        }
        textPaint.setTypeface(typeface2);
    }
    
    void freeze() {
        this.mFrozen = true;
    }
    
    int getBackgroundColor() {
        return this.mBackgroundColor;
    }
    
    String getFontFamily() {
        return this.mFontFamily;
    }
    
    int getFontSize() {
        return this.mFontSize;
    }
    
    int getFontStyle() {
        return this.mFontStyle;
    }
    
    int getFontWeight() {
        return this.mFontWeight;
    }
    
    double getTextColor() {
        return this.mTextColor;
    }
    
    boolean hasStrikeThrough() {
        return this.mHasStrikeThrough;
    }
    
    boolean hasUnderline() {
        return this.mHasUnderline;
    }
    
    boolean isFrozen() {
        return this.mFrozen;
    }
    
    FontStylingSpan mutableCopy() {
        return new FontStylingSpan(this.mTextColor, this.mBackgroundColor, this.mFontSize, this.mFontStyle, this.mFontWeight, this.mHasUnderline, this.mHasStrikeThrough, this.mFontFamily, false);
    }
    
    void setBackgroundColor(final int mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }
    
    void setFontFamily(final String mFontFamily) {
        this.mFontFamily = mFontFamily;
    }
    
    void setFontSize(final int mFontSize) {
        this.mFontSize = mFontSize;
    }
    
    void setFontStyle(final int mFontStyle) {
        this.mFontStyle = mFontStyle;
    }
    
    void setFontWeight(final int mFontWeight) {
        this.mFontWeight = mFontWeight;
    }
    
    void setHasStrikeThrough(final boolean mHasStrikeThrough) {
        this.mHasStrikeThrough = mHasStrikeThrough;
    }
    
    void setHasUnderline(final boolean mHasUnderline) {
        this.mHasUnderline = mHasUnderline;
    }
    
    void setTextColor(final double mTextColor) {
        this.mTextColor = mTextColor;
    }
    
    public void updateDrawState(final TextPaint textPaint) {
        if (!Double.isNaN(this.mTextColor)) {
            textPaint.setColor((int)this.mTextColor);
        }
        textPaint.bgColor = this.mBackgroundColor;
        textPaint.setUnderlineText(this.mHasUnderline);
        textPaint.setStrikeThruText(this.mHasStrikeThrough);
        this.updateMeasureState(textPaint);
    }
    
    public void updateMeasureState(final TextPaint textPaint) {
        if (this.mFontSize != -1) {
            textPaint.setTextSize((float)this.mFontSize);
        }
        this.updateTypeface(textPaint);
    }
}
