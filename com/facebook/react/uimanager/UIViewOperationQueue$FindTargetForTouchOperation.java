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

final class UIViewOperationQueue$FindTargetForTouchOperation implements UIViewOperationQueue$UIOperation
{
    private final Callback mCallback;
    private final int mReactTag;
    private final float mTargetX;
    private final float mTargetY;
    final /* synthetic */ UIViewOperationQueue this$0;
    
    private UIViewOperationQueue$FindTargetForTouchOperation(final UIViewOperationQueue this$0, final int mReactTag, final float mTargetX, final float mTargetY, final Callback mCallback) {
        this.this$0 = this$0;
        this.mReactTag = mReactTag;
        this.mTargetX = mTargetX;
        this.mTargetY = mTargetY;
        this.mCallback = mCallback;
    }
    
    @Override
    public void execute() {
        float n;
        float n2;
        int targetTagForTouch;
        try {
            this.this$0.mNativeViewHierarchyManager.measure(this.mReactTag, this.this$0.mMeasureBuffer);
            n = this.this$0.mMeasureBuffer[0];
            n2 = this.this$0.mMeasureBuffer[1];
            targetTagForTouch = this.this$0.mNativeViewHierarchyManager.findTargetTagForTouch(this.mReactTag, this.mTargetX, this.mTargetY);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation = this;
            final UIViewOperationQueue uiViewOperationQueue = uiViewOperationQueue$FindTargetForTouchOperation.this$0;
            final NativeViewHierarchyManager nativeViewHierarchyManager = uiViewOperationQueue.mNativeViewHierarchyManager;
            final int n3 = targetTagForTouch;
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation2 = this;
            final UIViewOperationQueue uiViewOperationQueue2 = uiViewOperationQueue$FindTargetForTouchOperation2.this$0;
            final int[] array = uiViewOperationQueue2.mMeasureBuffer;
            nativeViewHierarchyManager.measure(n3, array);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation3 = this;
            final UIViewOperationQueue uiViewOperationQueue3 = uiViewOperationQueue$FindTargetForTouchOperation3.this$0;
            final int[] array2 = uiViewOperationQueue3.mMeasureBuffer;
            final int n4 = 0;
            final int n5 = array2[n4];
            final float n6 = n5;
            final float n7 = n;
            final float n8 = n6 - n7;
            final float n9 = PixelUtil.toDIPFromPixel(n8);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation4 = this;
            final UIViewOperationQueue uiViewOperationQueue4 = uiViewOperationQueue$FindTargetForTouchOperation4.this$0;
            final int[] array3 = uiViewOperationQueue4.mMeasureBuffer;
            final int n10 = 1;
            final int n11 = array3[n10];
            final float n12 = n11;
            final float n13 = n2;
            final float n14 = n12 - n13;
            final float n15 = PixelUtil.toDIPFromPixel(n14);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation5 = this;
            final UIViewOperationQueue uiViewOperationQueue5 = uiViewOperationQueue$FindTargetForTouchOperation5.this$0;
            final int[] array4 = uiViewOperationQueue5.mMeasureBuffer;
            final int n16 = 2;
            final int n17 = array4[n16];
            final float n18 = n17;
            final float n19 = PixelUtil.toDIPFromPixel(n18);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation6 = this;
            final UIViewOperationQueue uiViewOperationQueue6 = uiViewOperationQueue$FindTargetForTouchOperation6.this$0;
            final int[] array5 = uiViewOperationQueue6.mMeasureBuffer;
            final int n20 = 3;
            final int n21 = array5[n20];
            final float n22 = n21;
            final float n23 = PixelUtil.toDIPFromPixel(n22);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation7 = this;
            final Callback callback = uiViewOperationQueue$FindTargetForTouchOperation7.mCallback;
            final int n24 = 5;
            final Object[] array6 = new Object[n24];
            final int n25 = 0;
            final int n26 = targetTagForTouch;
            final Integer n27 = n26;
            array6[n25] = n27;
            final int n28 = 1;
            final float n29 = n9;
            final Float n30 = n29;
            array6[n28] = n30;
            final int n31 = 2;
            final float n32 = n15;
            final Float n33 = n32;
            array6[n31] = n33;
            final int n34 = 3;
            final float n35 = n19;
            final Float n36 = n35;
            array6[n34] = n36;
            final int n37 = 4;
            final float n38 = n23;
            final Float n39 = n38;
            array6[n37] = n39;
            callback.invoke(array6);
            return;
        }
        catch (IllegalViewOperationException ex) {
            this.mCallback.invoke(new Object[0]);
            return;
        }
        try {
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation = this;
            final UIViewOperationQueue uiViewOperationQueue = uiViewOperationQueue$FindTargetForTouchOperation.this$0;
            final NativeViewHierarchyManager nativeViewHierarchyManager = uiViewOperationQueue.mNativeViewHierarchyManager;
            final int n3 = targetTagForTouch;
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation2 = this;
            final UIViewOperationQueue uiViewOperationQueue2 = uiViewOperationQueue$FindTargetForTouchOperation2.this$0;
            final int[] array = uiViewOperationQueue2.mMeasureBuffer;
            nativeViewHierarchyManager.measure(n3, array);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation3 = this;
            final UIViewOperationQueue uiViewOperationQueue3 = uiViewOperationQueue$FindTargetForTouchOperation3.this$0;
            final int[] array2 = uiViewOperationQueue3.mMeasureBuffer;
            final int n4 = 0;
            final int n5 = array2[n4];
            final float n6 = n5;
            final float n7 = n;
            final float n8 = n6 - n7;
            final float n9 = PixelUtil.toDIPFromPixel(n8);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation4 = this;
            final UIViewOperationQueue uiViewOperationQueue4 = uiViewOperationQueue$FindTargetForTouchOperation4.this$0;
            final int[] array3 = uiViewOperationQueue4.mMeasureBuffer;
            final int n10 = 1;
            final int n11 = array3[n10];
            final float n12 = n11;
            final float n13 = n2;
            final float n14 = n12 - n13;
            final float n15 = PixelUtil.toDIPFromPixel(n14);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation5 = this;
            final UIViewOperationQueue uiViewOperationQueue5 = uiViewOperationQueue$FindTargetForTouchOperation5.this$0;
            final int[] array4 = uiViewOperationQueue5.mMeasureBuffer;
            final int n16 = 2;
            final int n17 = array4[n16];
            final float n18 = n17;
            final float n19 = PixelUtil.toDIPFromPixel(n18);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation6 = this;
            final UIViewOperationQueue uiViewOperationQueue6 = uiViewOperationQueue$FindTargetForTouchOperation6.this$0;
            final int[] array5 = uiViewOperationQueue6.mMeasureBuffer;
            final int n20 = 3;
            final int n21 = array5[n20];
            final float n22 = n21;
            final float n23 = PixelUtil.toDIPFromPixel(n22);
            final UIViewOperationQueue$FindTargetForTouchOperation uiViewOperationQueue$FindTargetForTouchOperation7 = this;
            final Callback callback = uiViewOperationQueue$FindTargetForTouchOperation7.mCallback;
            final int n24 = 5;
            final Object[] array6 = new Object[n24];
            final int n25 = 0;
            final int n26 = targetTagForTouch;
            final Integer n27 = n26;
            array6[n25] = n27;
            final int n28 = 1;
            final float n29 = n9;
            final Float n30 = n29;
            array6[n28] = n30;
            final int n31 = 2;
            final float n32 = n15;
            final Float n33 = n32;
            array6[n31] = n33;
            final int n34 = 3;
            final float n35 = n19;
            final Float n36 = n35;
            array6[n34] = n36;
            final int n37 = 4;
            final float n38 = n23;
            final Float n39 = n38;
            array6[n37] = n39;
            callback.invoke(array6);
        }
        catch (IllegalViewOperationException ex2) {
            this.mCallback.invoke(new Object[0]);
        }
    }
}
