// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.Arguments;

public class ContentSizeChangeEvent extends Event<ContentSizeChangeEvent>
{
    private final int mHeight;
    private final int mWidth;
    
    public ContentSizeChangeEvent(final int n, final int mWidth, final int mHeight) {
        super(n);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        final WritableMap map = Arguments.createMap();
        map.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        map.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        rctEventEmitter.receiveEvent(this.getViewTag(), "topContentSizeChange", map);
    }
    
    @Override
    public String getEventName() {
        return "topContentSizeChange";
    }
}
