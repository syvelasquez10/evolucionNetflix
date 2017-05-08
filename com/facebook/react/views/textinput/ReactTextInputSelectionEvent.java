// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class ReactTextInputSelectionEvent extends Event<ReactTextInputSelectionEvent>
{
    private int mSelectionEnd;
    private int mSelectionStart;
    
    public ReactTextInputSelectionEvent(final int n, final int mSelectionStart, final int mSelectionEnd) {
        super(n);
        this.mSelectionStart = mSelectionStart;
        this.mSelectionEnd = mSelectionEnd;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        final WritableMap map2 = Arguments.createMap();
        map2.putInt("end", this.mSelectionEnd);
        map2.putInt("start", this.mSelectionStart);
        map.putMap("selection", map2);
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public String getEventName() {
        return "topSelectionChange";
    }
}
