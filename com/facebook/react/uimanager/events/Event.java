// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import com.facebook.react.common.SystemClock;

public abstract class Event<T extends Event>
{
    private static int sUniqueID;
    private boolean mInitialized;
    private long mTimestampMs;
    private int mUniqueID;
    private int mViewTag;
    
    static {
        Event.sUniqueID = 0;
    }
    
    protected Event() {
        final int sUniqueID = Event.sUniqueID;
        Event.sUniqueID = sUniqueID + 1;
        this.mUniqueID = sUniqueID;
    }
    
    protected Event(final int n) {
        final int sUniqueID = Event.sUniqueID;
        Event.sUniqueID = sUniqueID + 1;
        this.mUniqueID = sUniqueID;
        this.init(n);
    }
    
    public boolean canCoalesce() {
        return true;
    }
    
    public T coalesce(final T t) {
        if (this.getTimestampMs() >= t.getTimestampMs()) {
            return (T)this;
        }
        return t;
    }
    
    public abstract void dispatch(final RCTEventEmitter p0);
    
    final void dispose() {
        this.mInitialized = false;
        this.onDispose();
    }
    
    public short getCoalescingKey() {
        return 0;
    }
    
    public abstract String getEventName();
    
    public final long getTimestampMs() {
        return this.mTimestampMs;
    }
    
    public int getUniqueID() {
        return this.mUniqueID;
    }
    
    public final int getViewTag() {
        return this.mViewTag;
    }
    
    protected void init(final int mViewTag) {
        this.mViewTag = mViewTag;
        this.mTimestampMs = SystemClock.uptimeMillis();
        this.mInitialized = true;
    }
    
    boolean isInitialized() {
        return this.mInitialized;
    }
    
    public void onDispose() {
    }
}
