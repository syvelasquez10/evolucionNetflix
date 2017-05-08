// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ObjectAnimator;
import android.text.Html;
import com.netflix.mediaclient.util.ViewUtils;
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
import com.netflix.mediaclient.Log;
import android.view.animation.LinearInterpolator;
import android.graphics.BitmapFactory$Options;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.OnAnimationEndListener;
import android.view.animation.Interpolator;
import android.animation.Animator$AnimatorListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import android.widget.TextView;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.view.View;
import android.view.View$OnClickListener;

class KongBattleIntroScreen$5 implements View$OnClickListener
{
    final /* synthetic */ KongBattleIntroScreen this$0;
    
    KongBattleIntroScreen$5(final KongBattleIntroScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (this.this$0.handler != null) {
            this.this$0.handler.removeCallbacksAndMessages((Object)null);
        }
        final boolean hasTimerExpired = this.this$0.hasTimerExpired();
        if (!hasTimerExpired) {
            this.this$0.postPlayManager.setUserInteraction();
        }
        final int battleWinVideoId = this.this$0.postPlayManager.getBattleWinVideoId();
        final int battleLoseVideoId = this.this$0.postPlayManager.getBattleLoseVideoId();
        int battleWinTrackId = this.this$0.postPlayManager.getBattleWinTrackId();
        final int battleLoseTrackId = this.this$0.postPlayManager.getBattleLoseTrackId();
        int n;
        if (!this.this$0.isBattleResultWin) {
            n = battleWinVideoId;
        }
        else {
            n = battleLoseVideoId;
        }
        if (this.this$0.isBattleResultWin) {
            battleWinTrackId = battleLoseTrackId;
        }
        if (this.this$0.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT && !this.this$0.hasWatchedAllBattlesForEpisode) {
            this.this$0.postPlayManager.startPlayback(n, battleWinTrackId, VideoType.MOVIE, hasTimerExpired);
            return;
        }
        if (this.this$0.postPlayManager.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.POWERUP || battleWinVideoId == battleLoseVideoId) {
            this.this$0.postPlayManager.startPlayback(battleWinVideoId, battleWinTrackId, VideoType.MOVIE, hasTimerExpired);
            return;
        }
        this.this$0.postPlayManager.showGearSelection();
    }
}
