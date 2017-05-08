// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaWrap;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Locale;
import com.facebook.yoga.YogaAlign;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.Dynamic;

public class LayoutShadowNode extends ReactShadowNode
{
    private static boolean dynamicIsPercent(final Dynamic dynamic) {
        return dynamic.getType() == ReadableType.String && dynamic.asString().endsWith("%");
    }
    
    private static float getDynamicAsFloat(final Dynamic dynamic) {
        return PixelUtil.toPixelFromDIP(dynamic.asDouble());
    }
    
    private static float getDynamicAsPercent(final Dynamic dynamic) {
        final String string = dynamic.asString();
        return Float.parseFloat(string.substring(0, string.length() - 1));
    }
    
    private static boolean isNull(final Dynamic dynamic) {
        return dynamic == null || dynamic.isNull();
    }
    
    @ReactProp(name = "alignItems")
    public void setAlignItems(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaAlign alignItems;
        if (s == null) {
            alignItems = YogaAlign.STRETCH;
        }
        else {
            alignItems = YogaAlign.valueOf(s.toUpperCase(Locale.US).replace("-", "_"));
        }
        this.setAlignItems(alignItems);
    }
    
    @ReactProp(name = "alignSelf")
    public void setAlignSelf(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaAlign alignSelf;
        if (s == null) {
            alignSelf = YogaAlign.AUTO;
        }
        else {
            alignSelf = YogaAlign.valueOf(s.toUpperCase(Locale.US).replace("-", "_"));
        }
        this.setAlignSelf(alignSelf);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "aspectRatio")
    public void setAspectRatio(final float styleAspectRatio) {
        this.setStyleAspectRatio(styleAspectRatio);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth" })
    public void setBorderWidths(final int n, final float n2) {
        if (this.isVirtual()) {
            return;
        }
        this.setBorder(ViewProps.BORDER_SPACING_TYPES[n], PixelUtil.toPixelFromDIP(n2));
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "flex")
    @Override
    public void setFlex(final float flex) {
        if (this.isVirtual()) {
            return;
        }
        super.setFlex(flex);
    }
    
    @ReactProp(name = "flexBasis")
    public void setFlexBasis(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setFlexBasisPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = 0.0f;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setFlexBasis(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "flexDirection")
    public void setFlexDirection(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaFlexDirection flexDirection;
        if (s == null) {
            flexDirection = YogaFlexDirection.COLUMN;
        }
        else {
            flexDirection = YogaFlexDirection.valueOf(s.toUpperCase(Locale.US).replace("-", "_"));
        }
        this.setFlexDirection(flexDirection);
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "flexGrow")
    @Override
    public void setFlexGrow(final float flexGrow) {
        if (this.isVirtual()) {
            return;
        }
        super.setFlexGrow(flexGrow);
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "flexShrink")
    @Override
    public void setFlexShrink(final float flexShrink) {
        if (this.isVirtual()) {
            return;
        }
        super.setFlexShrink(flexShrink);
    }
    
    @ReactProp(name = "flexWrap")
    public void setFlexWrap(final String s) {
        if (this.isVirtual()) {
            return;
        }
        if (s == null || s.equals("nowrap")) {
            this.setFlexWrap(YogaWrap.NO_WRAP);
            return;
        }
        if (s.equals("wrap")) {
            this.setFlexWrap(YogaWrap.WRAP);
            return;
        }
        throw new IllegalArgumentException("Unknown flexWrap value: " + s);
    }
    
    @ReactProp(name = "height")
    public void setHeight(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleHeightPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleHeight(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "justifyContent")
    public void setJustifyContent(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaJustify justifyContent;
        if (s == null) {
            justifyContent = YogaJustify.FLEX_START;
        }
        else {
            justifyContent = YogaJustify.valueOf(s.toUpperCase(Locale.US).replace("-", "_"));
        }
        this.setJustifyContent(justifyContent);
    }
    
    @ReactPropGroup(names = { "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom" })
    public void setMargins(int n, final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setMarginPercent(ViewProps.PADDING_MARGIN_SPACING_TYPES[n], getDynamicAsPercent(dynamic));
        }
        else {
            n = ViewProps.PADDING_MARGIN_SPACING_TYPES[n];
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setMargin(n, dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "maxHeight")
    public void setMaxHeight(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleMaxHeightPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleMaxHeight(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "maxWidth")
    public void setMaxWidth(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleMaxWidthPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleMaxWidth(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "minHeight")
    public void setMinHeight(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleMinHeightPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleMinHeight(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "minWidth")
    public void setMinWidth(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleMinWidthPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleMinWidth(dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "overflow")
    public void setOverflow(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaOverflow overflow;
        if (s == null) {
            overflow = YogaOverflow.VISIBLE;
        }
        else {
            overflow = YogaOverflow.valueOf(s.toUpperCase(Locale.US).replace("-", "_"));
        }
        this.setOverflow(overflow);
    }
    
    @ReactPropGroup(names = { "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom" })
    public void setPaddings(int n, final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setPaddingPercent(ViewProps.PADDING_MARGIN_SPACING_TYPES[n], getDynamicAsPercent(dynamic));
        }
        else {
            n = ViewProps.PADDING_MARGIN_SPACING_TYPES[n];
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setPadding(n, dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "position")
    public void setPosition(final String s) {
        if (this.isVirtual()) {
            return;
        }
        YogaPositionType positionType;
        if (s == null) {
            positionType = YogaPositionType.RELATIVE;
        }
        else {
            positionType = YogaPositionType.valueOf(s.toUpperCase(Locale.US));
        }
        this.setPositionType(positionType);
    }
    
    @ReactPropGroup(names = { "left", "right", "top", "bottom" })
    public void setPositionValues(int n, final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setPositionPercent(ViewProps.POSITION_SPACING_TYPES[n], getDynamicAsPercent(dynamic));
        }
        else {
            n = ViewProps.POSITION_SPACING_TYPES[n];
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setPosition(n, dynamicAsFloat);
        }
        dynamic.recycle();
    }
    
    @ReactProp(name = "onLayout")
    @Override
    public void setShouldNotifyOnLayout(final boolean shouldNotifyOnLayout) {
        super.setShouldNotifyOnLayout(shouldNotifyOnLayout);
    }
    
    @ReactProp(name = "width")
    public void setWidth(final Dynamic dynamic) {
        if (this.isVirtual()) {
            return;
        }
        if (!isNull(dynamic) && dynamicIsPercent(dynamic)) {
            this.setStyleWidthPercent(getDynamicAsPercent(dynamic));
        }
        else {
            float dynamicAsFloat;
            if (isNull(dynamic)) {
                dynamicAsFloat = Float.NaN;
            }
            else {
                dynamicAsFloat = getDynamicAsFloat(dynamic);
            }
            this.setStyleWidth(dynamicAsFloat);
        }
        dynamic.recycle();
    }
}
