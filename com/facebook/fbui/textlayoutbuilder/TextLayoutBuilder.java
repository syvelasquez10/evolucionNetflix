// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder;

import android.graphics.Typeface;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.TextUtils$TruncateAt;
import android.text.Layout$Alignment;
import android.text.BoringLayout$Metrics;
import android.util.Log;
import android.text.BoringLayout;
import android.text.style.ClickableSpan;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.Layout;
import android.support.v4.util.LruCache;

public class TextLayoutBuilder
{
    static final LruCache<Integer, Layout> sCache;
    private GlyphWarmer mGlyphWarmer;
    final TextLayoutBuilder$Params mParams;
    private Layout mSavedLayout;
    private boolean mShouldCacheLayout;
    private boolean mShouldWarmText;
    
    static {
        sCache = new LruCache<Integer, Layout>(100);
    }
    
    public TextLayoutBuilder() {
        this.mParams = new TextLayoutBuilder$Params();
        this.mSavedLayout = null;
        this.mShouldCacheLayout = true;
        this.mShouldWarmText = false;
    }
    
    public Layout build() {
        while (true) {
            Layout mSavedLayout;
            if (this.mShouldCacheLayout && this.mSavedLayout != null) {
                mSavedLayout = this.mSavedLayout;
            }
            else {
                if (TextUtils.isEmpty(this.mParams.text)) {
                    return null;
                }
                final int n = -1;
                int n3;
                if (this.mShouldCacheLayout && this.mParams.text instanceof Spannable) {
                    int n2;
                    if (((ClickableSpan[])((Spannable)this.mParams.text).getSpans(0, this.mParams.text.length() - 1, (Class)ClickableSpan.class)).length > 0) {
                        n2 = 1;
                    }
                    else {
                        n2 = 0;
                    }
                    n3 = n2;
                }
                else {
                    n3 = 0;
                }
                int hashCode = n;
                if (this.mShouldCacheLayout) {
                    hashCode = n;
                    if (n3 == 0) {
                        hashCode = this.mParams.hashCode();
                        if ((mSavedLayout = TextLayoutBuilder.sCache.get(hashCode)) != null) {
                            return mSavedLayout;
                        }
                    }
                }
                BoringLayout$Metrics boring = null;
                int maxLines;
                if (this.mParams.singleLine) {
                    maxLines = 1;
                }
                else {
                    maxLines = this.mParams.maxLines;
                }
                if (maxLines == 1) {
                    boring = BoringLayout.isBoring(this.mParams.text, this.mParams.paint);
                }
                int n4 = 0;
                switch (this.mParams.measureMode) {
                    default: {
                        throw new IllegalStateException("Unexpected measure mode " + this.mParams.measureMode);
                    }
                    case 0: {
                        n4 = (int)Math.ceil(Layout.getDesiredWidth(this.mParams.text, this.mParams.paint));
                        break;
                    }
                    case 1: {
                        n4 = this.mParams.width;
                        break;
                    }
                    case 2: {
                        n4 = Math.min((int)Math.ceil(Layout.getDesiredWidth(this.mParams.text, this.mParams.paint)), this.mParams.width);
                        break;
                    }
                }
                if (boring == null) {
                    break Label_0529;
                }
                Object mSavedLayout2 = BoringLayout.make(this.mParams.text, this.mParams.paint, n4, this.mParams.alignment, this.mParams.spacingMult, this.mParams.spacingAdd, boring, this.mParams.includePadding, this.mParams.ellipsize, n4);
                while (true) {
                    if (this.mShouldCacheLayout && n3 == 0) {
                        this.mSavedLayout = (Layout)mSavedLayout2;
                        TextLayoutBuilder.sCache.put(hashCode, (Layout)mSavedLayout2);
                    }
                    this.mParams.mForceNewPaint = true;
                    mSavedLayout = (Layout)mSavedLayout2;
                    if (!this.mShouldWarmText) {
                        return mSavedLayout;
                    }
                    mSavedLayout = (Layout)mSavedLayout2;
                    if (this.mGlyphWarmer != null) {
                        this.mGlyphWarmer.warmLayout((Layout)mSavedLayout2);
                        return (Layout)mSavedLayout2;
                    }
                    return mSavedLayout;
                    mSavedLayout2 = StaticLayoutHelper.make(this.mParams.text, 0, this.mParams.text.length(), this.mParams.paint, n4, this.mParams.alignment, this.mParams.spacingMult, this.mParams.spacingAdd, this.mParams.includePadding, this.mParams.ellipsize, n4, maxLines, this.mParams.textDirection);
                    continue;
                }
            }
            return mSavedLayout;
            final IndexOutOfBoundsException ex;
            Log.e("TextLayoutBuilder", "Hit bug #35412, retrying with Spannables removed", (Throwable)ex);
            this.mParams.text = this.mParams.text.toString();
            continue;
        }
    }
    
