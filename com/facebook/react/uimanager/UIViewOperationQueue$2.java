// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.SoftAssertions;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayDeque;
import com.facebook.react.animation.AnimationRegistry;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;

class UIViewOperationQueue$2 implements Runnable
{
    final /* synthetic */ UIViewOperationQueue this$0;
    final /* synthetic */ int val$batchId;
    final /* synthetic */ UIViewOperationQueue$UIOperation[] val$nonBatchedOperations;
    final /* synthetic */ ArrayList val$operations;
    
    UIViewOperationQueue$2(final UIViewOperationQueue this$0, final int val$batchId, final UIViewOperationQueue$UIOperation[] val$nonBatchedOperations, final ArrayList val$operations) {
        this.this$0 = this$0;
        this.val$batchId = val$batchId;
        this.val$nonBatchedOperations = val$nonBatchedOperations;
        this.val$operations = val$operations;
    }
    
    @Override
    public void run() {
        SystraceMessage.beginSection(0L, "DispatchUI").arg("BatchId", this.val$batchId).flush();
        try {
            if (this.val$nonBatchedOperations != null) {
                final UIViewOperationQueue$UIOperation[] val$nonBatchedOperations = this.val$nonBatchedOperations;
                for (int length = val$nonBatchedOperations.length, i = 0; i < length; ++i) {
                    val$nonBatchedOperations[i].execute();
                }
            }
            if (this.val$operations != null) {
                for (int j = 0; j < this.val$operations.size(); ++j) {
                    ((UIViewOperationQueue$UIOperation)this.val$operations.get(j)).execute();
                }
            }
            this.this$0.mNativeViewHierarchyManager.clearLayoutAnimation();
            if (this.this$0.mViewHierarchyUpdateDebugListener != null) {
                this.this$0.mViewHierarchyUpdateDebugListener.onViewHierarchyUpdateFinished();
            }
        }
        finally {
            Systrace.endSection(0L);
        }
    }
}
