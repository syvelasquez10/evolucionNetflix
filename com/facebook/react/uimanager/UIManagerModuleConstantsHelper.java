// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import java.util.HashMap;
import java.util.Iterator;
import com.facebook.systrace.Systrace;
import com.facebook.react.common.MapBuilder;
import com.facebook.systrace.SystraceMessage;
import java.util.Map;
import java.util.List;

class UIManagerModuleConstantsHelper
{
    static Map<String, Object> createConstants(final List<ViewManager> list, final boolean b) {
        final Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        final Map bubblingEventTypeConstants = UIManagerModuleConstants.getBubblingEventTypeConstants();
        final Map directEventTypeConstants = UIManagerModuleConstants.getDirectEventTypeConstants();
        for (final ViewManager viewManager : list) {
            SystraceMessage.beginSection(0L, "constants for ViewManager").arg("ViewManager", viewManager.getName()).flush();
            try {
                final Map exportedCustomBubblingEventTypeConstants = viewManager.getExportedCustomBubblingEventTypeConstants();
                if (exportedCustomBubblingEventTypeConstants != null) {
                    recursiveMerge(bubblingEventTypeConstants, exportedCustomBubblingEventTypeConstants);
                }
                final Map exportedCustomDirectEventTypeConstants = viewManager.getExportedCustomDirectEventTypeConstants();
                if (exportedCustomDirectEventTypeConstants != null) {
                    recursiveMerge(directEventTypeConstants, exportedCustomDirectEventTypeConstants);
                }
                final HashMap<Object, Object> hashMap = MapBuilder.newHashMap();
                final Map exportedViewConstants = viewManager.getExportedViewConstants();
                if (exportedViewConstants != null) {
                    hashMap.put("Constants", exportedViewConstants);
                }
                final Map commandsMap = viewManager.getCommandsMap();
                if (commandsMap != null) {
                    hashMap.put("Commands", commandsMap);
                }
                final Map nativeProps = viewManager.getNativeProps();
                if (!nativeProps.isEmpty()) {
                    hashMap.put("NativeProps", nativeProps);
                }
                if (hashMap.isEmpty()) {
                    continue;
                }
                constants.put(viewManager.getName(), hashMap);
                continue;
            }
            finally {
                Systrace.endSection(0L);
            }
            break;
        }
        constants.put("customBubblingEventTypes", bubblingEventTypeConstants);
        constants.put("customDirectEventTypes", directEventTypeConstants);
        constants.put("AndroidLazyViewManagersEnabled", b);
        return constants;
    }
    
    private static void recursiveMerge(final Map map, final Map map2) {
        for (final Object next : map2.keySet()) {
            final V value = map2.get(next);
            final Map value2 = map.get(next);
            if (value2 != null && value instanceof Map && value2 instanceof Map) {
                recursiveMerge(value2, (Map)value);
            }
            else {
                map.put(next, value);
            }
        }
    }
}
