// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.Choreographer$FrameCallback;
import com.facebook.react.animation.Animation;
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
import com.facebook.react.bridge.Callback;

final class UIViewOperationQueue$MeasureInWindowOperation implements UIViewOperationQueue$UIOperation
{
    private final Callback mCallback;
    private final int mReactTag;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$MeasureInWindowOperation(final UIViewOperationQueue this$0, final int mReactTag, final Callback mCallback) {
        this.this$0 = this$0;
        this.mReactTag = mReactTag;
        this.mCallback = mCallback;
    }
    
    @Override
    public void execute() {
        try {
            this.this$0.mNativeViewHierarchyManager.measureInWindow(this.mReactTag, this.this$0.mMeasureBuffer);
            this.mCallback.invoke(PixelUtil.toDIPFromPixel(this.this$0.mMeasureBuffer[0]), PixelUtil.toDIPFromPixel(this.this$0.mMeasureBuffer[1]), PixelUtil.toDIPFromPixel(this.this$0.mMeasureBuffer[2]), PixelUtil.toDIPFromPixel(this.this$0.mMeasureBuffer[3]));
        }
        catch (NoSuchNativeViewException ex) {
            this.mCallback.invoke(new Object[0]);
        }
    }
}
