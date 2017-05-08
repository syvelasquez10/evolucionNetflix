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
import com.netflix.mediaclient.util.CoppolaUtils;
import com.netflix.mediaclient.util.ViewUtils;
import android.animation.Animator;
import android.animation.Animator$AnimatorListener;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.animation.TimeInterpolator;
import android.view.animation.LinearInterpolator;
import android.os.Build$VERSION;
import android.animation.ObjectAnimator;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.os.Handler;
import android.view.View;

public class CoppolaLoadingDecorator extends PlayScreenDecorator
{
    private static final int AUTOPLAY_DELAY_TIMEOUT_MS = 6000;
    private static final int PLAYER_ANIMATION_DURATION_MS = 200;
    private static final String TAG = "CoppolaLoadingDecorator";
    private View blackBackground;
    long finishTime;
    private View gradient;
    private Handler handler;
    private AdvancedImageView horzDispImg;
    private View launchButton;
    private View loadingIndicator;
    private TextView loadingTextIndicator;
    private View oldLaunchButton;
    private CoppolaLoadingDecorator$UpdateLoadingTextRunnable updateLoadingTextRunnable;
    
    public CoppolaLoadingDecorator(final PlayScreen playScreen) {
        super(playScreen);
        this.handler = new Handler();
        this.updateUI((ViewGroup)this.getController().getView());
    }
    
    public CoppolaLoadingDecorator(final PlayScreenDecorator playScreenDecorator) {
        super(playScreenDecorator);
        this.handler = new Handler();
        this.updateUI((ViewGroup)this.getController().getView());
    }
    
    private void animateProgressBar() {
        Log.v("CoppolaLoadingDecorator", "animateProgressBar()");
        final ObjectAnimator ofFloat = ObjectAnimator.ofFloat((Object)this.loadingIndicator, "translationX", new float[] { -(int)this.getController().getResources().getDimension(2131296501), 0.0f });
        ofFloat.setDuration(700L);
        if (Build$VERSION.SDK_INT >= 18) {
            ofFloat.setAutoCancel(true);
        }
        ofFloat.setInterpolator((TimeInterpolator)new LinearInterpolator());
        ofFloat.setRepeatMode(1);
        ofFloat.setRepeatCount(-1);
        ofFloat.start();
    }
    
    private void animateUI(final boolean b) {
        Log.v("CoppolaLoadingDecorator", "animateUI()");
        final Animator startViewAppearanceAnimation = AnimationUtils.startViewAppearanceAnimation((View)this.horzDispImg, b, 200);
        if (!b) {
            startViewAppearanceAnimation.addListener((Animator$AnimatorListener)new CoppolaLoadingDecorator$4(this));
        }
        if (b) {
            this.animateProgressBar();
        }
        AnimationUtils.startViewAppearanceAnimation(this.loadingIndicator, b, 200);
        AnimationUtils.startViewAppearanceAnimation((View)this.loadingTextIndicator, b, 200);
        AnimationUtils.startViewAppearanceAnimation(this.gradient, b, 200);
    }
    
    private void hideLaunchButtons() {
        ViewUtils.setVisibleOrGone(this.launchButton, false);
        ViewUtils.setVisibleOrGone(this.oldLaunchButton, false);
    }
    
    private boolean isDelayedAutoplay() {
        return !CoppolaUtils.isAutoplay((Context)this.getController().getActivity()) || CoppolaUtils.showCountdownTimer((Context)this.getController().getActivity());
    }
    
    private void launchPlayback() {
        Log.v("CoppolaLoadingDecorator", "launchPlayback()");
        this.finishTime = 0L;
        if (!this.getController().isMDXTargetSelected()) {
            ViewUtils.setVisibleOrGone(this.launchButton, false);
            if (CoppolaUtils.isNewPlayerExperience((Context)this.getController().getActivity())) {
                if (!this.getController().wasBufferingComplete()) {
                    ((FrameLayout$LayoutParams)this.loadingTextIndicator.getLayoutParams()).gravity = 80;
                    this.loadingTextIndicator.setText((CharSequence)"");
                    if (!ViewUtils.isVisible(this.loadingIndicator) || !ViewUtils.isVisible((View)this.loadingTextIndicator)) {
                        this.animateProgressBar();
                        AnimationUtils.startViewAppearanceAnimation(this.loadingIndicator, true, 200);
                        AnimationUtils.startViewAppearanceAnimation((View)this.loadingTextIndicator, true, 200);
                    }
                }
                else {
                    this.loadingTextIndicator.setVisibility(8);
                }
            }
        }
        this.getController().launchPlayback();
    }
    
