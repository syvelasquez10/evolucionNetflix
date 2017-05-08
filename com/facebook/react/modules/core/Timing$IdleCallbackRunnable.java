// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import java.util.Iterator;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
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
import java.util.List;
import com.facebook.react.devsupport.DevSupportManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ExecutorToken;
import java.util.Collection;
import com.facebook.react.common.SystemClock;

class Timing$IdleCallbackRunnable implements Runnable
{
    private volatile boolean mCancelled;
    private final long mFrameStartTime;
    final /* synthetic */ Timing this$0;
    
    public Timing$IdleCallbackRunnable(final Timing this$0, final long mFrameStartTime) {
        this.this$0 = this$0;
        this.mCancelled = false;
        this.mFrameStartTime = mFrameStartTime;
    }
    
    public void cancel() {
        this.mCancelled = true;
    }
    
    @Override
    public void run() {
        if (!this.mCancelled) {
            final long n = SystemClock.uptimeMillis() - this.mFrameStartTime / 1000000L;
            final long currentTimeMillis = SystemClock.currentTimeMillis();
            if (16.666666f - n >= 1.0f) {
                this.this$0.mIdleCallbackContextsToCall.clear();
                Object o = this.this$0.mIdleCallbackGuard;
                synchronized (o) {
                    this.this$0.mIdleCallbackContextsToCall.addAll(this.this$0.mSendIdleEventsExecutorTokens);
                    // monitorexit(o)
                    o = this.this$0.mIdleCallbackContextsToCall.iterator();
                    while (((Iterator)o).hasNext()) {
                        this.this$0.getReactApplicationContext().getJSModule(((Iterator<ExecutorToken>)o).next(), JSTimersExecution.class).callIdleCallbacks(currentTimeMillis - n);
                    }
                }
                this.this$0.mCurrentIdleCallbackRunnable = null;
            }
        }
    }
}
