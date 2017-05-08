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
    
    public ReactTextUpdate(final Spannable mText, final int mJsEventCounter, final boolean mContainsImages, final float mPaddingLeft, final float mPaddingTop, final float mPaddingRight, final float mPaddingBottom, final int mTextAlign) {
        this.mText = mText;
        this.mJsEventCounter = mJsEventCounter;
        this.mContainsImages = mContainsImages;
        this.mPaddingLeft = mPaddingLeft;
        this.mPaddingTop = mPaddingTop;
        this.mPaddingRight = mPaddingRight;
        this.mPaddingBottom = mPaddingBottom;
        this.mTextAlign = mTextAlign;
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
}
