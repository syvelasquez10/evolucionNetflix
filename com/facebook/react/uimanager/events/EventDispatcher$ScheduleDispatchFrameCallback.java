// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager.events;

import java.util.Iterator;
import com.facebook.infer.annotation.Assertions;
import java.util.Arrays;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayList;
import java.util.Map;
import android.util.LongSparseArray;
import java.util.Comparator;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.react.uimanager.ReactChoreographer;
import android.view.Choreographer$FrameCallback;

class EventDispatcher$ScheduleDispatchFrameCallback implements Choreographer$FrameCallback
{
    private volatile boolean mIsPosted;
    private boolean mShouldStop;
    final /* synthetic */ EventDispatcher this$0;
    
    private EventDispatcher$ScheduleDispatchFrameCallback(final EventDispatcher this$0) {
        this.this$0 = this$0;
        this.mIsPosted = false;
        this.mShouldStop = false;
    }
    
    private void post() {
        ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer$CallbackType.TIMERS_EVENTS, (Choreographer$FrameCallback)this.this$0.mCurrentFrameCallback);
    }
    
    public void doFrame(final long n) {
        UiThreadUtil.assertOnUiThread();
        Label_0092: {
            if (!this.mShouldStop) {
                break Label_0092;
            }
            this.mIsPosted = false;
            while (true) {
                Systrace.beginSection(0L, "ScheduleDispatchFrameCallback");
                try {
                    this.this$0.moveStagedEventsToDispatchQueue();
                    if (this.this$0.mEventsToDispatchSize > 0 && !this.this$0.mHasDispatchScheduled) {
                        this.this$0.mHasDispatchScheduled = true;
                        Systrace.startAsyncFlow(0L, "ScheduleDispatchFrameCallback", this.this$0.mHasDispatchScheduledCount);
                        this.this$0.mReactContext.runOnJSQueueThread(this.this$0.mDispatchEventsRunnable);
                    }
                    return;
                    this.post();
                }
                finally {
                    Systrace.endSection(0L);
                }
            }
        }
    }
    
    public void maybePost() {
        if (!this.mIsPosted) {
            this.mIsPosted = true;
            this.post();
        }
    }
    
    public void maybePostFromNonUI() {
        if (this.mIsPosted) {
            return;
        }
        if (this.this$0.mReactContext.isOnUiQueueThread()) {
            this.maybePost();
            return;
        }
        this.this$0.mReactContext.runOnUiQueueThread(new EventDispatcher$ScheduleDispatchFrameCallback$1(this));
    }
    
    public void stop() {
        this.mShouldStop = true;
    }
}
