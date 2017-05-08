// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.Event;

public class ToolbarClickEvent extends Event<ToolbarClickEvent>
{
    private final int position;
    
    public ToolbarClickEvent(final int n, final int position) {
        super(n);
        this.position = position;
    }
    
    @Override
    public boolean canCoalesce() {
        return false;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        final WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putInt("position", this.getPosition());
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), writableNativeMap);
    }
    
    @Override
    public String getEventName() {
        return "topSelect";
    }
    
    public int getPosition() {
        return this.position;
    }
}
