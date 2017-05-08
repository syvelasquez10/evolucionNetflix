// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import java.util.Collection;
import android.animation.AnimatorSet;
import com.netflix.mediaclient.util.DeviceUtils;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import android.widget.TextView;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.Iterator;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.ImageView;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class KongPowerUpScreen$4 extends AnimatorListenerAdapter
{
    final /* synthetic */ KongPowerUpScreen this$0;
    
    KongPowerUpScreen$4(final KongPowerUpScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onAnimationCancel(final Animator animator) {
        super.onAnimationCancel(animator);
        this.onAnimationEnd(animator);
    }
    
    public void onAnimationEnd(final Animator animator) {
        super.onAnimationEnd(animator);
        this.this$0.powerUpTitleView.setAlpha(0.0f);
        this.this$0.whiteFlareImage.setAlpha(0.0f);
        this.this$0.smAvatarImage.setAlpha(0.0f);
        final Iterator<ImageView> iterator = this.this$0.gearItemsImageViewList.iterator();
        while (iterator.hasNext()) {
            iterator.next().setAlpha(0.0f);
        }
        this.this$0.powerUpAvatarImage.setAlpha(0.0f);
        ViewUtils.setVisibleOrGone((View)this.this$0.powerUpContainer, false);
        this.this$0.handler.post((Runnable)new KongPowerUpScreen$4$1(this));
    }
}
