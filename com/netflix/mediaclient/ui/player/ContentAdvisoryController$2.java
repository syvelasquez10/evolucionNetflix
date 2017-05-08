// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.TimeInterpolator;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator$AnimatorListener;
import android.widget.RelativeLayout;
import android.view.animation.Interpolator;
import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class ContentAdvisoryController$2 extends OnAnimationEndListener
{
    final /* synthetic */ ContentAdvisoryController this$0;
    
    ContentAdvisoryController$2(final ContentAdvisoryController this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.hideAndReset();
    }
}
