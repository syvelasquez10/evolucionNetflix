// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.bridge.ReadableMap;
import android.text.TextUtils;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.text.SpannableStringBuilder;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.PixelUtil;

class RCTVirtualText extends FlatTextShadowNode
{
    private FontStylingSpan mFontStylingSpan;
    private ShadowStyleSpan mShadowStyleSpan;
    
    RCTVirtualText() {
        this.mFontStylingSpan = FontStylingSpan.INSTANCE;
        this.mShadowStyleSpan = ShadowStyleSpan.INSTANCE;
    }
    
    static int fontSizeFromSp(final float n) {
        return (int)Math.ceil(PixelUtil.toPixelFromSP(n));
    }
    
    private final ShadowStyleSpan getShadowSpan() {
        if (this.mShadowStyleSpan.isFrozen()) {
            this.mShadowStyleSpan = this.mShadowStyleSpan.mutableCopy();
        }
        return this.mShadowStyleSpan;
    }
    
    private static int parseNumericFontWeight(final String s) {
        if (s.length() == 3 && s.endsWith("00") && s.charAt(0) <= '9' && s.charAt(0) >= '1') {
            return (s.charAt(0) - '0') * 'd';
        }
        return -1;
    }
    
    @Override
    public void addChildAt(final ReactShadowNode reactShadowNode, final int n) {
        super.addChildAt(reactShadowNode, n);
        this.notifyChanged(true);
    }
    
    protected int getDefaultFontSize() {
        return -1;
    }
    
    protected final int getFontSize() {
        return this.mFontStylingSpan.getFontSize();
    }
    
    protected final int getFontStyle() {
        final int fontStyle = this.mFontStylingSpan.getFontStyle();
        if (fontStyle >= 0) {
            return fontStyle;
        }
        return 0;
    }
    
    protected final FontStylingSpan getSpan() {
        if (this.mFontStylingSpan.isFrozen()) {
            this.mFontStylingSpan = this.mFontStylingSpan.mutableCopy();
        }
        return this.mFontStylingSpan;
    }
    
    final SpannableStringBuilder getText() {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        this.collectText(spannableStringBuilder);
        this.applySpans(spannableStringBuilder, this.isEditable());
        return spannableStringBuilder;
    }
    
    @Override
    protected void performApplySpans(final SpannableStringBuilder spannableStringBuilder, int i, int childCount, final boolean b) {
        this.mFontStylingSpan.freeze();
        int n;
        if (b) {
            n = 33;
        }
        else if (i == 0) {
            n = 18;
        }
        else {
            n = 34;
        }
        spannableStringBuilder.setSpan((Object)this.mFontStylingSpan, i, childCount, n);
        if (this.mShadowStyleSpan.getColor() != 0 && this.mShadowStyleSpan.getRadius() != 0.0f) {
            this.mShadowStyleSpan.freeze();
            spannableStringBuilder.setSpan((Object)this.mShadowStyleSpan, i, childCount, n);
        }
        for (childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            ((FlatTextShadowNode)this.getChildAt(i)).applySpans(spannableStringBuilder, b);
        }
    }
    
    @Override
    protected void performCollectText(final SpannableStringBuilder spannableStringBuilder) {
        for (int childCount = this.getChildCount(), i = 0; i < childCount; ++i) {
            ((FlatTextShadowNode)this.getChildAt(i)).collectText(spannableStringBuilder);
        }
    }
    
    @Override
    public void setBackgroundColor(final int n) {
        if (this.isVirtual()) {
            if (this.mFontStylingSpan.getBackgroundColor() != n) {
                this.getSpan().setBackgroundColor(n);
                this.notifyChanged(false);
            }
            return;
        }
        super.setBackgroundColor(n);
    }
    
    @ReactProp(defaultDouble = Double.NaN, name = "color")
    public void setColor(final double textColor) {
        if (this.mFontStylingSpan.getTextColor() != textColor) {
            this.getSpan().setTextColor(textColor);
            this.notifyChanged(false);
        }
    }
    
