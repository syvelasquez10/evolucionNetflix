// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.animated;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.List;
import com.facebook.react.uimanager.events.RCTEventEmitter;

class EventAnimationDriver implements RCTEventEmitter
{
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;
    
    public EventAnimationDriver(final List<String> mEventPath, final ValueAnimatedNode mValueNode) {
        this.mEventPath = mEventPath;
        this.mValueNode = mValueNode;
    }
    
    @Override
    public void receiveEvent(int i, final String s, WritableMap map) {
        if (map == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        for (i = 0; i < this.mEventPath.size() - 1; ++i) {
            map = (WritableMap)map.getMap(this.mEventPath.get(i));
        }
        this.mValueNode.mValue = map.getDouble(this.mEventPath.get(this.mEventPath.size() - 1));
    }
    
    @Override
    public void receiveTouches(final String s, final WritableArray writableArray, final WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
