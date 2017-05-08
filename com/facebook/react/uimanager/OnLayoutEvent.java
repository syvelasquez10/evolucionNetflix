// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import android.support.v4.util.Pools$SynchronizedPool;
import com.facebook.react.uimanager.events.Event;

public class OnLayoutEvent extends Event<OnLayoutEvent>
{
    private static final Pools$SynchronizedPool<OnLayoutEvent> EVENTS_POOL;
    private int mHeight;
    private int mWidth;
    private int mX;
    private int mY;
    
    static {
        EVENTS_POOL = new Pools$SynchronizedPool<OnLayoutEvent>(20);
    }
    
    public static OnLayoutEvent obtain(final int n, final int n2, final int n3, final int n4, final int n5) {
        OnLayoutEvent onLayoutEvent;
        if ((onLayoutEvent = OnLayoutEvent.EVENTS_POOL.acquire()) == null) {
            onLayoutEvent = new OnLayoutEvent();
        }
        onLayoutEvent.init(n, n2, n3, n4, n5);
        return onLayoutEvent;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        final WritableMap map = Arguments.createMap();
        map.putDouble("x", PixelUtil.toDIPFromPixel(this.mX));
        map.putDouble("y", PixelUtil.toDIPFromPixel(this.mY));
        map.putDouble("width", PixelUtil.toDIPFromPixel(this.mWidth));
        map.putDouble("height", PixelUtil.toDIPFromPixel(this.mHeight));
        final WritableMap map2 = Arguments.createMap();
        map2.putMap("layout", map);
        map2.putInt("target", this.getViewTag());
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), map2);
    }
    
    @Override
    public String getEventName() {
        return "topLayout";
    }
    
    protected void init(final int n, final int mx, final int my, final int mWidth, final int mHeight) {
        super.init(n);
        this.mX = mx;
        this.mY = my;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }
    
    @Override
    public void onDispose() {
        OnLayoutEvent.EVENTS_POOL.release(this);
    }
}
