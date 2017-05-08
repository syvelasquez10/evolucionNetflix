// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class PageScrollStateChangedEvent extends Event<PageScrollStateChangedEvent>
{
    private final String mPageScrollState;
    
    protected PageScrollStateChangedEvent(final int n, final String mPageScrollState) {
        super(n);
        this.mPageScrollState = mPageScrollState;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putString("pageScrollState", this.mPageScrollState);
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public String getEventName() {
        return "topPageScrollStateChanged";
    }
}
