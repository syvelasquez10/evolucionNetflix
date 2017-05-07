// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.content.Context;

public class AutoResizeTextView extends UnderlineTextView
{
    public static final float MIN_TEXT_SIZE = 4.0f;
    private static final String mEllipsis = "...";
    private boolean mAddEllipsis;
    private float mMaxTextSize;
    private float mMinTextSize;
    private boolean mNeedsResize;
    private float mSpacingAdd;
    private float mSpacingMult;
    private float mTextSize;
    
    public AutoResizeTextView(final Context context) {
        super(context);
        this.mNeedsResize = false;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = 4.0f;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = false;
    }
    
    public AutoResizeTextView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mNeedsResize = false;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = 4.0f;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = false;
    }
    
    public AutoResizeTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mNeedsResize = false;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = 4.0f;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = false;
        this.mTextSize = this.getTextSize();
    }
    
    private int getTextHeight(final CharSequence charSequence, final TextPaint textPaint, final int n, final float textSize) {
        textPaint.setTextSize(textSize);
        return new StaticLayout(charSequence, textPaint, n, Layout$Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, true).getHeight();
    }
    
    public boolean getAddEllipsis() {
        return this.mAddEllipsis;
    }
    
    public float getMaxTextSize() {
        return this.mMaxTextSize;
    }
    
    public float getMinTextSize() {
        return this.mMinTextSize;
    }
    
    protected void onLayout(final boolean b, final int n, final int n2, final int n3, final int n4) {
        if (b || this.mNeedsResize) {
            this.resizeText(n3 - n - this.getCompoundPaddingLeft() - this.getCompoundPaddingRight(), n4 - n2 - this.getCompoundPaddingBottom() - this.getCompoundPaddingTop());
        }
        super.onLayout(b, n, n2, n3, n4);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        if (n != n3 || n2 != n4) {
            this.mNeedsResize = true;
        }
    }
    
    protected void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        this.mNeedsResize = true;
        this.resetTextSize();
    }
    
    public void resetTextSize() {
        if (this.mTextSize > 0.0f) {
            super.setTextSize(0, this.mTextSize);
            this.mMaxTextSize = this.mTextSize;
        }
    }
    
    public void resizeText() {
        this.resizeText(this.getWidth() - this.getPaddingLeft() - this.getPaddingRight(), this.getHeight() - this.getPaddingBottom() - this.getPaddingTop());
    }
    
    public void resizeText(final int n, int lineEnd) {
        final CharSequence text = this.getText();
        if (text == null || text.length() == 0 || lineEnd <= 0 || n <= 0 || this.mTextSize == 0.0f) {
            return;
        }
        final TextPaint paint = this.getPaint();
        float textSize;
        if (this.mMaxTextSize > 0.0f) {
            textSize = Math.min(this.mTextSize, this.mMaxTextSize);
        }
        else {
            textSize = this.mTextSize;
        }
        int n2;
        for (n2 = this.getTextHeight(text, paint, n, textSize); n2 > lineEnd && textSize > this.mMinTextSize; textSize = Math.max(textSize - 2.0f, this.mMinTextSize), n2 = this.getTextHeight(text, paint, n, textSize)) {}
        if (this.mAddEllipsis && textSize == this.mMinTextSize && n2 > lineEnd) {
            final StaticLayout staticLayout = new StaticLayout(text, paint, n, Layout$Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, false);
            if (staticLayout.getLineCount() > 0) {
                final int n3 = staticLayout.getLineForVertical(lineEnd) - 1;
                if (n3 < 0) {
                    this.setText((CharSequence)"");
                }
                else {
                    final int lineStart = staticLayout.getLineStart(n3);
                    lineEnd = staticLayout.getLineEnd(n3);
                    for (float n4 = staticLayout.getLineWidth(n3); n < n4 + paint.measureText("..."); n4 = paint.measureText(text.subSequence(lineStart, lineEnd + 1).toString())) {
                        --lineEnd;
                    }
                    this.setText((CharSequence)((Object)text.subSequence(0, lineEnd) + "..."));
                }
            }
        }
        paint.setTextSize(textSize);
        this.setLineSpacing(this.mSpacingAdd, this.mSpacingMult);
        this.mNeedsResize = false;
    }
    
    public void setAddEllipsis(final boolean mAddEllipsis) {
        this.mAddEllipsis = mAddEllipsis;
    }
    
    public void setLineSpacing(final float mSpacingAdd, final float mSpacingMult) {
        super.setLineSpacing(mSpacingAdd, mSpacingMult);
        this.mSpacingMult = mSpacingMult;
        this.mSpacingAdd = mSpacingAdd;
    }
    
    public void setMaxTextSize(final float mMaxTextSize) {
        this.mMaxTextSize = mMaxTextSize;
        this.requestLayout();
        this.invalidate();
    }
    
    public void setMinTextSize(final float mMinTextSize) {
        this.mMinTextSize = mMinTextSize;
        this.requestLayout();
        this.invalidate();
    }
    
    public void setTextSize(final float textSize) {
        super.setTextSize(textSize);
        this.mTextSize = this.getTextSize();
    }
    
    public void setTextSize(final int n, final float n2) {
        super.setTextSize(n, n2);
        this.mTextSize = this.getTextSize();
    }
}
