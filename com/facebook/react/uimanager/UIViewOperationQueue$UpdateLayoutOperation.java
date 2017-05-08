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
import com.facebook.systrace.Systrace;

final class UIViewOperationQueue$UpdateLayoutOperation extends UIViewOperationQueue$ViewOperation
{
    private final int mHeight;
    private final int mParentTag;
    private final int mWidth;
    private final int mX;
    private final int mY;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$UpdateLayoutOperation(final UIViewOperationQueue this$0, final int mParentTag, final int n, final int mx, final int my, final int mWidth, final int mHeight) {
        this.this$0 = this$0;
        super(this$0, n);
        this.mParentTag = mParentTag;
        this.mX = mx;
        this.mY = my;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        Systrace.startAsyncFlow(0L, "updateLayout", this.mTag);
    }
    
    @Override
    public void execute() {
        Systrace.endAsyncFlow(0L, "updateLayout", this.mTag);
        this.this$0.mNativeViewHierarchyManager.updateLayout(this.mParentTag, this.mTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }
}
