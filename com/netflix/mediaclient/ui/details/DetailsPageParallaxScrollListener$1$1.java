// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.view.ViewGroup;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;

class DetailsPageParallaxScrollListener$1$1 implements Animator$AnimatorListener
{
    final /* synthetic */ DetailsPageParallaxScrollListener$1 this$1;
    
    DetailsPageParallaxScrollListener$1$1(final DetailsPageParallaxScrollListener$1 this$1) {
        this.this$1 = this$1;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$1.this$0.animating = false;
        this.this$1.this$0.trackingView.clearAnimation();
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
    }
}
