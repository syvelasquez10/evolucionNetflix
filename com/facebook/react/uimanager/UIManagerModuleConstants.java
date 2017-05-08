// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.widget.ImageView$ScaleType;
import java.util.HashMap;
import com.facebook.react.uimanager.events.TouchEventType;
import com.facebook.react.common.MapBuilder;
import java.util.Map;

class UIManagerModuleConstants
{
    static Map getBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topChange", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onChange", "captured", "onChangeCapture"))).put("topSelect", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(TouchEventType.START.getJSEventName(), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).put(TouchEventType.MOVE.getJSEventName(), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).put(TouchEventType.END.getJSEventName(), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).build();
    }
    
    public static Map<String, Object> getConstants() {
        final HashMap<String, Map<String, Map<String, Integer>>> hashMap = (HashMap<String, Map<String, Map<String, Integer>>>)new HashMap<String, Object>();
        hashMap.put("UIView", MapBuilder.of("ContentMode", MapBuilder.of("ScaleAspectFit", ImageView$ScaleType.FIT_CENTER.ordinal(), "ScaleAspectFill", ImageView$ScaleType.CENTER_CROP.ordinal(), "ScaleAspectCenter", ImageView$ScaleType.CENTER_INSIDE.ordinal())));
        final DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        final DisplayMetrics screenDisplayMetrics = DisplayMetricsHolder.getScreenDisplayMetrics();
        hashMap.put("Dimensions", MapBuilder.of("windowPhysicalPixels", MapBuilder.of("width", windowDisplayMetrics.widthPixels, "height", windowDisplayMetrics.heightPixels, "scale", windowDisplayMetrics.density, "fontScale", windowDisplayMetrics.scaledDensity, "densityDpi", windowDisplayMetrics.densityDpi), "screenPhysicalPixels", MapBuilder.of("width", screenDisplayMetrics.widthPixels, "height", screenDisplayMetrics.heightPixels, "scale", screenDisplayMetrics.density, "fontScale", screenDisplayMetrics.scaledDensity, "densityDpi", screenDisplayMetrics.densityDpi)));
        hashMap.put("StyleConstants", MapBuilder.of("PointerEventsValues", MapBuilder.of("none", PointerEvents.NONE.ordinal(), "boxNone", PointerEvents.BOX_NONE.ordinal(), "boxOnly", PointerEvents.BOX_ONLY.ordinal(), "unspecified", PointerEvents.AUTO.ordinal())));
        hashMap.put("PopupMenu", MapBuilder.of("dismissed", "dismissed", "itemSelected", (Map<String, Integer>)"itemSelected"));
        hashMap.put("AccessibilityEventTypes", MapBuilder.of("typeWindowStateChanged", 32, "typeViewClicked", (Map<String, Integer>)1));
        return (Map<String, Object>)hashMap;
    }
    
    static Map getDirectEventTypeConstants() {
        return MapBuilder.builder().put("topContentSizeChange", MapBuilder.of("registrationName", "onContentSizeChange")).put("topLayout", MapBuilder.of("registrationName", "onLayout")).put("topLoadingError", MapBuilder.of("registrationName", "onLoadingError")).put("topLoadingFinish", MapBuilder.of("registrationName", "onLoadingFinish")).put("topLoadingStart", MapBuilder.of("registrationName", "onLoadingStart")).put("topSelectionChange", MapBuilder.of("registrationName", "onSelectionChange")).put("topMessage", MapBuilder.of("registrationName", "onMessage")).build();
    }
}
