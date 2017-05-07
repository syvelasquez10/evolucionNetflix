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
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.Toolbar$LayoutParams;
import android.support.v7.widget.Toolbar;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;
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
        this.this$0.trackingView.animate().alpha(1.0f).setDuration((long)this.this$0.getTrackerViewLatchFadeinDuration()).setListener((Animator$AnimatorListener)new DetailsPageParallaxScrollListener$1$1(this));
        this.this$0.detachTrackingViewFromOriginalParent();
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.animating = true;
    }
}
