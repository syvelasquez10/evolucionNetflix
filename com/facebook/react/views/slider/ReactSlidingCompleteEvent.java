// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class ReactSlidingCompleteEvent extends Event<ReactSlidingCompleteEvent>
{
    private final double mValue;
    
    public ReactSlidingCompleteEvent(final int n, final double mValue) {
        super(n);
        this.mValue = mValue;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("target", this.getViewTag());
        map.putDouble("value", this.getValue());
        return map;
    }
    
    @Override
    public boolean canCoalesce() {
        return false;
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
        return "topSlidingComplete";
    }
    
    public double getValue() {
        return this.mValue;
    }
}
