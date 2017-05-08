// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

class EventDispatcher$ScheduleDispatchFrameCallback$1 implements Runnable
{
    final /* synthetic */ EventDispatcher$ScheduleDispatchFrameCallback this$1;
    
    EventDispatcher$ScheduleDispatchFrameCallback$1(final EventDispatcher$ScheduleDispatchFrameCallback this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.maybePost();
    }
}
