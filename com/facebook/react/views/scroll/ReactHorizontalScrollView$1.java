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
import android.view.View;
import android.graphics.Canvas;
import android.annotation.TargetApi;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import android.widget.HorizontalScrollView;
import android.view.ViewGroup;

class ReactHorizontalScrollView$1 implements Runnable
{
    private boolean mSnappingToPage;
    final /* synthetic */ ReactHorizontalScrollView this$0;
    
    ReactHorizontalScrollView$1(final ReactHorizontalScrollView this$0) {
        this.this$0 = this$0;
        this.mSnappingToPage = false;
    }
    
    @Override
    public void run() {
        int n = 0;
        if (this.this$0.mActivelyScrolling) {
            this.this$0.mActivelyScrolling = false;
            this.this$0.postOnAnimationDelayed((Runnable)this, 20L);
            return;
        }
        if (this.this$0.mPagingEnabled && !this.mSnappingToPage) {
            this.mSnappingToPage = true;
            this.this$0.smoothScrollToPage(0);
        }
        else {
            n = 1;
        }
        if (n != 0) {
            if (this.this$0.mSendMomentumEvents) {
                ReactScrollViewHelper.emitScrollMomentumEndEvent((ViewGroup)this.this$0);
            }
            this.this$0.mPostTouchRunnable = null;
            this.this$0.disableFpsListener();
            return;
        }
        this.this$0.postOnAnimationDelayed((Runnable)this, 20L);
    }
}
