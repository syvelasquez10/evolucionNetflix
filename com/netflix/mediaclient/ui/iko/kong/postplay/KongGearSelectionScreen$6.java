// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import java.util.Iterator;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import java.util.List;
import android.widget.TextView;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.text.Html;
import com.netflix.mediaclient.Log;

class KongGearSelectionScreen$6 implements Runnable
{
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    KongGearSelectionScreen$6(final KongGearSelectionScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.postPlayManager.isPostPlayPaused()) {
            Log.d("KongGearSelectionScreen", "Post play is in paused state. Ignoring request to countdown timer for gear selection.");
            return;
        }
        int access$1000;
        if (this.this$0.finishTimeCounterSeconds > 0) {
            access$1000 = this.this$0.finishTimeCounterSeconds;
        }
        else {
            access$1000 = 0;
        }
        this.this$0.gear1Timer.setText((CharSequence)Html.fromHtml(String.format(this.this$0.timerString, String.valueOf(access$1000))));
        ViewUtils.setVisibleOrGone((View)this.this$0.gear1Timer, true);
        if (this.this$0.finishTimeCounterSeconds-- < 0) {
            this.this$0.gear1Timer.performClick();
            ViewUtils.setVisibleOrInvisible((View)this.this$0.gear1Timer, false);
            return;
        }
        this.this$0.getHandler().postDelayed((Runnable)this, 1000L);
    }
}
