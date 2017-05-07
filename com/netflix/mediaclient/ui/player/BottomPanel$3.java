// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnTouchListener;
import android.widget.SeekBar$OnSeekBarChangeListener;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.ImageButton;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.widget.TextView;
import android.view.View;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;

class BottomPanel$3 implements Animator$AnimatorListener
{
    final /* synthetic */ BottomPanel this$0;
    final /* synthetic */ Runnable val$onFinishRunnable;
    
    BottomPanel$3(final BottomPanel this$0, final Runnable val$onFinishRunnable) {
        this.this$0 = this$0;
        this.val$onFinishRunnable = val$onFinishRunnable;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.currentTime.show();
        this.this$0.extraSeekbarHandler.post(this.val$onFinishRunnable);
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
        this.this$0.currentTime.hide();
    }
}
