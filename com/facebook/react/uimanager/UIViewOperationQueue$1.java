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
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import com.facebook.react.animation.AnimationRegistry;
import java.util.concurrent.Semaphore;

class UIViewOperationQueue$1 implements Runnable
{
    final /* synthetic */ UIViewOperationQueue this$0;
    final /* synthetic */ SizeMonitoringFrameLayout val$rootView;
    final /* synthetic */ Semaphore val$semaphore;
    final /* synthetic */ int val$tag;
    final /* synthetic */ ThemedReactContext val$themedRootContext;
    
    UIViewOperationQueue$1(final UIViewOperationQueue this$0, final int val$tag, final SizeMonitoringFrameLayout val$rootView, final ThemedReactContext val$themedRootContext, final Semaphore val$semaphore) {
        this.this$0 = this$0;
        this.val$tag = val$tag;
        this.val$rootView = val$rootView;
        this.val$themedRootContext = val$themedRootContext;
        this.val$semaphore = val$semaphore;
    }
    
    @Override
    public void run() {
        this.this$0.mNativeViewHierarchyManager.addRootView(this.val$tag, this.val$rootView, this.val$themedRootContext);
        this.val$semaphore.release();
    }
}
