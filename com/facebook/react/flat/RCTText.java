// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import android.text.SpannableStringBuilder;
import com.facebook.yoga.YogaMeasureOutput;
import android.text.TextUtils;
import com.facebook.yoga.YogaNodeAPI;
import com.facebook.yoga.YogaDirection;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.text.Layout;
import android.text.Layout$Alignment;
import android.text.TextUtils$TruncateAt;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.fbui.textlayoutbuilder.GlyphWarmer;
import com.facebook.fbui.textlayoutbuilder.glyphwarmer.GlyphWarmerImpl;
import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;
import com.facebook.yoga.YogaMeasureFunction;

final class RCTText extends RCTVirtualText implements YogaMeasureFunction
{
    private static final TextLayoutBuilder sTextLayoutBuilder;
    private int mAlignment;
    private DrawTextLayout mDrawCommand;
    private int mNumberOfLines;
    private float mSpacingAdd;
    private float mSpacingMult;
    private CharSequence mText;
    
    static {
        sTextLayoutBuilder = new TextLayoutBuilder().setShouldCacheLayout(false).setShouldWarmText(true).setGlyphWarmer(new GlyphWarmerImpl());
    }
    
    public RCTText() {
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mNumberOfLines = Integer.MAX_VALUE;
        this.mAlignment = 0;
        this.setMeasureFunction(this);
        this.getSpan().setFontSize(this.getDefaultFontSize());
    }
    
    private static Layout createTextLayout(final int n, final YogaMeasureMode yogaMeasureMode, final TextUtils$TruncateAt ellipsize, final boolean includeFontPadding, final int maxLines, final boolean singleLine, final CharSequence text, final int textSize, final float textSpacingExtra, final float textSpacingMultiplier, final int textStyle, final Layout$Alignment alignment) {
        int n2 = 0;
        switch (RCTText$1.$SwitchMap$com$facebook$yoga$YogaMeasureMode[yogaMeasureMode.ordinal()]) {
            default: {
                throw new IllegalStateException("Unexpected size mode: " + yogaMeasureMode);
            }
            case 1: {
                n2 = 0;
                break;
            }
            case 2: {
                n2 = 1;
                break;
            }
            case 3: {
                n2 = 2;
                break;
            }
        }
        RCTText.sTextLayoutBuilder.setEllipsize(ellipsize).setMaxLines(maxLines).setSingleLine(singleLine).setText(text).setTextSize(textSize).setWidth(n, n2);
        RCTText.sTextLayoutBuilder.setTextStyle(textStyle);
        RCTText.sTextLayoutBuilder.setTextDirection(TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR);
        RCTText.sTextLayoutBuilder.setIncludeFontPadding(includeFontPadding);
        RCTText.sTextLayoutBuilder.setTextSpacingExtra(textSpacingExtra);
        RCTText.sTextLayoutBuilder.setTextSpacingMultiplier(textSpacingMultiplier);
        RCTText.sTextLayoutBuilder.setAlignment(alignment);
        final Layout build = RCTText.sTextLayoutBuilder.build();
        RCTText.sTextLayoutBuilder.setText(null);
        return build;
    }
    
    public Layout$Alignment getAlignment() {
        final int n = 3;
        final boolean b = this.getLayoutDirection() == YogaDirection.RTL;
        switch (this.mAlignment) {
            default: {
                return Layout$Alignment.ALIGN_NORMAL;
            }
            case 3: {
                int n2;
                if (b) {
                    n2 = 4;
                }
                else {
                    n2 = 3;
                }
                return Layout$Alignment.values()[n2];
            }
            case 5: {
                int n3;
                if (b) {
                    n3 = n;
                }
                else {
                    n3 = 4;
                }
                return Layout$Alignment.values()[n3];
            }
            case 17: {
                return Layout$Alignment.ALIGN_CENTER;
            }
        }
    }
    
    @Override
    protected int getDefaultFontSize() {
        return RCTVirtualText.fontSizeFromSp(14.0f);
    }
    
    @Override
    public boolean isVirtual() {
        return false;
    }
    
    @Override
    public boolean isVirtualAnchor() {
        return true;
    }
    
    @Override
    public long measure(final YogaNodeAPI yogaNodeAPI, final float n, final YogaMeasureMode yogaMeasureMode, final float n2, final YogaMeasureMode yogaMeasureMode2) {
        final SpannableStringBuilder text = this.getText();
        if (TextUtils.isEmpty((CharSequence)text)) {
            this.mText = null;
            return YogaMeasureOutput.make(0, 0);
        }
        this.mText = (CharSequence)text;
        final Layout textLayout = createTextLayout((int)Math.ceil(n), yogaMeasureMode, TextUtils$TruncateAt.END, true, this.mNumberOfLines, this.mNumberOfLines == 1, (CharSequence)text, this.getFontSize(), this.mSpacingAdd, this.mSpacingMult, this.getFontStyle(), this.getAlignment());
        if (this.mDrawCommand != null && !this.mDrawCommand.isFrozen()) {
            this.mDrawCommand.setLayout(textLayout);
        }
        else {
            this.mDrawCommand = new DrawTextLayout(textLayout);
        }
        return YogaMeasureOutput.make(this.mDrawCommand.getLayoutWidth(), this.mDrawCommand.getLayoutHeight());
    }
    
    @Override
    protected void notifyChanged(final boolean b) {
        this.dirty();
    }
    
    @ReactProp(defaultDouble = Double.NaN, name = "lineHeight")
    public void setLineHeight(final double n) {
        if (Double.isNaN(n)) {
            this.mSpacingMult = 1.0f;
            this.mSpacingAdd = 0.0f;
        }
        else {
            this.mSpacingMult = 0.0f;
            this.mSpacingAdd = PixelUtil.toPixelFromSP((float)n);
        }
        this.notifyChanged(true);
    }
    
    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(final int mNumberOfLines) {
        this.mNumberOfLines = mNumberOfLines;
        this.notifyChanged(true);
    }
    
    @ReactProp(name = "textAlign")
    public void setTextAlign(final String s) {
        if (s == null || "auto".equals(s)) {
            this.mAlignment = 0;
        }
        else if ("left".equals(s)) {
            this.mAlignment = 3;
        }
        else if ("right".equals(s)) {
            this.mAlignment = 5;
        }
        else {
            if (!"center".equals(s)) {
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + s);
            }
            this.mAlignment = 17;
        }
        this.notifyChanged(false);
    }
}
