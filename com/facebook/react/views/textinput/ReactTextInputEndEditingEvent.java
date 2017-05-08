// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputEndEditingEvent extends Event<ReactTextInputEndEditingEvent>
{
    private String mText;
    
    public ReactTextInputEndEditingEvent(final int n, final String mText) {
        super(n);
        this.mText = mText;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("target", this.getViewTag());
        map.putString("text", this.mText);
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
        return "topEndEditing";
    }
}
