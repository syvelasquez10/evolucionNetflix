// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class PageSelectedEvent extends Event<PageSelectedEvent>
{
    private final int mPosition;
    
    protected PageSelectedEvent(final int n, final int mPosition) {
        super(n);
        this.mPosition = mPosition;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("position", this.mPosition);
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public String getEventName() {
        return "topPageSelected";
    }
}
