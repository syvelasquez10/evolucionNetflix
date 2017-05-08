// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
import com.facebook.react.animation.Animation;
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
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;

final class UIViewOperationQueue$ShowPopupMenuOperation extends UIViewOperationQueue$ViewOperation
{
    private final ReadableArray mItems;
    private final Callback mSuccess;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    public UIViewOperationQueue$ShowPopupMenuOperation(final UIViewOperationQueue this$0, final int n, final ReadableArray mItems, final Callback mSuccess) {
        this.this$0 = this$0;
        super(this$0, n);
        this.mItems = mItems;
        this.mSuccess = mSuccess;
    }
    
    @Override
    public void execute() {
        this.this$0.mNativeViewHierarchyManager.showPopupMenu(this.mTag, this.mItems, this.mSuccess);
    }
}
