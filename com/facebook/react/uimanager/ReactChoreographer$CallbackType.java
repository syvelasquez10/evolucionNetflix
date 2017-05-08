// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

public enum ReactChoreographer$CallbackType
{
    DISPATCH_UI(1), 
    IDLE_EVENT(4), 
    NATIVE_ANIMATED_MODULE(2), 
    PERF_MARKERS(0), 
    TIMERS_EVENTS(3);
    
    private final int mOrder;
    
    private ReactChoreographer$CallbackType(final int mOrder) {
        this.mOrder = mOrder;
    }
    
    int getOrder() {
        return this.mOrder;
    }
}
