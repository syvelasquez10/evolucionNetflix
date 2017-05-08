// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator$AnimatorListener;
import android.widget.RelativeLayout;
import android.view.animation.Interpolator;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class ContentAdvisoryController$1 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ ContentAdvisoryController this$0;
    
    ContentAdvisoryController$1(final ContentAdvisoryController this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        this.this$0.mNetflixBarHeight = this.this$0.mNetflixBarView.getMeasuredHeight();
        this.this$0.mNetflixBarView.getViewTreeObserver().removeOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
