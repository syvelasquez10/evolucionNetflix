// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.graphics.drawable.ColorDrawable;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import com.facebook.react.uimanager.events.NativeGestureUtil;
import android.view.MotionEvent;
import android.graphics.Canvas;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import android.widget.OverScroller;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.graphics.Rect;
import java.lang.reflect.Field;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import android.view.ViewGroup$OnHierarchyChangeListener;
import android.view.View$OnLayoutChangeListener;
import android.widget.ScrollView;
import android.view.ViewGroup;

class ReactScrollView$1 implements Runnable
{
    final /* synthetic */ ReactScrollView this$0;
    
    ReactScrollView$1(final ReactScrollView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.mDoneFlinging) {
            this.this$0.mFlinging = false;
            this.this$0.disableFpsListener();
            ReactScrollViewHelper.emitScrollMomentumEndEvent((ViewGroup)this.this$0);
            return;
        }
        this.this$0.mDoneFlinging = true;
        this.this$0.postOnAnimationDelayed((Runnable)this, 20L);
    }
}
