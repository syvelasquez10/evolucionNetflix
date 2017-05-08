// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.slider;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class ReactSliderEvent extends Event<ReactSliderEvent>
{
    private final boolean mFromUser;
    private final double mValue;
    
    public ReactSliderEvent(final int n, final double mValue, final boolean mFromUser) {
        super(n);
        this.mValue = mValue;
        this.mFromUser = mFromUser;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("target", this.getViewTag());
        map.putDouble("value", this.getValue());
        map.putBoolean("fromUser", this.isFromUser());
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
        return "topChange";
    }
    
    public double getValue() {
        return this.mValue;
    }
    
    public boolean isFromUser() {
        return this.mFromUser;
    }
}
