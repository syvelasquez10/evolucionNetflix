// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.common.SystemClock;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Comparator;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.List;
import com.facebook.react.devsupport.DevSupportManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import java.util.Iterator;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactChoreographer;
import java.util.Map;
import android.util.SparseArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.ExecutorToken;
import java.util.HashMap;
import android.view.Choreographer$FrameCallback;

class Timing$TimerFrameCallback implements Choreographer$FrameCallback
{
    private final HashMap<ExecutorToken, WritableArray> mTimersToCall;
    final /* synthetic */ Timing this$0;
    
    private Timing$TimerFrameCallback(final Timing this$0) {
        this.this$0 = this$0;
        this.mTimersToCall = new HashMap<ExecutorToken, WritableArray>();
    }
    
    public void doFrame(long n) {
        if (this.this$0.isPaused.get() && !this.this$0.isRunningTasks.get()) {
            return;
        }
        n /= 1000000L;
        // monitorenter(Timing.access$200(this.this$0))
        while (true) {
            Timing$Timer timing$Timer = null;
            Label_0192: {
                try {
                    while (!this.this$0.mTimers.isEmpty() && this.this$0.mTimers.peek().mTargetTime < n) {
                        timing$Timer = this.this$0.mTimers.poll();
                        WritableArray array;
                        if ((array = this.mTimersToCall.get(timing$Timer.mExecutorToken)) == null) {
                            array = Arguments.createArray();
                            this.mTimersToCall.put(timing$Timer.mExecutorToken, array);
                        }
                        array.pushInt(timing$Timer.mCallbackID);
                        if (!timing$Timer.mRepeat) {
                            break Label_0192;
                        }
                        timing$Timer.mTargetTime = timing$Timer.mInterval + n;
                        this.this$0.mTimers.add(timing$Timer);
                    }
                    break;
                }
                finally {
                }
                // monitorexit(Timing.access$200(this.this$0))
            }
            final SparseArray sparseArray = this.this$0.mTimerIdsToTimers.get(timing$Timer.mExecutorToken);
            if (sparseArray != null) {
                sparseArray.remove(timing$Timer.mCallbackID);
                if (sparseArray.size() != 0) {
                    continue;
                }
                this.this$0.mTimerIdsToTimers.remove(timing$Timer.mExecutorToken);
            }
        }
        // monitorexit(o)
        for (final Map.Entry<ExecutorToken, WritableArray> entry : this.mTimersToCall.entrySet()) {
            this.this$0.getReactApplicationContext().getJSModule(entry.getKey(), JSTimersExecution.class).callTimers(entry.getValue());
        }
        this.mTimersToCall.clear();
        Assertions.assertNotNull(this.this$0.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.TIMERS_EVENTS, (Choreographer$FrameCallback)this);
    }
}
