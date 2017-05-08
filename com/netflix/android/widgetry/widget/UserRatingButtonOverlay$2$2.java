// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$dimen;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.android.widgetry.R$drawable;
import android.view.View$OnClickListener;
import com.netflix.android.widgetry.R$id;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$color;
import com.netflix.android.widgetry.R$layout;
import android.content.Context;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.widget.ImageView;
import android.view.animation.Interpolator;
import android.annotation.SuppressLint;
import android.view.ViewGroup;

class UserRatingButtonOverlay$2$2 implements UserRatingButtonOverlay$AnimationEndedCallback
{
    final /* synthetic */ UserRatingButtonOverlay$2 this$1;
    
    UserRatingButtonOverlay$2$2(final UserRatingButtonOverlay$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onAnimationEnded() {
        this.this$1.this$0.dismiss();
    }
}
