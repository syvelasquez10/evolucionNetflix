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

final class UIViewOperationQueue$CreateViewOperation extends UIViewOperationQueue$ViewOperation
{
    private final String mClassName;
    private final ReactStylesDiffMap mInitialProps;
    private final ThemedReactContext mThemedContext;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$CreateViewOperation(final UIViewOperationQueue this$0, final ThemedReactContext mThemedContext, final int n, final String mClassName, final ReactStylesDiffMap mInitialProps) {
        this.this$0 = this$0;
        super(this$0, n);
        this.mThemedContext = mThemedContext;
        this.mClassName = mClassName;
        this.mInitialProps = mInitialProps;
        Systrace.startAsyncFlow(0L, "createView", this.mTag);
    }
    
    @Override
    public void execute() {
        Systrace.endAsyncFlow(0L, "createView", this.mTag);
        this.this$0.mNativeViewHierarchyManager.createView(this.mThemedContext, this.mTag, this.mClassName, this.mInitialProps);
    }
}
