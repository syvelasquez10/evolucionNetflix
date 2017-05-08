// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.Asset;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.service.net.LogMobileType;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.bandwidthsetting.BandwidthUtility;
import android.content.res.Resources;
import android.text.Html;
import com.netflix.mediaclient.util.MdxUtils;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.os.Build$VERSION;
import android.animation.ObjectAnimator;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.os.Handler;
import com.netflix.mediaclient.util.Coppola1Utils;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.Log;

class CoppolaLoadingDecorator$1 implements PlayerFragment$OnPlaybackStateListener
{
    final /* synthetic */ CoppolaLoadingDecorator this$0;
    
    CoppolaLoadingDecorator$1(final CoppolaLoadingDecorator this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPlaybackRestarting() {
        Log.v("CoppolaLoadingDecorator", "onPlaybackRestarting()");
        this.this$0.hideLaunchButtons();
        this.this$0.showProgressAndTextIndicator(true);
        this.this$0.horzDispImg.setVisibility(0);
        this.this$0.animateProgressBar();
        AnimationUtils.startViewAppearanceAnimation(this.this$0.loadingIndicator, true, 200);
        AnimationUtils.startViewAppearanceAnimation((View)this.this$0.loadingTextIndicator, true, 200);
        AnimationUtils.startViewAppearanceAnimation(this.this$0.gradient, true, 200);
    }
    
    @Override
    public void onPlaybackStarted() {
        Log.v("CoppolaLoadingDecorator", "onPlaybackStarted()");
        this.this$0.hideLaunchButtons();
        this.this$0.getController().getNetflixActivity().getNetflixActionBar().getToolbar().setBackground((Drawable)new ColorDrawable(0));
        this.this$0.animateUI(false);
        if (!this.this$0.getController().isPaused()) {
            this.this$0.playerScreen.setMediaImage(false);
        }
        Coppola1Utils.unlockOrientationIfNeeded(this.this$0.getController().getActivity());
    }
    
    @Override
    public void onPlaybackStopped() {
        Log.v("CoppolaLoadingDecorator", "onPlaybackStopped()");
        this.this$0.showProgressAndTextIndicator(false);
        AnimationUtils.startViewAppearanceAnimation((View)this.this$0.horzDispImg, true, 200);
    }
}
