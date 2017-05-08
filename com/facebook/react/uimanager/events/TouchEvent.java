// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.infer.annotation.Assertions;
import android.view.MotionEvent;
import android.support.v4.util.Pools$SynchronizedPool;

public class TouchEvent extends Event<TouchEvent>
{
    private static final Pools$SynchronizedPool<TouchEvent> EVENTS_POOL;
    private short mCoalescingKey;
    private MotionEvent mMotionEvent;
    private TouchEventType mTouchEventType;
    private float mViewX;
    private float mViewY;
    
    static {
        EVENTS_POOL = new Pools$SynchronizedPool<TouchEvent>(3);
    }
    
    private void init(int n, final TouchEventType mTouchEventType, final MotionEvent motionEvent, final float mViewX, final float mViewY, final TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        super.init(n);
        short coalescingKey = 0;
        n = (motionEvent.getAction() & 0xFF);
        switch (n) {
            default: {
                throw new RuntimeException("Unhandled MotionEvent action: " + n);
            }
            case 0: {
                touchEventCoalescingKeyHelper.addCoalescingKey(motionEvent.getDownTime());
                break;
            }
            case 1: {
                touchEventCoalescingKeyHelper.removeCoalescingKey(motionEvent.getDownTime());
                break;
            }
            case 5:
            case 6: {
                touchEventCoalescingKeyHelper.incrementCoalescingKey(motionEvent.getDownTime());
                break;
            }
            case 2: {
                coalescingKey = touchEventCoalescingKeyHelper.getCoalescingKey(motionEvent.getDownTime());
                break;
            }
            case 3: {
                touchEventCoalescingKeyHelper.removeCoalescingKey(motionEvent.getDownTime());
                break;
            }
        }
        this.mTouchEventType = mTouchEventType;
        this.mMotionEvent = MotionEvent.obtain(motionEvent);
        this.mCoalescingKey = coalescingKey;
        this.mViewX = mViewX;
        this.mViewY = mViewY;
    }
    
    public static TouchEvent obtain(final int n, final TouchEventType touchEventType, final MotionEvent motionEvent, final float n2, final float n3, final TouchEventCoalescingKeyHelper touchEventCoalescingKeyHelper) {
        TouchEvent touchEvent;
        if ((touchEvent = TouchEvent.EVENTS_POOL.acquire()) == null) {
            touchEvent = new TouchEvent();
        }
        touchEvent.init(n, touchEventType, motionEvent, n2, n3, touchEventCoalescingKeyHelper);
        return touchEvent;
    }
    
    @Override
    public boolean canCoalesce() {
        switch (TouchEvent$1.$SwitchMap$com$facebook$react$uimanager$events$TouchEventType[Assertions.assertNotNull(this.mTouchEventType).ordinal()]) {
            default: {
                throw new RuntimeException("Unknown touch event type: " + this.mTouchEventType);
            }
            case 1:
            case 2:
            case 3: {
                return false;
            }
            case 4: {
                return true;
            }
        }
    }
    
    @Override
    public void dispatch(final RCTEventEmitter rctEventEmitter) {
        TouchesHelper.sendTouchEvent(rctEventEmitter, Assertions.assertNotNull(this.mTouchEventType), this.getViewTag(), this);
    }
    
    @Override
    public short getCoalescingKey() {
        return this.mCoalescingKey;
    }
    
    @Override
    public String getEventName() {
        return Assertions.assertNotNull(this.mTouchEventType).getJSEventName();
    }
    
    public MotionEvent getMotionEvent() {
        Assertions.assertNotNull(this.mMotionEvent);
        return this.mMotionEvent;
    }
    
    public float getViewX() {
        return this.mViewX;
    }
    
    public float getViewY() {
        return this.mViewY;
    }
    
    @Override
    public void onDispose() {
        Assertions.assertNotNull(this.mMotionEvent).recycle();
        this.mMotionEvent = null;
        TouchEvent.EVENTS_POOL.release(this);
    }
}
