// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable$Orientation;
import com.netflix.mediaclient.Log;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.Toolbar$LayoutParams;
import java.util.Date;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.netflix.mediaclient.util.AndroidUtils;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;
import android.support.v7.widget.RecyclerView$OnScrollListener;

class DetailsPageParallaxScrollListener$2 implements DetailsPageParallaxScrollListener$ItrackingViewAnimationActions
{
    final /* synthetic */ DetailsPageParallaxScrollListener this$0;
    
    DetailsPageParallaxScrollListener$2(final DetailsPageParallaxScrollListener this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onAnimationEnd() {
        this.this$0.reAttachTrackingViewOriginalParent();
    }
}
