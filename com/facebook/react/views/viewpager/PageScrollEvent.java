// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.viewpager;

import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;

class PageScrollEvent extends Event<PageScrollEvent>
{
    private final float mOffset;
    private final int mPosition;
    
    protected PageScrollEvent(final int n, final int mPosition, final float n2) {
        super(n);
        this.mPosition = mPosition;
        float mOffset = 0.0f;
        Label_0030: {
            if (!Float.isInfinite(n2)) {
                mOffset = n2;
                if (!Float.isNaN(n2)) {
                    break Label_0030;
                }
            }
            mOffset = 0.0f;
        }
        this.mOffset = mOffset;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putInt("position", this.mPosition);
        map.putDouble("offset", this.mOffset);
        return map;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public String getEventName() {
        return "topPageScroll";
    }
}
