// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget.advisor;

import android.animation.TimeInterpolator;
import com.netflix.mediaclient.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.animation.Animator$AnimatorListener;
import android.view.View;
import android.view.animation.Interpolator;
import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class TwoLineAdvisor$1 extends OnAnimationEndListener
{
    final /* synthetic */ TwoLineAdvisor this$0;
    
    TwoLineAdvisor$1(final TwoLineAdvisor this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.cancelAnimation();
        this.this$0.resetViews();
        this.this$0.dismiss();
    }
}
