// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.image;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.events.Event;

public class ImageLoadEvent extends Event<ImageLoadEvent>
{
    private final int mEventType;
    private final int mHeight;
    private final String mImageUri;
    private final int mWidth;
    
    public ImageLoadEvent(final int n, final int n2) {
        this(n, n2, null);
    }
    
    public ImageLoadEvent(final int n, final int n2, final String s) {
        this(n, n2, s, 0, 0);
    }
    
    public ImageLoadEvent(final int n, final int mEventType, final String mImageUri, final int mWidth, final int mHeight) {
        super(n);
        this.mEventType = mEventType;
        this.mImageUri = mImageUri;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    public static String eventNameForType(final int n) {
        switch (n) {
            default: {
                throw new IllegalStateException("Invalid image event: " + Integer.toString(n));
            }
            case 1: {
                return "topError";
            }
            case 2: {
                return "topLoad";
            }
            case 3: {
                return "topLoadEnd";
            }
            case 4: {
                return "topLoadStart";
            }
            case 5: {
                return "topProgress";
            }
        }
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        WritableMap writableMap = null;
        if (this.mImageUri != null || this.mEventType == 2) {
            final WritableMap map = Arguments.createMap();
            if (this.mImageUri != null) {
                map.putString("uri", this.mImageUri);
            }
            writableMap = map;
            if (this.mEventType == 2) {
                final WritableMap map2 = Arguments.createMap();
                map2.putDouble("width", this.mWidth);
                map2.putDouble("height", this.mHeight);
                if (this.mImageUri != null) {
                    map2.putString("url", this.mImageUri);
                }
                map.putMap("source", map2);
                writableMap = map;
            }
        }
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), writableMap);
    }
    
    @Override
    public short getCoalescingKey() {
        return (short)this.mEventType;
    }
    
    @Override
    public String getEventName() {
        return eventNameForType(this.mEventType);
    }
}
