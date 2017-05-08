// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
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
import com.facebook.react.bridge.Callback;

class UIViewOperationQueue$AddAnimationOperation extends UIViewOperationQueue$AnimationOperation
{
    private final int mReactTag;
    private final Callback mSuccessCallback;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$AddAnimationOperation(final UIViewOperationQueue this$0, final int mReactTag, final int n, final Callback mSuccessCallback) {
        this.this$0 = this$0;
        super(n);
        this.mReactTag = mReactTag;
        this.mSuccessCallback = mSuccessCallback;
    }
    
    @Override
    public void execute() {
        final Animation animation = this.this$0.mAnimationRegistry.getAnimation(this.mAnimationID);
        if (animation != null) {
            this.this$0.mNativeViewHierarchyManager.startAnimationForNativeView(this.mReactTag, animation, this.mSuccessCallback);
            return;
        }
        throw new IllegalViewOperationException("Animation with id " + this.mAnimationID + " was not found");
    }
}
