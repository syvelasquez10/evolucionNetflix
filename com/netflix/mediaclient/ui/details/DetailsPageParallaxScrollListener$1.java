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
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;

class DetailsPageParallaxScrollListener$1 implements Animator$AnimatorListener
{
    final /* synthetic */ DetailsPageParallaxScrollListener this$0;
    
    DetailsPageParallaxScrollListener$1(final DetailsPageParallaxScrollListener this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.animating = false;
        this.this$0.trackingView.setTranslationX((float)this.this$0.trackerViewfinalXPosition);
        this.this$0.trackingView.setTranslationY((float)(((NetflixActivity)this.this$0.trackingView.getContext()).getActionBarHeight() / 2));
        this.this$0.trackingView.animate().alpha(1.0f).setDuration(300L).setListener((Animator$AnimatorListener)new DetailsPageParallaxScrollListener$1$1(this));
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.animating = true;
    }
}
