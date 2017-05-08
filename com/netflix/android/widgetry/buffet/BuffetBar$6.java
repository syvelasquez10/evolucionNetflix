// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$Behavior;
import android.support.design.widget.SwipeDismissBehavior$OnDismissListener;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.graphics.Typeface;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.support.design.widget.CoordinatorLayout;
import android.content.res.TypedArray;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.AnimationUtils;
import com.netflix.android.widgetry.R$anim;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.ViewCompat;
import android.os.Build$VERSION;
import com.netflix.android.widgetry.R$layout;
import android.view.LayoutInflater;
import android.os.Handler$Callback;
import android.os.Looper;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.appcompat.R$attr;
import android.view.ViewGroup;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import android.os.Handler;
import android.view.animation.Interpolator;
import android.annotation.SuppressLint;
import android.view.View;

class BuffetBar$6 implements BuffetBar$BuffetLayout$OnLayoutChangeListener
{
    final /* synthetic */ BuffetBar this$0;
    final /* synthetic */ boolean val$animate;
    
    BuffetBar$6(final BuffetBar this$0, final boolean val$animate) {
        this.this$0 = this$0;
        this.val$animate = val$animate;
    }
    
    @Override
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4) {
        this.this$0.mView.setOnLayoutChangeListener(null);
        if (this.val$animate && this.this$0.shouldAnimate()) {
            this.this$0.animateViewIn();
            return;
        }
        this.this$0.onViewShown();
    }
}
