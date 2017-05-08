// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.util.Property;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.animation.TimeInterpolator;
import android.animation.AnimatorSet;
import com.netflix.mediaclient.ui.iko.wordparty.WPConstants;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.view.animation.Interpolator;
import android.graphics.drawable.BitmapDrawable;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel$WPAudio;
import android.widget.ImageView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPCardView$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPCardView this$0;
    
    WPCardView$2(final WPCardView this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationCancel(final Animator animator) {
        this.onAnimationEnd(animator);
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.updateDrawableBitmap();
        if (this.this$0.cardClickListener != null) {
            this.this$0.cardClickListener.onCardClickEnd(this.this$0);
        }
    }
}
