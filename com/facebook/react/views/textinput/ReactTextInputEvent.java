// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.textinput;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

public class ReactTextInputEvent extends Event<ReactTextInputEvent>
{
    private String mPreviousText;
    private int mRangeEnd;
    private int mRangeStart;
    private String mText;
    
    public ReactTextInputEvent(final int n, final String mText, final String mPreviousText, final int mRangeStart, final int mRangeEnd) {
        super(n);
        this.mText = mText;
        this.mPreviousText = mPreviousText;
        this.mRangeStart = mRangeStart;
        this.mRangeEnd = mRangeEnd;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        final WritableMap map2 = Arguments.createMap();
        map2.putDouble("start", this.mRangeStart);
        map2.putDouble("end", this.mRangeEnd);
        map.putString("text", this.mText);
        map.putString("previousText", this.mPreviousText);
        map.putMap("range", map2);
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
        return "topTextInput";
    }
}
