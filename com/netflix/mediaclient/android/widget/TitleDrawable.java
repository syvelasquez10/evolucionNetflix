// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.graphics.Canvas;
import com.netflix.mediaclient.ui.common.StaticLayoutWithMaxLines;
import android.text.TextUtils$TruncateAt;
import android.text.TextUtils;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.Layout$Alignment;
import android.graphics.drawable.Drawable;

public class TitleDrawable extends Drawable
{
    private static final boolean DEBUG_BORDERS = false;
    private Drawable mBackground;
    private boolean mDirty;
    private Layout$Alignment mHorizontalTextAlignment;
    private boolean mHorizontallyCentered;
    private int mMaxLines;
    private final TextPaint mPaint;
    private int mTextContainerWidth;
    private StaticLayout mTextLayout;
    private int mTextPaddingBottom;
    private int mTextPaddingLeft;
    private int mTextPaddingRight;
    private int mTextPaddingTop;
    private int mTextSize;
    private boolean mVerticallyCentered;
    private CharSequence mVideoTitle;
    
    public TitleDrawable() {
        this.mMaxLines = 3;
        this.mVerticallyCentered = false;
        this.mHorizontallyCentered = false;
        this.mDirty = true;
        this.mHorizontalTextAlignment = Layout$Alignment.ALIGN_NORMAL;
        this.mPaint = new TextPaint(1);
    }
    
    private void invalidateTextLayout() {
        if (this.getBounds().width() > 0 && !TextUtils.isEmpty(this.mVideoTitle)) {
            this.mTextContainerWidth = this.getBounds().width() - this.mTextPaddingLeft - this.mTextPaddingRight;
            Layout$Alignment mHorizontalTextAlignment;
            if (this.mHorizontallyCentered) {
                mHorizontalTextAlignment = Layout$Alignment.ALIGN_CENTER;
            }
            else {
                mHorizontalTextAlignment = Layout$Alignment.ALIGN_NORMAL;
            }
            this.mHorizontalTextAlignment = mHorizontalTextAlignment;
            this.mTextLayout = StaticLayoutWithMaxLines.instantiate(this.mVideoTitle, 0, this.mVideoTitle.length(), this.mPaint, this.mTextContainerWidth, this.mHorizontalTextAlignment, 1.0f, 0.0f, false, TextUtils$TruncateAt.END, this.mTextContainerWidth, this.mMaxLines);
            return;
        }
        this.mTextContainerWidth = 0;
        this.mTextLayout = null;
    }
    
    public void draw(final Canvas canvas) {
        if (this.mDirty) {
            this.invalidateTextLayout();
            this.mDirty = false;
        }
        if (this.mBackground != null) {
            this.mBackground.draw(canvas);
        }
        if (this.mTextLayout != null) {
            final int height = this.mTextLayout.getHeight();
            final float n = (canvas.getWidth() - this.mTextContainerWidth) / 2;
            float n2;
            if (this.mVerticallyCentered) {
                n2 = canvas.getHeight() / 2 - height;
            }
            else {
                n2 = canvas.getHeight() - height - this.mTextPaddingTop - this.mTextPaddingBottom;
            }
            canvas.save();
            canvas.translate(n, n2);
            this.mTextLayout.draw(canvas);
            canvas.restore();
        }
    }
    
    public int getOpacity() {
        return -3;
    }
    
    public void setAlpha(final int n) {
        if (this.mPaint.getAlpha() != n) {
            this.mDirty = true;
        }
        this.mPaint.setAlpha(n);
        if (this.mBackground != null) {
            this.mBackground.setAlpha(n);
        }
    }
    
    public void setBackground(final Context context, final int n) {
        this.mBackground = ContextCompat.getDrawable(context, n);
    }
    
    public void setBackground(final Drawable mBackground) {
        this.mBackground = mBackground;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        if (this.getBounds() == null || this.getBounds().left != n || this.getBounds().top != n2 || this.getBounds().bottom != n4) {
            this.mDirty = true;
        }
        super.setBounds(n, n2, n3, n4);
        if (this.mBackground != null) {
            this.mBackground.setBounds(n, n2, n3, n4);
        }
    }
    
    public void setBounds(final Rect rect) {
        if (this.getBounds() == null || !this.getBounds().equals((Object)rect)) {
            this.mDirty = true;
        }
        super.setBounds(rect);
        if (this.mBackground != null) {
            this.mBackground.setBounds(rect);
        }
    }
    
    public void setColorFilter(final ColorFilter colorFilter) {
        if (!this.mPaint.getColorFilter().equals(colorFilter)) {
            this.mDirty = true;
        }
        this.mPaint.setColorFilter(colorFilter);
    }
    
    public void setHorizontallyCentered(final boolean mHorizontallyCentered) {
        if (this.mHorizontallyCentered != mHorizontallyCentered) {
            this.mDirty = true;
        }
        this.mHorizontallyCentered = mHorizontallyCentered;
    }
    
    public void setMaxLines(final int mMaxLines) {
        if (this.mMaxLines != mMaxLines) {
            this.mDirty = true;
        }
        this.mMaxLines = mMaxLines;
    }
    
    public void setTextColor(final int color) {
        if (this.mPaint.getColor() != color) {
            this.mDirty = true;
        }
        this.mPaint.setColor(color);
    }
    
    public void setTextPadding(final int n) {
        if (this.mTextPaddingTop != n || this.mTextPaddingRight != n || this.mTextPaddingBottom != n || this.mTextPaddingLeft != n) {
            this.mDirty = true;
        }
        this.mTextPaddingLeft = n;
        this.mTextPaddingBottom = n;
        this.mTextPaddingRight = n;
        this.mTextPaddingTop = n;
    }
    
    public void setTextPadding(final int mTextPaddingTop, final int mTextPaddingRight, final int mTextPaddingBottom, final int mTextPaddingLeft) {
        if (this.mTextPaddingTop != mTextPaddingTop || this.mTextPaddingRight != mTextPaddingRight || this.mTextPaddingBottom != mTextPaddingBottom || this.mTextPaddingLeft != mTextPaddingLeft) {
            this.mDirty = true;
        }
        this.mTextPaddingTop = mTextPaddingTop;
        this.mTextPaddingRight = mTextPaddingRight;
        this.mTextPaddingBottom = mTextPaddingBottom;
        this.mTextPaddingLeft = mTextPaddingLeft;
    }
    
    public void setTextSize(final int mTextSize) {
        if (this.mPaint.getTextSize() != mTextSize) {
            this.mDirty = true;
        }
        this.mPaint.setTextSize((float)mTextSize);
        this.mTextSize = mTextSize;
    }
    
    public void setVerticallyCentered(final boolean mVerticallyCentered) {
        if (this.mVerticallyCentered != mVerticallyCentered) {
            this.mDirty = true;
        }
        this.mVerticallyCentered = mVerticallyCentered;
    }
    
    public void setVideoTitle(final CharSequence mVideoTitle) {
        if (this.mVideoTitle != null && !this.mVideoTitle.equals(mVideoTitle)) {
            this.mDirty = true;
        }
        this.mVideoTitle = mVideoTitle;
    }
}
