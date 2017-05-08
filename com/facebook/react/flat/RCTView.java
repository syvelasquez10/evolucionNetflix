// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import android.graphics.Rect;

final class RCTView extends FlatShadowNode
{
    private static final int[] SPACING_TYPES;
    private DrawBorder mDrawBorder;
    private Rect mHitSlop;
    
    static {
        SPACING_TYPES = new int[] { 8, 0, 2, 1, 3 };
    }
    
    private DrawBorder getMutableBorder() {
        if (this.mDrawBorder == null) {
            this.mDrawBorder = new DrawBorder();
        }
        else if (this.mDrawBorder.isFrozen()) {
            this.mDrawBorder = (DrawBorder)this.mDrawBorder.mutableCopy();
        }
        this.invalidate();
        return this.mDrawBorder;
    }
    
    @Override
    public void setBackgroundColor(final int backgroundColor) {
        this.getMutableBorder().setBackgroundColor(backgroundColor);
    }
    
    @ReactPropGroup(customType = "Color", defaultDouble = Double.NaN, names = { "borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor" })
    public void setBorderColor(int n, final double n2) {
        n = RCTView.SPACING_TYPES[n];
        if (Double.isNaN(n2)) {
            this.getMutableBorder().resetBorderColor(n);
            return;
        }
        this.getMutableBorder().setBorderColor(n, (int)n2);
    }
    
    @ReactProp(name = "borderRadius")
    public void setBorderRadius(final float mClipRadius) {
        this.mClipRadius = mClipRadius;
        if (this.mClipToBounds && mClipRadius > 0.5f) {
            this.forceMountToView();
        }
        this.getMutableBorder().setBorderRadius(PixelUtil.toPixelFromDIP(mClipRadius));
    }
    
    @ReactProp(name = "borderStyle")
    public void setBorderStyle(final String borderStyle) {
        this.getMutableBorder().setBorderStyle(borderStyle);
    }
    
    @Override
    public void setBorderWidths(int n, final float n2) {
        super.setBorderWidths(n, n2);
        n = RCTView.SPACING_TYPES[n];
        this.getMutableBorder().setBorderWidth(n, PixelUtil.toPixelFromDIP(n2));
    }
    
    @ReactProp(name = "hitSlop")
    public void setHitSlop(final ReadableMap readableMap) {
        if (readableMap == null) {
            this.mHitSlop = null;
            return;
        }
        this.mHitSlop = new Rect((int)PixelUtil.toPixelFromDIP(readableMap.getDouble("left")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("top")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("right")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("bottom")));
    }
    
    @ReactProp(name = "nativeBackgroundAndroid")
    public void setHotspot(final ReadableMap readableMap) {
        if (readableMap != null) {
            this.forceMountToView();
        }
    }
    
    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(final String s) {
        this.forceMountToView();
    }
}
