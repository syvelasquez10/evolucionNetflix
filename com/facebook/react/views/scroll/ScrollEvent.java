// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import android.support.v4.util.Pools$SynchronizedPool;
import com.facebook.react.uimanager.events.Event;

public class ScrollEvent extends Event<ScrollEvent>
{
    private static final Pools$SynchronizedPool<ScrollEvent> EVENTS_POOL;
    private int mContentHeight;
    private int mContentWidth;
    private ScrollEventType mScrollEventType;
    private int mScrollViewHeight;
    private int mScrollViewWidth;
    private int mScrollX;
    private int mScrollY;
    
    static {
        EVENTS_POOL = new Pools$SynchronizedPool<ScrollEvent>(3);
    }
    
    private void init(final int n, final ScrollEventType mScrollEventType, final int mScrollX, final int mScrollY, final int mContentWidth, final int mContentHeight, final int mScrollViewWidth, final int mScrollViewHeight) {
        super.init(n);
        this.mScrollEventType = mScrollEventType;
        this.mScrollX = mScrollX;
        this.mScrollY = mScrollY;
        this.mContentWidth = mContentWidth;
        this.mContentHeight = mContentHeight;
        this.mScrollViewWidth = mScrollViewWidth;
        this.mScrollViewHeight = mScrollViewHeight;
    }
    
    public static ScrollEvent obtain(final int n, final ScrollEventType scrollEventType, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        ScrollEvent scrollEvent;
        if ((scrollEvent = ScrollEvent.EVENTS_POOL.acquire()) == null) {
            scrollEvent = new ScrollEvent();
        }
        scrollEvent.init(n, scrollEventType, n2, n3, n4, n5, n6, n7);
        return scrollEvent;
    }
    
    private WritableMap serializeEventData() {
        final WritableMap map = Arguments.createMap();
        map.putDouble("top", 0.0);
        map.putDouble("bottom", 0.0);
        map.putDouble("left", 0.0);
        map.putDouble("right", 0.0);
        final WritableMap map2 = Arguments.createMap();
        map2.putDouble("x", PixelUtil.toDIPFromPixel(this.mScrollX));
        map2.putDouble("y", PixelUtil.toDIPFromPixel(this.mScrollY));
        final WritableMap map3 = Arguments.createMap();
        map3.putDouble("width", PixelUtil.toDIPFromPixel(this.mContentWidth));
        map3.putDouble("height", PixelUtil.toDIPFromPixel(this.mContentHeight));
        final WritableMap map4 = Arguments.createMap();
        map4.putDouble("width", PixelUtil.toDIPFromPixel(this.mScrollViewWidth));
        map4.putDouble("height", PixelUtil.toDIPFromPixel(this.mScrollViewHeight));
        final WritableMap map5 = Arguments.createMap();
        map5.putMap("contentInset", map);
        map5.putMap("contentOffset", map2);
        map5.putMap("contentSize", map3);
        map5.putMap("layoutMeasurement", map4);
        map5.putInt("target", this.getViewTag());
        map5.putBoolean("responderIgnoreScroll", true);
        return map5;
    }
    
    @Override
    public boolean canCoalesce() {
        return this.mScrollEventType == ScrollEventType.SCROLL;
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(this.getViewTag(), this.getEventName(), this.serializeEventData());
    }
    
    @Override
    public short getCoalescingKey() {
        return 0;
    }
    
    @Override
    public String getEventName() {
        return Assertions.assertNotNull(this.mScrollEventType).getJSEventName();
    }
    
    @Override
    public void onDispose() {
        ScrollEvent.EVENTS_POOL.release(this);
    }
}
