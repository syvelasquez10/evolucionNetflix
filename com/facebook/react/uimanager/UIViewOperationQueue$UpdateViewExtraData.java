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

final class UIViewOperationQueue$UpdateViewExtraData extends UIViewOperationQueue$ViewOperation
{
    private final Object mExtraData;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$UpdateViewExtraData(final UIViewOperationQueue this$0, final int n, final Object mExtraData) {
        this.this$0 = this$0;
        super(this$0, n);
        this.mExtraData = mExtraData;
    }
    
    @Override
    public void execute() {
        this.this$0.mNativeViewHierarchyManager.updateViewExtraData(this.mTag, this.mExtraData);
    }
}
