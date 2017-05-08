// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.react.uimanager.RootViewUtil;
import android.view.MotionEvent;
import android.view.View;

public class NativeGestureUtil
{
    public static void notifyNativeGestureStarted(final View view, final MotionEvent motionEvent) {
        RootViewUtil.getRootView(view).onChildStartedNativeGesture(motionEvent);
    }
}