    private void updateLoadingText(final String s, final boolean b, String text, final int n, final boolean b2) {
        if (!this.getController().isActivityValid()) {
            return;
        }
        if (Log.isLoggable()) {
            Log.v("CoppolaLoadingDecorator", "updateLoadingText() - title: " + s);
        }
        final Resources resources = this.getController().getActivity().getResources();
        final long currentTimeMillis = System.currentTimeMillis();
        if (MdxUtils.isCurrentMdxTargetAvailable(this.getController().getServiceManager())) {
            this.showProgressAndTextIndicator(false);
            return;
        }
        if (CoppolaUtils.showCountdownTimer((Context)this.getController().getActivity()) && this.finishTime >= currentTimeMillis && this.getController().isInPortrait() && ViewUtils.isVisible(this.launchButton)) {
            final int n2 = (int)((this.finishTime - currentTimeMillis) / 1000L);
            String s2;
            if (n2 > 9) {
                s2 = String.valueOf(n2);
            }
            else {
                s2 = "0" + n2;
            }
            String s3;
            if (b) {
                s3 = resources.getString(2131165531, new Object[] { n, s2 });
            }
            else {
                s3 = resources.getString(2131165571, new Object[] { s2 });
            }
            this.loadingTextIndicator.setText((CharSequence)Html.fromHtml(s3));
            ((FrameLayout$LayoutParams)this.loadingTextIndicator.getLayoutParams()).gravity = 17;
            if (n2 == 0) {
                this.loadingTextIndicator.setVisibility(8);
                this.launchPlayback();
                return;
            }
            if (this.updateLoadingTextRunnable != null) {
                this.handler.removeCallbacks((Runnable)this.updateLoadingTextRunnable);
            }
            this.updateLoadingTextRunnable = new CoppolaLoadingDecorator$UpdateLoadingTextRunnable(this, s, b, text, n, b2);
            this.handler.postDelayed((Runnable)this.updateLoadingTextRunnable, 1000L);
        }
        else {
            if (b) {
                text = resources.getString(2131165528, new Object[] { text, n, s });
                if (b2) {
                    text = resources.getString(2131165527, new Object[] { s });
                }
                this.loadingTextIndicator.setText((CharSequence)text);
                return;
            }
            this.loadingTextIndicator.setText(2131165554);
        }
    }
    
    private void updateUI(final ViewGroup viewGroup) {
        Log.v("CoppolaLoadingDecorator", "Updating UI...");
        AnimationUtils.startViewAppearanceAnimation(this.getController().getView(), true);
        this.getController().getActivity().getLayoutInflater().inflate(2130903200, viewGroup);
        this.horzDispImg = (AdvancedImageView)viewGroup.findViewById(2131624476);
        this.loadingIndicator = viewGroup.findViewById(2131624482);
        this.loadingTextIndicator = (TextView)viewGroup.findViewById(2131624479);
        this.gradient = viewGroup.findViewById(2131624477);
        this.blackBackground = viewGroup.findViewById(2131624475);
        this.animateProgressBar();
        boolean b;
        if (BandwidthUtility.isPlaybackInWifiOnly((Context)this.playerScreen.getController()) && ConnectivityUtils.getConnectionType((Context)this.playerScreen.getController()) != LogMobileType.WIFI) {
            b = true;
        }
        else {
            b = false;
        }
        if (b) {
            this.showProgressAndTextIndicator(false);
        }
        this.getController().setOnStartedPlaybackListener(new CoppolaLoadingDecorator$1(this));
        this.launchButton = viewGroup.findViewById(2131624480);
        final View launchButton = this.launchButton;
        int visibility;
        if (this.getController().isInPortrait() && this.isDelayedAutoplay() && !b && CoppolaUtils.isNewPlayerExperience((Context)this.getController().getActivity())) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        launchButton.setVisibility(visibility);
        if (!CoppolaUtils.isNewPlayerExperience((Context)this.getController().getActivity())) {
            this.showProgressAndTextIndicator(false);
        }
        else if (this.isDelayedAutoplay() && !b) {
            if (this.getController().isInPortrait()) {
                this.launchButton.setOnClickListener((View$OnClickListener)new CoppolaLoadingDecorator$2(this));
                if (!CoppolaUtils.isAutoplay((Context)this.getController().getActivity())) {
                    this.showProgressAndTextIndicator(false);
                }
                else {
                    ViewUtils.setVisibleOrGone(this.loadingIndicator, false);
                }
            }
            else if (CoppolaUtils.showCountdownTimer((Context)this.getController().getActivity())) {
                this.getController().launchPlayback();
            }
        }
        this.updateViewMargins(this.getController().isInPortrait());
        if (!CoppolaUtils.isNewPlayerExperience((Context)this.getController().getActivity()) && !CoppolaUtils.shouldInjectPlayerFragment((Context)this.getController().getActivity())) {
            this.getController().getActivity().setRequestedOrientation(1);
            (this.oldLaunchButton = viewGroup.findViewById(2131624481)).setVisibility(0);
            this.oldLaunchButton.setOnClickListener((View$OnClickListener)new CoppolaLoadingDecorator$3(this));
        }
    }
    
