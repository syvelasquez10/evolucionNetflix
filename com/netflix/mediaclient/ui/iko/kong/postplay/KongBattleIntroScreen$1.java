// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ObjectAnimator;
import android.text.Html;
import android.graphics.Paint;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.util.StringUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import com.netflix.mediaclient.util.OnAnimationEndListener;

class KongBattleIntroScreen$1 extends OnAnimationEndListener
{
    final /* synthetic */ KongBattleIntroScreen this$0;
    
    KongBattleIntroScreen$1(final KongBattleIntroScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        this.this$0.postPlayManager.setTitleText(this.this$0.battleOptInHeaderString);
        this.this$0.battleIntroCompoundView.setScaleX(this.this$0.scaleDownWidth);
        this.this$0.battleIntroCompoundView.setScaleY(this.this$0.scaleDownHeight);
        this.this$0.battleIntroContainer.setTranslationX(-this.this$0.battleEpisodeSpacing);
        this.this$0.battleIntroCompoundView.setAlpha(0.0f);
        ViewUtils.setVisibleOrInvisible((View)this.this$0.battleCard, !this.this$0.hasWatchedAllBattlesForEpisode);
        if (StringUtils.isNotEmpty(this.this$0.resultDataBattleCardImageUrl)) {
            ViewUtils.setVisibleOrInvisible(this.this$0.battleCardComposite, this.this$0.hasWatchedAllBattlesForEpisode);
        }
        if (this.this$0.nextEpisodeVideoId > 0) {
            this.this$0.nextEpisodeContainer.setVisibility(0);
            this.this$0.nextEpisodeContainer.setScaleX(1.0f);
            this.this$0.nextEpisodeContainer.setScaleY(1.0f);
            this.this$0.nextEpisodeContainer.setTranslationX(this.this$0.battleEpisodeSpacing);
            this.this$0.nextEpisodeContainer.setAlpha(0.0f);
        }
    }
}
