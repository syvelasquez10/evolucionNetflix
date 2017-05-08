// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import java.util.List;
import android.widget.TextView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class KongGearSelectionScreen$5 extends OnAnimationEndListener
{
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    KongGearSelectionScreen$5(final KongGearSelectionScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.animationEndUIState();
        this.this$0.gear1Group.setClickable(true);
        this.this$0.gear2Group.setClickable(true);
        if (this.this$0.autoPlayInterval < 0) {
            return;
        }
        this.this$0.finishTimeCounterSeconds = this.this$0.autoPlayInterval;
        this.this$0.setCountdownTimerText();
    }
}
