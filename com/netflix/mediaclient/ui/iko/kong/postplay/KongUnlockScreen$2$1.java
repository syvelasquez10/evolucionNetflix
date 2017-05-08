// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.BounceInterpolator;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.mediaclient.util.OnAnimationEndListener;
import java.util.List;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;

class KongUnlockScreen$2$1 implements Runnable
{
    final /* synthetic */ KongUnlockScreen$2 this$1;
    
    KongUnlockScreen$2$1(final KongUnlockScreen$2 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.this$0.isFirstGearUnlockingComplete) {
            this.this$1.this$0.playBattleTitleSound();
            this.this$1.this$0.postPlayManager.showBattleIntro();
            return;
        }
        this.this$1.this$0.isFirstGearUnlockingComplete = true;
        this.this$1.this$0.startAnimation(this.this$1.this$0.gear2Item);
    }
}
