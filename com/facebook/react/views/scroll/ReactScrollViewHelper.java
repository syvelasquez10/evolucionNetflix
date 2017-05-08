// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.view.View;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import android.view.ViewGroup;

public class ReactScrollViewHelper
{
    public static void emitScrollBeginDragEvent(final ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.BEGIN_DRAG);
    }
    
    public static void emitScrollEndDragEvent(final ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.END_DRAG);
    }
    
    public static void emitScrollEvent(final ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.SCROLL);
    }
    
    private static void emitScrollEvent(final ViewGroup viewGroup, final ScrollEventType scrollEventType) {
        final View child = viewGroup.getChildAt(0);
        if (child == null) {
            return;
        }
        ((ReactContext)viewGroup.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(ScrollEvent.obtain(viewGroup.getId(), scrollEventType, viewGroup.getScrollX(), viewGroup.getScrollY(), child.getWidth(), child.getHeight(), viewGroup.getWidth(), viewGroup.getHeight()));
    }
    
    public static void emitScrollMomentumBeginEvent(final ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.MOMENTUM_BEGIN);
    }
    
    public static void emitScrollMomentumEndEvent(final ViewGroup viewGroup) {
        emitScrollEvent(viewGroup, ScrollEventType.MOMENTUM_END);
    }
    
    public static int parseOverScrollMode(final String s) {
        if (s == null || s.equals("auto")) {
            return 1;
        }
        if (s.equals("always")) {
            return 0;
        }
        if (s.equals("never")) {
            return 2;
        }
        throw new JSApplicationIllegalArgumentException("wrong overScrollMode: " + s);
    }
}
