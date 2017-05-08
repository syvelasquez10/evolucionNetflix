// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer.events;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class DrawerSlideEvent extends Event<DrawerSlideEvent>
{
    private final float mOffset;
    
    public DrawerSlideEvent(final int n, final float mOffset) {
        super(n);
        this.mOffset = mOffset;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putDouble("offset", this.getOffset());
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public short getCoalescingKey() {
        return 0;
    }
    
    @Override
    public String getEventName() {
        return "topDrawerSlide";
    }
    
    public float getOffset() {
        return this.mOffset;
    }
}
