// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.Toolbar$LayoutParams;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;

class DetailsPageParallaxScrollListener$3 implements Animator$AnimatorListener
{
    final /* synthetic */ DetailsPageParallaxScrollListener this$0;
    final /* synthetic */ DetailsPageParallaxScrollListener$ItrackingViewAnimationActions val$animationActions;
    
    DetailsPageParallaxScrollListener$3(final DetailsPageParallaxScrollListener this$0, final DetailsPageParallaxScrollListener$ItrackingViewAnimationActions val$animationActions) {
        this.this$0 = this$0;
        this.val$animationActions = val$animationActions;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.animating = false;
        this.this$0.trackingView.animate().alpha(1.0f).setDuration((long)this.this$0.getTrackerViewLatchFadeinDuration()).setListener((Animator$AnimatorListener)new DetailsPageParallaxScrollListener$3$1(this));
        this.val$animationActions.onAnimationEnd();
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.animating = true;
    }
}
