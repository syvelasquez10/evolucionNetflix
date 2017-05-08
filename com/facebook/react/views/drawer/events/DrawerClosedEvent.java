// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.drawer.events;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.Event;

public class DrawerClosedEvent extends Event<DrawerClosedEvent>
{
    public DrawerClosedEvent(final int n) {
        super(n);
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), Arguments.createMap());
    }
    
    @Override
    public short getCoalescingKey() {
        return 0;
    }
    
    @Override
    public String getEventName() {
        return "topDrawerClosed";
    }
}
