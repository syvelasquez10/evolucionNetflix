// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator$AnimatorListener;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongSound;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.animation.LinearInterpolator;
import android.animation.TimeInterpolator;
import java.util.List;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.graphics.Bitmap;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel$KongCollectionItems;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View;
import android.view.View$OnClickListener;

class KongGearSelectionScreen$BattleResultClickListener implements View$OnClickListener
{
    final int index;
    final /* synthetic */ KongGearSelectionScreen this$0;
    
    public KongGearSelectionScreen$BattleResultClickListener(final KongGearSelectionScreen this$0, final int index) {
        this.this$0 = this$0;
        this.index = index;
    }
    
    public void onClick(final View view) {
        boolean b;
        if (this.this$0.finishTimeCounterSeconds <= 0) {
            b = true;
        }
        else {
            this.this$0.postPlayManager.setUserInteraction();
            b = false;
        }
        ViewUtils.setVisibleOrInvisible((View)this.this$0.gear1Timer, false);
        ViewUtils.setVisibleOrInvisible((View)this.this$0.gear2Timer, false);
        this.this$0.postPlayManager.removeHandlerCallbacksAndMessages();
        if (this.this$0.gearItemsList != null && this.index < this.this$0.gearItemsList.size()) {
            final int videoId = this.this$0.gearItemsList.get(this.index).getVideoId();
            final KongInteractivePostPlayManager postPlayManager = this.this$0.postPlayManager;
            int n;
            if (videoId == this.this$0.battleWinVideoId) {
                n = this.this$0.battleWinVideoId;
            }
            else {
                n = this.this$0.battleLoseVideoId;
            }
            postPlayManager.startPlayback(n, VideoType.MOVIE, b);
        }
    }
}