    @ReactProp(name = "fontFamily")
    public void setFontFamily(final String fontFamily) {
        if (!TextUtils.equals((CharSequence)this.mFontStylingSpan.getFontFamily(), (CharSequence)fontFamily)) {
            this.getSpan().setFontFamily(fontFamily);
            this.notifyChanged(true);
        }
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(final float n) {
        int fontSize;
        if (Float.isNaN(n)) {
            fontSize = this.getDefaultFontSize();
        }
        else {
            fontSize = fontSizeFromSp(n);
        }
        if (this.mFontStylingSpan.getFontSize() != fontSize) {
            this.getSpan().setFontSize(fontSize);
            this.notifyChanged(true);
        }
    }
    
    @ReactProp(name = "fontStyle")
    public void setFontStyle(final String s) {
        int fontStyle;
        if (s == null) {
            fontStyle = -1;
        }
        else if ("italic".equals(s)) {
            fontStyle = 2;
        }
        else {
            if (!"normal".equals(s)) {
                throw new RuntimeException("invalid font style " + s);
            }
            fontStyle = 0;
        }
        if (this.mFontStylingSpan.getFontStyle() != fontStyle) {
            this.getSpan().setFontStyle(fontStyle);
            this.notifyChanged(true);
        }
    }
    
    @ReactProp(name = "fontWeight")
    public void setFontWeight(final String s) {
        final boolean b = false;
        int fontWeight;
        if (s == null) {
            fontWeight = -1;
        }
        else if ("bold".equals(s)) {
            fontWeight = 1;
        }
        else {
            fontWeight = (b ? 1 : 0);
            if (!"normal".equals(s)) {
                final int numericFontWeight = parseNumericFontWeight(s);
                if (numericFontWeight == -1) {
                    throw new RuntimeException("invalid font weight " + s);
                }
                fontWeight = (b ? 1 : 0);
                if (numericFontWeight >= 500) {
                    fontWeight = 1;
                }
            }
        }
        if (this.mFontStylingSpan.getFontWeight() != fontWeight) {
            this.getSpan().setFontWeight(fontWeight);
            this.notifyChanged(true);
        }
    }
    
    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(final String s) {
        boolean hasStrikeThrough = false;
        boolean b = false;
        boolean hasUnderline;
        if (s != null) {
            final String[] split = s.split(" ");
            final int length = split.length;
            int n = 0;
            boolean b2 = false;
            while (true) {
                hasStrikeThrough = b;
                hasUnderline = b2;
                if (n >= length) {
                    break;
                }
                final String s2 = split[n];
                boolean b3;
                if ("underline".equals(s2)) {
                    b3 = true;
                }
                else {
                    b3 = b2;
                    if ("line-through".equals(s2)) {
                        b = true;
                        b3 = b2;
                    }
                }
                ++n;
                b2 = b3;
            }
        }
        else {
            hasUnderline = false;
        }
        if (hasUnderline != this.mFontStylingSpan.hasUnderline() || hasStrikeThrough != this.mFontStylingSpan.hasStrikeThrough()) {
            final FontStylingSpan span = this.getSpan();
            span.setHasUnderline(hasUnderline);
            span.setHasStrikeThrough(hasStrikeThrough);
            this.notifyChanged(true);
        }
    }
    
    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(final int color) {
        if (this.mShadowStyleSpan.getColor() != color) {
            this.getShadowSpan().setColor(color);
            this.notifyChanged(false);
        }
    }
    
    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(final ReadableMap readableMap) {
        float pixelFromDIP = 0.0f;
        float n;
        if (readableMap != null) {
            float pixelFromDIP2;
            if (readableMap.hasKey("width")) {
                pixelFromDIP2 = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            else {
                pixelFromDIP2 = 0.0f;
            }
            n = pixelFromDIP2;
            if (readableMap.hasKey("height")) {
                pixelFromDIP = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
                n = pixelFromDIP2;
            }
        }
        else {
            n = 0.0f;
        }
        if (!this.mShadowStyleSpan.offsetMatches(n, pixelFromDIP)) {
            this.getShadowSpan().setOffset(n, pixelFromDIP);
            this.notifyChanged(false);
        }
    }
    
    @ReactProp(name = "textShadowRadius")
    public void setTextShadowRadius(float pixelFromDIP) {
        pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        if (this.mShadowStyleSpan.getRadius() != pixelFromDIP) {
            this.getShadowSpan().setRadius(pixelFromDIP);
            this.notifyChanged(false);
        }
    }
}
