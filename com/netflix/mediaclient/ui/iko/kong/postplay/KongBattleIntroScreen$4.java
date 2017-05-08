// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ObjectAnimator;
import android.text.Html;
import android.graphics.Paint;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.LinearInterpolator;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.animation.Interpolator;
import android.view.View$OnClickListener;
import android.animation.Animator$AnimatorListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class KongBattleIntroScreen$4 extends OnAnimationEndListener
{
    final /* synthetic */ KongBattleIntroScreen this$0;
    
    KongBattleIntroScreen$4(final KongBattleIntroScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (!this.this$0.postPlayManager.isActivityValid()) {
            if (Log.isLoggable()) {
                Log.d("KongBattleIntroScreen", "Activity is already destroyed, ignore request!");
            }
        }
        else {
            this.this$0.postPlayManager.setTitleText(this.this$0.battleOptInHeaderString);
            if (this.this$0.nextEpisodeVideoId > 0) {
                ViewUtils.setVisibleOrGone((View)this.this$0.nextEpisodeContainer, true);
                this.this$0.nextEpisodeContainer.setAlpha(0.0f);
            }
        }
    }
}
