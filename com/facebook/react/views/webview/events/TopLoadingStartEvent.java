// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.webview.events;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class TopLoadingStartEvent extends Event<TopLoadingStartEvent>
{
    private WritableMap mEventData;
    
    public TopLoadingStartEvent(final int n, final WritableMap mEventData) {
        super(n);
        this.mEventData = mEventData;
    }
    
    @Override
    public boolean canCoalesce() {
        return false;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.mEventData);
    }
    
    @Override
    public short getCoalescingKey() {
        return 0;
    }
    
    @Override
    public String getEventName() {
        return "topLoadingStart";
    }
}
