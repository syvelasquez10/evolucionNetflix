// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import java.util.Locale;
import com.facebook.react.uimanager.PointerEvents;
import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.yoga.YogaConstants;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.PixelUtil;
import android.os.Build$VERSION;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import android.content.Context;
import com.facebook.react.uimanager.ThemedReactContext;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewGroupManager;

public class ReactViewManager extends ViewGroupManager<ReactViewGroup>
{
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES;
    
    static {
        SPACING_TYPES = new int[] { 8, 0, 2, 1, 3 };
    }
    
    @Override
    public void addView(final ReactViewGroup reactViewGroup, final View view, final int n) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            reactViewGroup.addViewWithSubviewClippingEnabled(view, n);
        }
        else {
            reactViewGroup.addView(view, n);
        }
        ViewGroupManager.reorderChildrenByZIndex(reactViewGroup);
    }
    
    public ReactViewGroup createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactViewGroup((Context)themedReactContext);
    }
    
    @Override
    public View getChildAt(final ReactViewGroup reactViewGroup, final int n) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            return reactViewGroup.getChildAtWithSubviewClippingEnabled(n);
        }
        return reactViewGroup.getChildAt(n);
    }
    
    @Override
    public int getChildCount(final ReactViewGroup reactViewGroup) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            return reactViewGroup.getAllChildrenCount();
        }
        return reactViewGroup.getChildCount();
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("hotspotUpdate", 1, "setPressed", 2);
    }
    
    @Override
    public String getName() {
        return "RCTView";
    }
    
    public void receiveCommand(final ReactViewGroup reactViewGroup, final int n, final ReadableArray readableArray) {
        switch (n) {
            case 1: {
                if (readableArray == null || readableArray.size() != 2) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
                }
                if (Build$VERSION.SDK_INT >= 21) {
                    reactViewGroup.drawableHotspotChanged(PixelUtil.toPixelFromDIP(readableArray.getDouble(0)), PixelUtil.toPixelFromDIP(readableArray.getDouble(1)));
                    return;
                }
                break;
            }
            case 2: {
                if (readableArray == null || readableArray.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                reactViewGroup.setPressed(readableArray.getBoolean(0));
            }
        }
    }
    
    @Override
    public void removeAllViews(final ReactViewGroup reactViewGroup) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            reactViewGroup.removeAllViewsWithSubviewClippingEnabled();
            return;
        }
        reactViewGroup.removeAllViews();
    }
    
    @Override
    public void removeViewAt(final ReactViewGroup reactViewGroup, final int n) {
        if (reactViewGroup.getRemoveClippedSubviews()) {
            final View child = this.getChildAt(reactViewGroup, n);
            if (child.getParent() != null) {
                reactViewGroup.removeView(child);
            }
            reactViewGroup.removeViewWithSubviewClippingEnabled(child);
            return;
        }
        reactViewGroup.removeViewAt(n);
    }
    
    @ReactProp(name = "accessible")
    public void setAccessible(final ReactViewGroup reactViewGroup, final boolean focusable) {
        reactViewGroup.setFocusable(focusable);
    }
    
    @ReactPropGroup(customType = "Color", names = { "borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor" })
    public void setBorderColor(final ReactViewGroup reactViewGroup, final int n, final Integer n2) {
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
        reactViewGroup.setBorderColor(ReactViewManager.SPACING_TYPES[n], n4, n3);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius" })
    public void setBorderRadius(final ReactViewGroup reactViewGroup, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        if (n == 0) {
            reactViewGroup.setBorderRadius(pixelFromDIP);
            return;
        }
        reactViewGroup.setBorderRadius(pixelFromDIP, n - 1);
    }
    
    @ReactProp(name = "borderStyle")
    public void setBorderStyle(final ReactViewGroup reactViewGroup, final String borderStyle) {
        reactViewGroup.setBorderStyle(borderStyle);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth" })
    public void setBorderWidth(final ReactViewGroup reactViewGroup, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        reactViewGroup.setBorderWidth(ReactViewManager.SPACING_TYPES[n], pixelFromDIP);
    }
    
    @ReactProp(name = "collapsable")
    public void setCollapsable(final ReactViewGroup reactViewGroup, final boolean b) {
    }
    
    @ReactProp(name = "hitSlop")
    public void setHitSlop(final ReactViewGroup reactViewGroup, final ReadableMap readableMap) {
        int n = 0;
        if (readableMap == null) {
            reactViewGroup.setHitSlopRect(null);
            return;
        }
        int n2;
        if (readableMap.hasKey("left")) {
            n2 = (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("left"));
        }
        else {
            n2 = 0;
        }
        int n3;
        if (readableMap.hasKey("top")) {
            n3 = (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("top"));
        }
        else {
            n3 = 0;
        }
        int n4;
        if (readableMap.hasKey("right")) {
            n4 = (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("right"));
        }
        else {
            n4 = 0;
        }
        if (readableMap.hasKey("bottom")) {
            n = (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("bottom"));
        }
        reactViewGroup.setHitSlopRect(new Rect(n2, n3, n4, n));
    }
    
    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(final ReactViewGroup reactViewGroup, final ReadableMap readableMap) {
        Drawable drawableFromJSDescription;
        if (readableMap == null) {
            drawableFromJSDescription = null;
        }
        else {
            drawableFromJSDescription = ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap);
        }
        reactViewGroup.setTranslucentBackgroundDrawable(drawableFromJSDescription);
    }
    
    @ReactProp(name = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(final ReactViewGroup reactViewGroup, final ReadableMap readableMap) {
        Drawable drawableFromJSDescription;
        if (readableMap == null) {
            drawableFromJSDescription = null;
        }
        else {
            drawableFromJSDescription = ReactDrawableHelper.createDrawableFromJSDescription(reactViewGroup.getContext(), readableMap);
        }
        reactViewGroup.setForeground(drawableFromJSDescription);
    }
    
    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(final ReactViewGroup reactViewGroup, final boolean needsOffscreenAlphaCompositing) {
        reactViewGroup.setNeedsOffscreenAlphaCompositing(needsOffscreenAlphaCompositing);
    }
    
    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(final ReactViewGroup reactViewGroup, final String s) {
        if (s == null) {
            reactViewGroup.setPointerEvents(PointerEvents.AUTO);
            return;
        }
        reactViewGroup.setPointerEvents(PointerEvents.valueOf(s.toUpperCase(Locale.US).replace("-", "_")));
    }
    
    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(final ReactViewGroup reactViewGroup, final boolean removeClippedSubviews) {
        reactViewGroup.setRemoveClippedSubviews(removeClippedSubviews);
    }
}
