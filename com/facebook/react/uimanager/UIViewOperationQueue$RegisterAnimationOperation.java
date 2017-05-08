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

class UIViewOperationQueue$RegisterAnimationOperation extends UIViewOperationQueue$AnimationOperation
{
    private final Animation mAnimation;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$RegisterAnimationOperation(final UIViewOperationQueue this$0, final Animation mAnimation) {
        this.this$0 = this$0;
        super(mAnimation.getAnimationID());
        this.mAnimation = mAnimation;
    }
    
    @Override
    public void execute() {
        this.this$0.mAnimationRegistry.registerAnimation(this.mAnimation);
    }
}
