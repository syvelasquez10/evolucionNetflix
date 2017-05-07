// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.view.animation.AlphaAnimation;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.Log;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView$OnScrollListener;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;

class DetailsPageParallaxScrollListener$1 implements Animation$AnimationListener
{
    final /* synthetic */ DetailsPageParallaxScrollListener this$0;
    
    DetailsPageParallaxScrollListener$1(final DetailsPageParallaxScrollListener this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animation animation) {
    }
    
    public void onAnimationRepeat(final Animation animation) {
    }
    
    public void onAnimationStart(final Animation animation) {
        this.this$0.trackingView.setTranslationX((float)this.this$0.trackerViewfinalXPosition);
        this.this$0.trackingView.setVisibility(0);
        this.this$0.trackingView.bringToFront();
    }
}
