// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableMap;
import android.view.MotionEvent;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;

class TouchesHelper
{
    private static WritableArray createsPointersArray(final int n, final TouchEvent touchEvent) {
        final WritableArray array = Arguments.createArray();
        final MotionEvent motionEvent = touchEvent.getMotionEvent();
        final float x = motionEvent.getX();
        final float viewX = touchEvent.getViewX();
        final float y = motionEvent.getY();
        final float viewY = touchEvent.getViewY();
        for (int i = 0; i < motionEvent.getPointerCount(); ++i) {
            final WritableMap map = Arguments.createMap();
            map.putDouble("pageX", PixelUtil.toDIPFromPixel(motionEvent.getX(i)));
            map.putDouble("pageY", PixelUtil.toDIPFromPixel(motionEvent.getY(i)));
            final float x2 = motionEvent.getX(i);
            final float y2 = motionEvent.getY(i);
            map.putDouble("locationX", PixelUtil.toDIPFromPixel(x2 - (x - viewX)));
            map.putDouble("locationY", PixelUtil.toDIPFromPixel(y2 - (y - viewY)));
            map.putInt("target", n);
            map.putDouble("timestamp", touchEvent.getTimestampMs());
            map.putDouble("identifier", motionEvent.getPointerId(i));
            array.pushMap(map);
        }
        return array;
    }
    
    public static void sendTouchEvent(final RCTEventEmitter rctEventEmitter, final TouchEventType touchEventType, int i, final TouchEvent touchEvent) {
        final WritableArray createsPointersArray = createsPointersArray(i, touchEvent);
        final MotionEvent motionEvent = touchEvent.getMotionEvent();
        final WritableArray array = Arguments.createArray();
        if (touchEventType == TouchEventType.MOVE || touchEventType == TouchEventType.CANCEL) {
            for (i = 0; i < motionEvent.getPointerCount(); ++i) {
                array.pushInt(i);
            }
        }
        else {
            if (touchEventType != TouchEventType.START && touchEventType != TouchEventType.END) {
                throw new RuntimeException("Unknown touch type: " + touchEventType);
            }
            array.pushInt(motionEvent.getActionIndex());
        }
        rctEventEmitter.receiveTouches(touchEventType.getJSEventName(), createsPointersArray, array);
    }
}