    private void updateViewMargins(final boolean b) {
        if (ViewUtils.isVisible((View)this.loadingTextIndicator)) {
            final Resources resources = this.getController().getResources();
            int leftMargin;
            if (b) {
                leftMargin = (int)resources.getDimension(2131296497);
            }
            else {
                leftMargin = (int)resources.getDimension(2131296528);
            }
            ((ViewGroup$MarginLayoutParams)this.loadingTextIndicator.getLayoutParams()).leftMargin = leftMargin;
        }
    }
    
    @Override
    public void onAssetUpdated(final Asset asset) {
        super.onAssetUpdated(asset);
        Log.v("CoppolaLoadingDecorator", "onAssetUpdated()");
        this.updateLoadingText(asset.getTitle(), asset.isEpisode(), asset.getSeasonAbbrSeqLabel(), asset.getEpisodeNumber(), asset.isNSRE());
    }
    
    @Override
    public void onConfigurationChanged(final Configuration configuration) {
        boolean b = true;
        super.onConfigurationChanged(configuration);
        Log.v("CoppolaLoadingDecorator", "onConfigurationChanged()");
        if (ViewUtils.isVisible(this.launchButton) && this.isDelayedAutoplay() && configuration.orientation == 2 && CoppolaUtils.isNewPlayerExperience((Context)this.getController().getActivity())) {
            this.launchPlayback();
        }
        if (configuration.orientation != 1) {
            b = false;
        }
        this.updateViewMargins(b);
    }
    
    @Override
    public void onVideoDetailsFetched(final VideoDetails videoDetails) {
        super.onVideoDetailsFetched(videoDetails);
        Log.v("CoppolaLoadingDecorator", "onVideoDetailsFetched()");
        NetflixActivity.getImageLoader((Context)this.getController().getActivity()).showImg(this.horzDispImg, videoDetails.getStoryDispUrl(), IClientLogging$AssetType.boxArt, "boxart", BrowseExperience.getImageLoaderConfig(), true);
        if (CoppolaUtils.showCountdownTimer((Context)this.getController().getActivity())) {
            this.finishTime = System.currentTimeMillis() + 6000L;
        }
        this.updateLoadingText(videoDetails.getPlayable().getPlayableTitle(), videoDetails.getType() == VideoType.SHOW, videoDetails.getPlayable().getSeasonAbbrSeqLabel(), videoDetails.getPlayable().getEpisodeNumber(), videoDetails.isNSRE());
    }
    
    public void showLaunchButtons() {
        ViewUtils.setVisibleOrGone(this.launchButton, true);
        ViewUtils.setVisibleOrGone(this.oldLaunchButton, true);
    }
    
    public void showProgressAndTextIndicator(final boolean b) {
        if (b) {
            this.animateProgressBar();
        }
        ViewUtils.setVisibleOrGone(this.loadingIndicator, b);
        ViewUtils.setVisibleOrGone((View)this.loadingTextIndicator, b);
    }
}
