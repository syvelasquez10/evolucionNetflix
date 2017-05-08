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
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;

class BuffetBar$7 extends ViewPropertyAnimatorListenerAdapter
{
    final /* synthetic */ BuffetBar this$0;
    
    BuffetBar$7(final BuffetBar this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd(final View view) {
        this.this$0.onViewShown();
    }
    
    @Override
    public void onAnimationStart(final View view) {
        this.this$0.mView.animateChildrenIn(70, 180);
    }
}
