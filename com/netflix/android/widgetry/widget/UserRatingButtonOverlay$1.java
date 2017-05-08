// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$dimen;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.android.widgetry.R$drawable;
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
import android.view.View;
import android.view.View$OnClickListener;

class UserRatingButtonOverlay$1 implements View$OnClickListener
{
    final /* synthetic */ UserRatingButtonOverlay this$0;
    
    UserRatingButtonOverlay$1(final UserRatingButtonOverlay this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.dismiss();
    }
}
