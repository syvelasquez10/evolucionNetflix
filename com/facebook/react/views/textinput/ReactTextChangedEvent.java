// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class ReactTextChangedEvent extends Event<ReactTextChangedEvent>
{
    private float mContentHeight;
    private float mContentWidth;
    private int mEventCount;
    private String mText;
    
    public ReactTextChangedEvent(final int n, final String mText, final float mContentWidth, final float mContentHeight, final int mEventCount) {
        super(n);
        this.mText = mText;
        this.mContentWidth = mContentWidth;
        this.mContentHeight = mContentHeight;
        this.mEventCount = mEventCount;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putString("text", this.mText);
        final WritableMap map2 = Arguments.createMap();
        map2.putDouble("width", this.mContentWidth);
        map2.putDouble("height", this.mContentHeight);
        map.putMap("contentSize", map2);
        map.putInt("eventCount", this.mEventCount);
        map.putInt("target", this.getViewTag());
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public String getEventName() {
        return "topChange";
    }
}
