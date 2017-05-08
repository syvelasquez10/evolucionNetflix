// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import android.view.View$MeasureSpec;
import com.facebook.react.uimanager.MeasureSpecAssertions;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import com.facebook.infer.annotation.Assertions;
import android.content.Context;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.touch.OnInterceptTouchEventListener;
import android.graphics.Rect;
import android.view.ViewGroup$LayoutParams;
import com.facebook.react.uimanager.ReactPointerEventsView;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.touch.ReactHitSlopView;
import android.view.ViewGroup;
import android.view.View;
import android.view.View$OnLayoutChangeListener;

final class ReactViewGroup$ChildrenLayoutChangeListener implements View$OnLayoutChangeListener
{
    private final ReactViewGroup mParent;
    
    private ReactViewGroup$ChildrenLayoutChangeListener(final ReactViewGroup mParent) {
        this.mParent = mParent;
    }
    
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (this.mParent.getRemoveClippedSubviews()) {
            this.mParent.updateSubviewClipStatus(view);
        }
    }
}
