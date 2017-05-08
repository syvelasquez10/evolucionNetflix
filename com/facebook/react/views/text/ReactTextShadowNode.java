// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.yoga.YogaDirection;
import com.facebook.react.uimanager.PixelUtil;
import java.util.ArrayList;
import com.facebook.react.uimanager.ReactShadowNode;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import com.facebook.react.uimanager.IllegalViewOperationException;
import java.util.List;
import android.text.SpannableStringBuilder;
import com.facebook.yoga.YogaMeasureFunction;
import android.text.Spannable;
import android.text.TextPaint;
import com.facebook.react.uimanager.LayoutShadowNode;

public class ReactTextShadowNode extends LayoutShadowNode
{
    private static final TextPaint sTextPaintInstance;
    private int mBackgroundColor;
    private int mColor;
    protected boolean mContainsImages;
    private String mFontFamily;
    protected int mFontSize;
    private int mFontStyle;
    private int mFontWeight;
    private float mHeightOfTallestInlineImage;
    private boolean mIsBackgroundColorSet;
    private boolean mIsColorSet;
    private boolean mIsLineThroughTextDecorationSet;
    private boolean mIsUnderlineTextDecorationSet;
    private float mLineHeight;
    protected int mNumberOfLines;
    private Spannable mPreparedSpannableText;
    private String mText;
    protected int mTextAlign;
    private final YogaMeasureFunction mTextMeasureFunction;
    private int mTextShadowColor;
    private float mTextShadowOffsetDx;
    private float mTextShadowOffsetDy;
    private float mTextShadowRadius;
    
    static {
        (sTextPaintInstance = new TextPaint()).setFlags(1);
    }
    
    public ReactTextShadowNode() {
        this.mTextMeasureFunction = new ReactTextShadowNode$1(this);
        this.mLineHeight = Float.NaN;
        this.mIsColorSet = false;
        this.mIsBackgroundColorSet = false;
        this.mNumberOfLines = -1;
        this.mFontSize = -1;
        this.mTextAlign = 0;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 1.0f;
        this.mTextShadowColor = 1426063360;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mText = null;
        this.mContainsImages = false;
        this.mHeightOfTallestInlineImage = Float.NaN;
        if (!this.isVirtual()) {
            this.setMeasureFunction(this.mTextMeasureFunction);
        }
    }
    
