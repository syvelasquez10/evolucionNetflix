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
import java.util.ArrayList;
import com.facebook.react.animation.AnimationRegistry;

class UIViewOperationQueue$SetLayoutAnimationEnabledOperation implements UIViewOperationQueue$UIOperation
{
    private final boolean mEnabled;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$SetLayoutAnimationEnabledOperation(final UIViewOperationQueue this$0, final boolean mEnabled) {
        this.this$0 = this$0;
        this.mEnabled = mEnabled;
    }
    
    @Override
    public void execute() {
        this.this$0.mNativeViewHierarchyManager.setLayoutAnimationEnabled(this.mEnabled);
    }
}
