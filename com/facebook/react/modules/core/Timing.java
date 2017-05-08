// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.common.SystemClock;
import android.view.Choreographer$FrameCallback;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Comparator;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.PriorityQueue;
import android.util.SparseArray;
import java.util.Map;
import java.util.Set;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.bridge.ExecutorToken;
import java.util.List;
import com.facebook.react.devsupport.DevSupportManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public final class Timing extends ReactContextBaseJavaModule implements LifecycleEventListener, HeadlessJsTaskEventListener
{
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    private final AtomicBoolean isPaused;
    private final AtomicBoolean isRunningTasks;
    private Timing$IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    private final DevSupportManager mDevSupportManager;
    private boolean mFrameCallbackPosted;
    private boolean mFrameIdleCallbackPosted;
    private final List<ExecutorToken> mIdleCallbackContextsToCall;
    private final Object mIdleCallbackGuard;
    private final Timing$IdleFrameCallback mIdleFrameCallback;
    private ReactChoreographer mReactChoreographer;
    private final Set<ExecutorToken> mSendIdleEventsExecutorTokens;
    private final Timing$TimerFrameCallback mTimerFrameCallback;
    private final Object mTimerGuard;
    private final Map<ExecutorToken, SparseArray<Timing$Timer>> mTimerIdsToTimers;
    private final PriorityQueue<Timing$Timer> mTimers;
    
    public Timing(final ReactApplicationContext reactApplicationContext, final DevSupportManager mDevSupportManager) {
        super(reactApplicationContext);
        this.mTimerGuard = new Object();
        this.mIdleCallbackGuard = new Object();
        this.isPaused = new AtomicBoolean(true);
        this.isRunningTasks = new AtomicBoolean(false);
        this.mTimerFrameCallback = new Timing$TimerFrameCallback(this, null);
        this.mIdleFrameCallback = new Timing$IdleFrameCallback(this, null);
        this.mFrameCallbackPosted = false;
        this.mFrameIdleCallbackPosted = false;
        this.mDevSupportManager = mDevSupportManager;
        this.mTimers = new PriorityQueue<Timing$Timer>(11, new Timing$1(this));
        this.mTimerIdsToTimers = new HashMap<ExecutorToken, SparseArray<Timing$Timer>>();
        this.mSendIdleEventsExecutorTokens = new HashSet<ExecutorToken>();
        this.mIdleCallbackContextsToCall = new ArrayList<ExecutorToken>();
    }
    
    private void clearChoreographerCallback() {
        final HeadlessJsTaskContext instance = HeadlessJsTaskContext.getInstance(this.getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !instance.hasActiveTasks()) {
            Assertions.assertNotNull(this.mReactChoreographer).removeFrameCallback(ReactChoreographer$CallbackType.TIMERS_EVENTS, (Choreographer$FrameCallback)this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }
    
    private void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            Assertions.assertNotNull(this.mReactChoreographer).removeFrameCallback(ReactChoreographer$CallbackType.IDLE_EVENT, (Choreographer$FrameCallback)this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }
    
    private void maybeClearChoreographerIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            this.clearChoreographerCallback();
        }
    }
    
    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEventsExecutorTokens.size() > 0) {
                this.setChoreographerIdleCallback();
            }
        }
    }
    
    private void setChoreographerCallback() {
        if (!this.mFrameCallbackPosted) {
            Assertions.assertNotNull(this.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.TIMERS_EVENTS, (Choreographer$FrameCallback)this.mTimerFrameCallback);
            this.mFrameCallbackPosted = true;
        }
    }
    
    private void setChoreographerIdleCallback() {
        if (!this.mFrameIdleCallbackPosted) {
            Assertions.assertNotNull(this.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.IDLE_EVENT, (Choreographer$FrameCallback)this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = true;
        }
    }
    
    @ReactMethod
    public void createTimer(final ExecutorToken executorToken, final int n, final int n2, final double n3, final boolean b) {
        final long currentTimeMillis = SystemClock.currentTimeMillis();
        final long n4 = (long)n3;
        if (this.mDevSupportManager.getDevSupportEnabled() && Math.abs(n4 - currentTimeMillis) > 60000L) {
            this.getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        final long max = Math.max(0L, n4 - currentTimeMillis + n2);
        if (n2 == 0 && !b) {
            final WritableArray array = Arguments.createArray();
            array.pushInt(n);
            this.getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class).callTimers(array);
            return;
        }
        final Timing$Timer timing$Timer = new Timing$Timer(executorToken, n, SystemClock.nanoTime() / 1000000L + max, n2, b, null);
        synchronized (this.mTimerGuard) {
            this.mTimers.add(timing$Timer);
            SparseArray sparseArray;
            if ((sparseArray = this.mTimerIdsToTimers.get(executorToken)) == null) {
                sparseArray = new SparseArray();
                this.mTimerIdsToTimers.put(executorToken, (SparseArray<Timing$Timer>)sparseArray);
            }
            sparseArray.put(n, (Object)timing$Timer);
        }
    }
    
    @ReactMethod
    public void deleteTimer(final ExecutorToken executorToken, final int n) {
        final SparseArray<Timing$Timer> sparseArray;
        final Timing$Timer timing$Timer;
        synchronized (this.mTimerGuard) {
            sparseArray = this.mTimerIdsToTimers.get(executorToken);
            if (sparseArray == null) {
                return;
            }
            timing$Timer = (Timing$Timer)sparseArray.get(n);
            if (timing$Timer == null) {
                return;
            }
        }
        sparseArray.remove(n);
        if (sparseArray.size() == 0) {
            final Throwable t;
            this.mTimerIdsToTimers.remove(t);
        }
        this.mTimers.remove(timing$Timer);
    }
    // monitorexit(o)
    
    @Override
    public String getName() {
        return "RCTTiming";
    }
    
    @Override
    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        this.getReactApplicationContext().addLifecycleEventListener(this);
        HeadlessJsTaskContext.getInstance(this.getReactApplicationContext()).addTaskEventListener(this);
    }
    
    @Override
    public void onCatalystInstanceDestroy() {
        this.clearChoreographerCallback();
        this.clearChoreographerIdleCallback();
        HeadlessJsTaskContext.getInstance(this.getReactApplicationContext()).removeTaskEventListener(this);
    }
    
    public void onExecutorDestroyed(final ExecutorToken executorToken) {
    Label_0030_Outer:
        while (true) {
            while (true) {
                Label_0105: {
                    synchronized (this.mTimerGuard) {
                        final SparseArray<Timing$Timer> sparseArray = this.mTimerIdsToTimers.remove(executorToken);
                        if (sparseArray == null) {
                            return;
                        }
                        break Label_0105;
                        // iftrue(Label_0072:, n >= sparseArray.size())
                        while (true) {
                            final int n;
                            this.mTimers.remove(sparseArray.get(sparseArray.keyAt(n)));
                            ++n;
                            continue Label_0030_Outer;
                        }
                        Label_0072: {
                            final Object mIdleCallbackGuard = this.mIdleCallbackGuard;
                        }
                        // monitorexit(this.mTimerGuard)
                        synchronized (this.mTimerGuard) {
                            final Throwable t;
                            this.mSendIdleEventsExecutorTokens.remove(t);
                            return;
                        }
                    }
                }
                int n = 0;
                continue;
            }
        }
    }
    
    @Override
    public void onHeadlessJsTaskFinish(final int n) {
        if (!HeadlessJsTaskContext.getInstance(this.getReactApplicationContext()).hasActiveTasks()) {
            this.isRunningTasks.set(false);
            this.clearChoreographerCallback();
            this.maybeClearChoreographerIdleCallback();
        }
    }
    
    public void onHeadlessJsTaskStart(final int n) {
        if (!this.isRunningTasks.getAndSet(true)) {
            this.setChoreographerCallback();
            this.maybeSetChoreographerIdleCallback();
        }
    }
    
    public void onHostDestroy() {
        this.clearChoreographerCallback();
        this.maybeClearChoreographerIdleCallback();
    }
    
    @Override
    public void onHostPause() {
        this.isPaused.set(true);
        this.clearChoreographerCallback();
        this.maybeClearChoreographerIdleCallback();
    }
    
    @Override
    public void onHostResume() {
        this.isPaused.set(false);
        this.setChoreographerCallback();
        this.maybeSetChoreographerIdleCallback();
    }
    
    @ReactMethod
    public void setSendIdleEvents(final ExecutorToken executorToken, final boolean b) {
        final Object mIdleCallbackGuard = this.mIdleCallbackGuard;
        // monitorenter(mIdleCallbackGuard)
        Label_0036: {
            if (!b) {
                break Label_0036;
            }
            try {
                this.mSendIdleEventsExecutorTokens.add(executorToken);
                while (true) {
                    UiThreadUtil.runOnUiThread(new Timing$2(this));
                    return;
                    this.mSendIdleEventsExecutorTokens.remove(executorToken);
                    continue;
                }
            }
            // monitorexit(mIdleCallbackGuard)
            finally {
            }
            // monitorexit(mIdleCallbackGuard)
        }
    }
    
    @Override
    public boolean supportsWebWorkers() {
        return true;
    }
}
