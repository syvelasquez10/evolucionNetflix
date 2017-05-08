// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.widget.TextView;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.text.TextUtils$TruncateAt;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaConstants;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.BaseViewManager;

public class ReactTextViewManager extends BaseViewManager<ReactTextView, ReactTextShadowNode>
{
    public static final String REACT_CLASS = "RCTText";
    private static final int[] SPACING_TYPES;
    
    static {
        SPACING_TYPES = new int[] { 8, 0, 2, 1, 3 };
    }
    
    @Override
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode();
    }
    
    public ReactTextView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactTextView((Context)themedReactContext);
    }
    
    @Override
    public String getName() {
        return "RCTText";
    }
    
    @Override
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }
    
    @Override
    protected void onAfterUpdateTransaction(final ReactTextView reactTextView) {
        super.onAfterUpdateTransaction(reactTextView);
        reactTextView.updateView();
    }
    
    @ReactPropGroup(customType = "Color", names = { "borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor" })
    public void setBorderColor(final ReactTextView reactTextView, final int n, final Integer n2) {
        float n3 = Float.NaN;
        float n4;
        if (n2 == null) {
            n4 = Float.NaN;
        }
        else {
            n4 = (n2 & 0xFFFFFF);
        }
        if (n2 != null) {
            n3 = n2 >>> 24;
        }
        reactTextView.setBorderColor(ReactTextViewManager.SPACING_TYPES[n], n4, n3);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius" })
    public void setBorderRadius(final ReactTextView reactTextView, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        if (n == 0) {
            reactTextView.setBorderRadius(pixelFromDIP);
            return;
        }
        reactTextView.setBorderRadius(pixelFromDIP, n - 1);
    }
    
    @ReactProp(name = "borderStyle")
    public void setBorderStyle(final ReactTextView reactTextView, final String borderStyle) {
        reactTextView.setBorderStyle(borderStyle);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth" })
    public void setBorderWidth(final ReactTextView reactTextView, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        reactTextView.setBorderWidth(ReactTextViewManager.SPACING_TYPES[n], pixelFromDIP);
    }
    
    @ReactProp(name = "ellipsizeMode")
    public void setEllipsizeMode(final ReactTextView reactTextView, final String s) {
        if (s == null || s.equals("tail")) {
            reactTextView.setEllipsizeLocation(TextUtils$TruncateAt.END);
            return;
        }
        if (s.equals("head")) {
            reactTextView.setEllipsizeLocation(TextUtils$TruncateAt.START);
            return;
        }
        if (s.equals("middle")) {
            reactTextView.setEllipsizeLocation(TextUtils$TruncateAt.MIDDLE);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid ellipsizeMode: " + s);
    }
    
    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(final ReactTextView reactTextView, final boolean includeFontPadding) {
        reactTextView.setIncludeFontPadding(includeFontPadding);
    }
    
    @ReactProp(defaultInt = Integer.MAX_VALUE, name = "numberOfLines")
    public void setNumberOfLines(final ReactTextView reactTextView, final int numberOfLines) {
        reactTextView.setNumberOfLines(numberOfLines);
    }
    
    @ReactProp(name = "selectable")
    public void setSelectable(final ReactTextView reactTextView, final boolean textIsSelectable) {
        reactTextView.setTextIsSelectable(textIsSelectable);
    }
    
    @ReactProp(name = "textAlignVertical")
    public void setTextAlignVertical(final ReactTextView reactTextView, final String s) {
        if (s == null || "auto".equals(s)) {
            reactTextView.setGravityVertical(0);
            return;
        }
        if ("top".equals(s)) {
            reactTextView.setGravityVertical(48);
            return;
        }
        if ("bottom".equals(s)) {
            reactTextView.setGravityVertical(80);
            return;
        }
        if ("center".equals(s)) {
            reactTextView.setGravityVertical(16);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid textAlignVertical: " + s);
    }
    
    @Override
    public void updateExtraData(final ReactTextView reactTextView, final Object o) {
        final ReactTextUpdate text = (ReactTextUpdate)o;
        if (text.containsImages()) {
            TextInlineImageSpan.possiblyUpdateInlineImageSpans(text.getText(), reactTextView);
        }
        reactTextView.setText(text);
    }
}
