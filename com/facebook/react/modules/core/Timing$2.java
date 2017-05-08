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

class Timing$2 implements Runnable
{
    final /* synthetic */ Timing this$0;
    
    Timing$2(final Timing this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        synchronized (this.this$0.mIdleCallbackGuard) {
            if (this.this$0.mSendIdleEventsExecutorTokens.size() > 0) {
                this.this$0.setChoreographerIdleCallback();
            }
            else {
                this.this$0.clearChoreographerIdleCallback();
            }
        }
    }
}
