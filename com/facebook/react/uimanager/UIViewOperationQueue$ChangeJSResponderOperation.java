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

final class UIViewOperationQueue$ChangeJSResponderOperation extends UIViewOperationQueue$ViewOperation
{
    private final boolean mBlockNativeResponder;
    private final boolean mClearResponder;
    private final int mInitialTag;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$ChangeJSResponderOperation(final UIViewOperationQueue this$0, final int n, final int mInitialTag, final boolean mClearResponder, final boolean mBlockNativeResponder) {
        this.this$0 = this$0;
        super(this$0, n);
        this.mInitialTag = mInitialTag;
        this.mClearResponder = mClearResponder;
        this.mBlockNativeResponder = mBlockNativeResponder;
    }
    
    @Override
    public void execute() {
        if (!this.mClearResponder) {
            this.this$0.mNativeViewHierarchyManager.setJSResponder(this.mTag, this.mInitialTag, this.mBlockNativeResponder);
            return;
        }
        this.this$0.mNativeViewHierarchyManager.clearJSResponder();
    }
}