    public TextLayoutBuilder setAlignment(final Layout$Alignment alignment) {
        if (this.mParams.alignment != alignment) {
            this.mParams.alignment = alignment;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setEllipsize(final TextUtils$TruncateAt ellipsize) {
        if (this.mParams.ellipsize != ellipsize) {
            this.mParams.ellipsize = ellipsize;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setGlyphWarmer(final GlyphWarmer mGlyphWarmer) {
        this.mGlyphWarmer = mGlyphWarmer;
        return this;
    }
    
    public TextLayoutBuilder setIncludeFontPadding(final boolean includePadding) {
        if (this.mParams.includePadding != includePadding) {
            this.mParams.includePadding = includePadding;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setMaxLines(final int maxLines) {
        if (this.mParams.maxLines != maxLines) {
            this.mParams.maxLines = maxLines;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setShouldCacheLayout(final boolean mShouldCacheLayout) {
        this.mShouldCacheLayout = mShouldCacheLayout;
        return this;
    }
    
    public TextLayoutBuilder setShouldWarmText(final boolean mShouldWarmText) {
        this.mShouldWarmText = mShouldWarmText;
        return this;
    }
    
    public TextLayoutBuilder setSingleLine(final boolean singleLine) {
        if (this.mParams.singleLine != singleLine) {
            this.mParams.singleLine = singleLine;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setText(final CharSequence text) {
        if (text == this.mParams.text || (text != null && this.mParams.text != null && text.equals(this.mParams.text))) {
            return this;
        }
        this.mParams.text = text;
        this.mSavedLayout = null;
        return this;
    }
    
    public TextLayoutBuilder setTextDirection(final TextDirectionHeuristicCompat textDirection) {
        if (this.mParams.textDirection != textDirection) {
            this.mParams.textDirection = textDirection;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setTextSize(final int n) {
        if (this.mParams.paint.getTextSize() != n) {
            this.mParams.createNewPaintIfNeeded();
            this.mParams.paint.setTextSize((float)n);
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setTextSpacingExtra(final float spacingAdd) {
        if (this.mParams.spacingAdd != spacingAdd) {
            this.mParams.spacingAdd = spacingAdd;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setTextSpacingMultiplier(final float spacingMult) {
        if (this.mParams.spacingMult != spacingMult) {
            this.mParams.spacingMult = spacingMult;
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setTextStyle(final int n) {
        return this.setTypeface(Typeface.defaultFromStyle(n));
    }
    
    public TextLayoutBuilder setTypeface(final Typeface typeface) {
        if (this.mParams.paint.getTypeface() != typeface) {
            this.mParams.createNewPaintIfNeeded();
            this.mParams.paint.setTypeface(typeface);
            this.mSavedLayout = null;
        }
        return this;
    }
    
    public TextLayoutBuilder setWidth(final int width, final int measureMode) {
        if (this.mParams.width != width || this.mParams.measureMode != measureMode) {
            this.mParams.width = width;
            this.mParams.measureMode = measureMode;
            this.mSavedLayout = null;
        }
        return this;
    }
}
