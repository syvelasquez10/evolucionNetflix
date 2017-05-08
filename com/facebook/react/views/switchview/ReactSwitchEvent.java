// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.switchview;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactSwitchEvent extends Event<ReactSwitchEvent>
{
    private final boolean mIsChecked;
    
    public ReactSwitchEvent(final int n, final boolean mIsChecked) {
        super(n);
        this.mIsChecked = mIsChecked;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("target", this.getViewTag());
        map.putBoolean("value", this.getIsChecked());
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
    
    public boolean getIsChecked() {
        return this.mIsChecked;
    }
}
