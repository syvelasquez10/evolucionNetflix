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

class UIViewOperationQueue$UIBlockOperation implements UIViewOperationQueue$UIOperation
{
    private final UIBlock mBlock;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$UIBlockOperation(final UIViewOperationQueue this$0, final UIBlock mBlock) {
        this.this$0 = this$0;
        this.mBlock = mBlock;
    }
    
    @Override
    public void execute() {
        this.mBlock.execute(this.this$0.mNativeViewHierarchyManager);
    }
}
