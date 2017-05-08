// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputBlurEvent extends Event<ReactTextInputBlurEvent>
{
    public ReactTextInputBlurEvent(final int n) {
        super(n);
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("target", this.getViewTag());
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
    public String getEventName() {
        return "topBlur";
    }
}
