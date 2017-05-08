// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.support.v4.view.ViewCompat;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.KeyEvent;
import android.animation.Animator$AnimatorListener;
import android.graphics.drawable.Drawable;
import android.graphics.Paint$Style;
import android.graphics.PathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.Xfermode;
import android.graphics.PorterDuffXfermode;
import android.graphics.PorterDuff$Mode;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View$OnClickListener;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Paint;
import android.widget.TextView;
import android.graphics.Rect;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class TooltipLayout$1 extends AnimatorListenerAdapter
{
    final /* synthetic */ TooltipLayout this$0;
    
    TooltipLayout$1(final TooltipLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.mAnimator = null;
        if (this.this$0.mCallback != null) {
            this.this$0.mCallback.onShown(null);
        }
    }
}
