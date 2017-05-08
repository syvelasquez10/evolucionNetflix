// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.text.Spannable;

public class ReactTextUpdate
{
    private final boolean mContainsImages;
    private final int mJsEventCounter;
    private final float mPaddingBottom;
    private final float mPaddingLeft;
    private final float mPaddingRight;
    private final float mPaddingTop;
    private final Spannable mText;
    private final int mTextAlign;
    private final int mTextBreakStrategy;
    
    public ReactTextUpdate(final Spannable spannable, final int n, final boolean b, final float n2, final float n3, final float n4, final float n5, final int n6) {
        this(spannable, n, b, n2, n3, n4, n5, n6, 1);
    }
    
    public ReactTextUpdate(final Spannable mText, final int mJsEventCounter, final boolean mContainsImages, final float mPaddingLeft, final float mPaddingTop, final float mPaddingRight, final float mPaddingBottom, final int mTextAlign, final int mTextBreakStrategy) {
        this.mText = mText;
        this.mJsEventCounter = mJsEventCounter;
        this.mContainsImages = mContainsImages;
        this.mPaddingLeft = mPaddingLeft;
        this.mPaddingTop = mPaddingTop;
        this.mPaddingRight = mPaddingRight;
        this.mPaddingBottom = mPaddingBottom;
        this.mTextAlign = mTextAlign;
        this.mTextBreakStrategy = mTextBreakStrategy;
    }
    
    public boolean containsImages() {
        return this.mContainsImages;
    }
    
    public int getJsEventCounter() {
        return this.mJsEventCounter;
    }
    
    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }
    
    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }
    
    public float getPaddingRight() {
        return this.mPaddingRight;
    }
    
    public float getPaddingTop() {
        return this.mPaddingTop;
    }
    
    public Spannable getText() {
        return this.mText;
    }
    
    public int getTextAlign() {
        return this.mTextAlign;
    }
    
    public int getTextBreakStrategy() {
        return this.mTextBreakStrategy;
    }
}
