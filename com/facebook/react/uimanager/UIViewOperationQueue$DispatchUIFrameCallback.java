// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.SoftAssertions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import com.facebook.react.animation.AnimationRegistry;
import android.view.Choreographer$FrameCallback;
import com.facebook.systrace.Systrace;
import com.facebook.react.bridge.ReactContext;

class UIViewOperationQueue$DispatchUIFrameCallback extends GuardedChoreographerFrameCallback
{
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$DispatchUIFrameCallback(final UIViewOperationQueue this$0, final ReactContext reactContext) {
        this.this$0 = this$0;
        super(reactContext);
    }
    
    private void dispatchPendingNonBatchedOperations(final long n) {
        while (16L - (System.nanoTime() - n) / 1000000L >= 8L) {
            synchronized (this.this$0.mNonBatchedOperationsLock) {
                if (this.this$0.mNonBatchedOperations.isEmpty()) {
                    return;
                }
            }
            final UIViewOperationQueue$UIOperation uiViewOperationQueue$UIOperation = this.this$0.mNonBatchedOperations.pollFirst();
            // monitorexit(o)
            uiViewOperationQueue$UIOperation.execute();
        }
    }
    
    public void doFrameGuarded(final long n) {
        Systrace.beginSection(0L, "dispatchNonBatchedUIOperations");
        try {
            this.dispatchPendingNonBatchedOperations(n);
            Systrace.endSection(0L);
            this.this$0.flushPendingBatches();
            ReactChoreographer.getInstance().postFrameCallback(ReactChoreographer$CallbackType.DISPATCH_UI, (Choreographer$FrameCallback)this);
        }
        finally {
            Systrace.endSection(0L);
        }
    }
}
