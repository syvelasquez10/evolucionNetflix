// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer.events;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class DrawerStateChangedEvent extends Event<DrawerStateChangedEvent>
{
    private final int mDrawerState;
    
    public DrawerStateChangedEvent(final int n, final int mDrawerState) {
        super(n);
        this.mDrawerState = mDrawerState;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putDouble("drawerState", this.getDrawerState());
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
    
    public int getDrawerState() {
        return this.mDrawerState;
    }
    
    @Override
    public String getEventName() {
        return "topDrawerStateChanged";
    }
}
