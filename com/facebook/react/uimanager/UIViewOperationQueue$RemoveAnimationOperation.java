// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
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
import com.facebook.react.animation.Animation;

final class UIViewOperationQueue$RemoveAnimationOperation extends UIViewOperationQueue$AnimationOperation
{
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$RemoveAnimationOperation(final UIViewOperationQueue this$0, final int n) {
        this.this$0 = this$0;
        super(n);
    }
    
    @Override
    public void execute() {
        final Animation animation = this.this$0.mAnimationRegistry.getAnimation(this.mAnimationID);
        if (animation != null) {
            animation.cancel();
        }
    }
}
