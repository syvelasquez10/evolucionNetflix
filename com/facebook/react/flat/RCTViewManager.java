// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import android.graphics.drawable.Drawable;
import com.facebook.react.views.view.ReactDrawableHelper;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.graphics.Rect;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import android.os.Build$VERSION;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import android.view.View;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PointerEvents;

public final class RCTViewManager extends FlatViewManager
{
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    private static final int[] TMP_INT_ARRAY;
    
    static {
        TMP_INT_ARRAY = new int[2];
    }
    
    private static PointerEvents parsePointerEvents(final String s) {
        if (s != null) {
            switch (s) {
                case "none": {
                    return PointerEvents.NONE;
                }
                case "auto": {
                    return PointerEvents.AUTO;
                }
                case "box-none": {
                    return PointerEvents.BOX_NONE;
                }
                case "box-only": {
                    return PointerEvents.BOX_ONLY;
                }
            }
        }
        return PointerEvents.AUTO;
    }
    
    @Override
    public RCTView createShadowNodeInstance() {
        return new RCTView();
    }
    
    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("hotspotUpdate", 1, "setPressed", 2);
    }
    
    @Override
    public String getName() {
        return "RCTView";
    }
    
    @Override
    public Class<RCTView> getShadowNodeClass() {
        return RCTView.class;
    }
    
    public void receiveCommand(final FlatViewGroup flatViewGroup, final int n, final ReadableArray readableArray) {
        switch (n) {
            case 1: {
                if (readableArray == null || readableArray.size() != 2) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
                }
                if (Build$VERSION.SDK_INT >= 21) {
                    flatViewGroup.getLocationOnScreen(RCTViewManager.TMP_INT_ARRAY);
                    flatViewGroup.drawableHotspotChanged(PixelUtil.toPixelFromDIP(readableArray.getDouble(0)) - RCTViewManager.TMP_INT_ARRAY[0], PixelUtil.toPixelFromDIP(readableArray.getDouble(1)) - RCTViewManager.TMP_INT_ARRAY[1]);
                    return;
                }
                break;
            }
            case 2: {
                if (readableArray == null || readableArray.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                flatViewGroup.setPressed(readableArray.getBoolean(0));
            }
        }
    }
    
    @ReactProp(name = "hitSlop")
    public void setHitSlop(final FlatViewGroup flatViewGroup, final ReadableMap readableMap) {
        if (readableMap == null) {
            flatViewGroup.setHitSlopRect(null);
            return;
        }
        flatViewGroup.setHitSlopRect(new Rect((int)PixelUtil.toPixelFromDIP(readableMap.getDouble("left")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("top")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("right")), (int)PixelUtil.toPixelFromDIP(readableMap.getDouble("bottom"))));
    }
    
    @ReactProp(name = "nativeBackgroundAndroid")
    public void setHotspot(final FlatViewGroup flatViewGroup, final ReadableMap readableMap) {
        Drawable drawableFromJSDescription;
        if (readableMap == null) {
            drawableFromJSDescription = null;
        }
        else {
            drawableFromJSDescription = ReactDrawableHelper.createDrawableFromJSDescription(flatViewGroup.getContext(), readableMap);
        }
        flatViewGroup.setHotspot(drawableFromJSDescription);
    }
    
    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(final FlatViewGroup flatViewGroup, final boolean needsOffscreenAlphaCompositing) {
        flatViewGroup.setNeedsOffscreenAlphaCompositing(needsOffscreenAlphaCompositing);
    }
    
    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(final FlatViewGroup flatViewGroup, final String s) {
        flatViewGroup.setPointerEvents(parsePointerEvents(s));
    }
    
    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(final FlatViewGroup flatViewGroup, final boolean removeClippedSubviews) {
        flatViewGroup.setRemoveClippedSubviews(removeClippedSubviews);
    }
}
