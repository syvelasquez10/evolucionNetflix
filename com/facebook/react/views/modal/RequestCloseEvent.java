// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.modal;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.Event;

class RequestCloseEvent extends Event<RequestCloseEvent>
{
    protected RequestCloseEvent(final int n) {
        super(n);
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), null);
    }
    
    @Override
    public String getEventName() {
        return "topRequestClose";
    }
}