    private static void buildSpannedFromTextCSSNode(final ReactTextShadowNode reactTextShadowNode, final SpannableStringBuilder spannableStringBuilder, final List<ReactTextShadowNode$SetSpanOperation> list) {
        final int length = spannableStringBuilder.length();
        if (reactTextShadowNode.mText != null) {
            spannableStringBuilder.append((CharSequence)reactTextShadowNode.mText);
        }
        for (int childCount = reactTextShadowNode.getChildCount(), i = 0; i < childCount; ++i) {
            final ReactShadowNode child = reactTextShadowNode.getChildAt(i);
            if (child instanceof ReactTextShadowNode) {
                buildSpannedFromTextCSSNode((ReactTextShadowNode)child, spannableStringBuilder, list);
            }
            else {
                if (!(child instanceof ReactTextInlineImageShadowNode)) {
                    throw new IllegalViewOperationException("Unexpected view type nested under text node: " + ((ReactTextInlineImageShadowNode)child).getClass());
                }
                spannableStringBuilder.append((CharSequence)"I");
                list.add(new ReactTextShadowNode$SetSpanOperation(spannableStringBuilder.length() - "I".length(), spannableStringBuilder.length(), ((ReactTextInlineImageShadowNode)child).buildInlineImageSpan()));
            }
            child.markUpdateSeen();
        }
        final int length2 = spannableStringBuilder.length();
        if (length2 >= length) {
            if (reactTextShadowNode.mIsColorSet) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new ForegroundColorSpan(reactTextShadowNode.mColor)));
            }
            if (reactTextShadowNode.mIsBackgroundColorSet) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new BackgroundColorSpan(reactTextShadowNode.mBackgroundColor)));
            }
            if (reactTextShadowNode.mFontSize != -1) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new AbsoluteSizeSpan(reactTextShadowNode.mFontSize)));
            }
            if (reactTextShadowNode.mFontStyle != -1 || reactTextShadowNode.mFontWeight != -1 || reactTextShadowNode.mFontFamily != null) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new CustomStyleSpan(reactTextShadowNode.mFontStyle, reactTextShadowNode.mFontWeight, reactTextShadowNode.mFontFamily, reactTextShadowNode.getThemedContext().getAssets())));
            }
            if (reactTextShadowNode.mIsUnderlineTextDecorationSet) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new UnderlineSpan()));
            }
            if (reactTextShadowNode.mIsLineThroughTextDecorationSet) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new StrikethroughSpan()));
            }
            if (reactTextShadowNode.mTextShadowOffsetDx != 0.0f || reactTextShadowNode.mTextShadowOffsetDy != 0.0f) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new ShadowStyleSpan(reactTextShadowNode.mTextShadowOffsetDx, reactTextShadowNode.mTextShadowOffsetDy, reactTextShadowNode.mTextShadowRadius, reactTextShadowNode.mTextShadowColor)));
            }
            if (!Float.isNaN(reactTextShadowNode.getEffectiveLineHeight())) {
                list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new CustomLineHeightSpan(reactTextShadowNode.getEffectiveLineHeight())));
            }
            list.add(new ReactTextShadowNode$SetSpanOperation(length, length2, new ReactTagSpan(reactTextShadowNode.getReactTag())));
        }
    }
    
    protected static Spannable fromTextCSSNode(final ReactTextShadowNode reactTextShadowNode) {
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        final ArrayList<ReactTextShadowNode$SetSpanOperation> list = new ArrayList<ReactTextShadowNode$SetSpanOperation>();
        buildSpannedFromTextCSSNode(reactTextShadowNode, spannableStringBuilder, list);
        if (reactTextShadowNode.mFontSize == -1) {
            spannableStringBuilder.setSpan((Object)new AbsoluteSizeSpan((int)Math.ceil(PixelUtil.toPixelFromSP(14.0f))), 0, spannableStringBuilder.length(), 17);
        }
        reactTextShadowNode.mContainsImages = false;
        reactTextShadowNode.mHeightOfTallestInlineImage = Float.NaN;
        for (int i = list.size() - 1; i >= 0; --i) {
            final ReactTextShadowNode$SetSpanOperation reactTextShadowNode$SetSpanOperation = list.get(i);
            if (reactTextShadowNode$SetSpanOperation.what instanceof TextInlineImageSpan) {
                final int height = ((TextInlineImageSpan)reactTextShadowNode$SetSpanOperation.what).getHeight();
                reactTextShadowNode.mContainsImages = true;
                if (Float.isNaN(reactTextShadowNode.mHeightOfTallestInlineImage) || height > reactTextShadowNode.mHeightOfTallestInlineImage) {
                    reactTextShadowNode.mHeightOfTallestInlineImage = height;
                }
            }
            reactTextShadowNode$SetSpanOperation.execute(spannableStringBuilder);
        }
        return (Spannable)spannableStringBuilder;
    }
    
    private int getTextAlign() {
        final int mTextAlign = this.mTextAlign;
        if (this.getLayoutDirection() == YogaDirection.RTL) {
            if (mTextAlign == 5) {
                return 3;
            }
            if (mTextAlign == 3) {
                return 5;
            }
        }
        return mTextAlign;
    }
    
    private static int parseNumericFontWeight(final String s) {
        if (s.length() == 3 && s.endsWith("00") && s.charAt(0) <= '9' && s.charAt(0) >= '1') {
            return (s.charAt(0) - '0') * 'd';
        }
        return -1;
    }
    
    public float getEffectiveLineHeight() {
        int n;
        if (!Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && this.mHeightOfTallestInlineImage > this.mLineHeight) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return this.mHeightOfTallestInlineImage;
        }
        return this.mLineHeight;
    }
    
    @Override
    public boolean isVirtualAnchor() {
        return !this.isVirtual();
    }
    
    @Override
    public void markUpdated() {
        super.markUpdated();
        if (!this.isVirtual()) {
            super.dirty();
        }
    }
    
    @Override
    public void onBeforeLayout() {
        if (this.isVirtual()) {
            return;
        }
        this.mPreparedSpannableText = fromTextCSSNode(this);
        this.markUpdated();
    }
    
    @Override
    public void onCollectExtraUpdates(final UIViewOperationQueue uiViewOperationQueue) {
        if (!this.isVirtual()) {
            super.onCollectExtraUpdates(uiViewOperationQueue);
            if (this.mPreparedSpannableText != null) {
                uiViewOperationQueue.enqueueUpdateExtraData(this.getReactTag(), new ReactTextUpdate(this.mPreparedSpannableText, -1, this.mContainsImages, this.getPadding(4), this.getPadding(1), this.getPadding(5), this.getPadding(3), this.getTextAlign()));
            }
        }
    }
    
    @ReactProp(name = "backgroundColor")
    public void setBackgroundColor(final Integer n) {
        if (!this.isVirtualAnchor()) {
            this.mIsBackgroundColorSet = (n != null);
            if (this.mIsBackgroundColorSet) {
                this.mBackgroundColor = n;
            }
            this.markUpdated();
        }
    }
    
    @ReactProp(name = "color")
    public void setColor(final Integer n) {
        this.mIsColorSet = (n != null);
        if (this.mIsColorSet) {
            this.mColor = n;
        }
        this.markUpdated();
    }
    
    @ReactProp(name = "fontFamily")
    public void setFontFamily(final String mFontFamily) {
        this.mFontFamily = mFontFamily;
        this.markUpdated();
    }
    
    @ReactProp(defaultFloat = -1.0f, name = "fontSize")
    public void setFontSize(final float n) {
        float n2 = n;
        if (n != -1.0f) {
            n2 = (float)Math.ceil(PixelUtil.toPixelFromSP(n));
        }
        this.mFontSize = (int)n2;
        this.markUpdated();
    }
    
    @ReactProp(name = "fontStyle")
    public void setFontStyle(final String s) {
        int mFontStyle = -1;
        if ("italic".equals(s)) {
            mFontStyle = 2;
        }
        else if ("normal".equals(s)) {
            mFontStyle = 0;
        }
        if (mFontStyle != this.mFontStyle) {
            this.mFontStyle = mFontStyle;
            this.markUpdated();
        }
    }
    
    @ReactProp(name = "fontWeight")
    public void setFontWeight(final String s) {
        final int n = -1;
        int numericFontWeight;
        if (s != null) {
            numericFontWeight = parseNumericFontWeight(s);
        }
        else {
            numericFontWeight = -1;
        }
        int mFontWeight = 0;
        Label_0031: {
            if (numericFontWeight >= 500 || "bold".equals(s)) {
                mFontWeight = 1;
            }
            else {
                if (!"normal".equals(s)) {
                    mFontWeight = n;
                    if (numericFontWeight == -1) {
                        break Label_0031;
                    }
                    mFontWeight = n;
                    if (numericFontWeight >= 500) {
                        break Label_0031;
                    }
                }
                mFontWeight = 0;
            }
        }
        if (mFontWeight != this.mFontWeight) {
            this.mFontWeight = mFontWeight;
            this.markUpdated();
        }
    }
    
    @ReactProp(defaultInt = -1, name = "lineHeight")
    public void setLineHeight(final int n) {
        float pixelFromSP;
        if (n == -1) {
            pixelFromSP = Float.NaN;
        }
        else {
            pixelFromSP = PixelUtil.toPixelFromSP(n);
        }
        this.mLineHeight = pixelFromSP;
        this.markUpdated();
    }
    
    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(final int n) {
        int mNumberOfLines = n;
        if (n == 0) {
            mNumberOfLines = -1;
        }
        this.mNumberOfLines = mNumberOfLines;
        this.markUpdated();
    }
    
    @ReactProp(name = "text")
    public void setText(final String mText) {
        this.mText = mText;
        this.markUpdated();
    }
    
    @ReactProp(name = "textAlign")
    public void setTextAlign(final String s) {
        if (s == null || "auto".equals(s)) {
            this.mTextAlign = 0;
        }
        else if ("left".equals(s)) {
            this.mTextAlign = 3;
        }
        else if ("right".equals(s)) {
            this.mTextAlign = 5;
        }
        else if ("center".equals(s)) {
            this.mTextAlign = 1;
        }
        else {
            if (!"justify".equals(s)) {
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + s);
            }
            this.mTextAlign = 3;
        }
        this.markUpdated();
    }
    
    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(final String s) {
        int i = 0;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (s != null) {
            for (String[] split = s.split(" "); i < split.length; ++i) {
                final String s2 = split[i];
                if ("underline".equals(s2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                }
                else if ("line-through".equals(s2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        this.markUpdated();
    }
    
    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(final int mTextShadowColor) {
        if (mTextShadowColor != this.mTextShadowColor) {
            this.mTextShadowColor = mTextShadowColor;
            this.markUpdated();
        }
    }
    
    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(final ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
            }
        }
        this.markUpdated();
    }
    
    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(final float mTextShadowRadius) {
        if (mTextShadowRadius != this.mTextShadowRadius) {
            this.mTextShadowRadius = mTextShadowRadius;
            this.markUpdated();
        }
    }
}
