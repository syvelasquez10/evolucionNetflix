// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaWrap;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Locale;
import com.facebook.yoga.YogaAlign;

public class LayoutShadowNode extends ReactShadowNode
{
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
    
    @ReactProp(defaultFloat = 0.0f, name = "flexBasis")
    @Override
    public void setFlexBasis(final float flexBasis) {
        if (this.isVirtual()) {
            return;
        }
        super.setFlexBasis(flexBasis);
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
    
    @ReactProp(defaultFloat = Float.NaN, name = "height")
    public void setHeight(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleHeight(pixelFromDIP);
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
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "margin", "marginVertical", "marginHorizontal", "marginLeft", "marginRight", "marginTop", "marginBottom" })
    public void setMargins(final int n, final float n2) {
        if (this.isVirtual()) {
            return;
        }
        this.setMargin(ViewProps.PADDING_MARGIN_SPACING_TYPES[n], PixelUtil.toPixelFromDIP(n2));
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "maxHeight")
    public void setMaxHeight(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleMaxHeight(pixelFromDIP);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "maxWidth")
    public void setMaxWidth(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleMaxWidth(pixelFromDIP);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "minHeight")
    public void setMinHeight(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleMinHeight(pixelFromDIP);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "minWidth")
    public void setMinWidth(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleMinWidth(pixelFromDIP);
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
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "padding", "paddingVertical", "paddingHorizontal", "paddingLeft", "paddingRight", "paddingTop", "paddingBottom" })
    public void setPaddings(int n, float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        n = ViewProps.PADDING_MARGIN_SPACING_TYPES[n];
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setPadding(n, pixelFromDIP);
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
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "left", "right", "top", "bottom" })
    public void setPositionValues(int n, float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        n = ViewProps.POSITION_SPACING_TYPES[n];
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setPosition(n, pixelFromDIP);
    }
    
    @ReactProp(name = "onLayout")
    @Override
    public void setShouldNotifyOnLayout(final boolean shouldNotifyOnLayout) {
        super.setShouldNotifyOnLayout(shouldNotifyOnLayout);
    }
    
    @ReactProp(defaultFloat = Float.NaN, name = "width")
    public void setWidth(float pixelFromDIP) {
        if (this.isVirtual()) {
            return;
        }
        if (!YogaConstants.isUndefined(pixelFromDIP)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(pixelFromDIP);
        }
        this.setStyleWidth(pixelFromDIP);
    }
}
