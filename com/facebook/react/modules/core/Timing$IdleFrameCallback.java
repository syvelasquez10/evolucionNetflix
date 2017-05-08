// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.common.SystemClock;
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
import com.facebook.react.bridge.ExecutorToken;
import java.util.List;
import com.facebook.react.devsupport.DevSupportManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.uimanager.ReactChoreographer$CallbackType;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactChoreographer;
import android.view.Choreographer$FrameCallback;

class Timing$IdleFrameCallback implements Choreographer$FrameCallback
{
    final /* synthetic */ Timing this$0;
    
    private Timing$IdleFrameCallback(final Timing this$0) {
        this.this$0 = this$0;
    }
    
    public void doFrame(final long n) {
        if (this.this$0.isPaused.get() && !this.this$0.isRunningTasks.get()) {
            return;
        }
        if (this.this$0.mCurrentIdleCallbackRunnable != null) {
            this.this$0.mCurrentIdleCallbackRunnable.cancel();
        }
        this.this$0.mCurrentIdleCallbackRunnable = new Timing$IdleCallbackRunnable(this.this$0, n);
        this.this$0.getReactApplicationContext().runOnJSQueueThread(this.this$0.mCurrentIdleCallbackRunnable);
        Assertions.assertNotNull(this.this$0.mReactChoreographer).postFrameCallback(ReactChoreographer$CallbackType.IDLE_EVENT, (Choreographer$FrameCallback)this);
    }
}
