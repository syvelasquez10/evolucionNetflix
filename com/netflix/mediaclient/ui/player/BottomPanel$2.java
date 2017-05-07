// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.SeekBar;
import com.netflix.mediaclient.Log;
import android.animation.TimeInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.android.widget.NetflixSeekBar;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnTouchListener;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.widget.SeekBar$OnSeekBarChangeListener;
import com.netflix.mediaclient.android.widget.TimelineSeekBar;
import android.widget.ImageButton;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.widget.TextView;
import android.view.View;
import android.animation.ValueAnimator;
import android.widget.RelativeLayout$LayoutParams;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class BottomPanel$2 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ BottomPanel this$0;
    final /* synthetic */ RelativeLayout$LayoutParams val$params;
    
    BottomPanel$2(final BottomPanel this$0, final RelativeLayout$LayoutParams val$params) {
        this.this$0 = this$0;
        this.val$params = val$params;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$params.leftMargin = (int)valueAnimator.getAnimatedValue();
        this.this$0.extraSeekbarHandler.requestLayout();
    }
}
