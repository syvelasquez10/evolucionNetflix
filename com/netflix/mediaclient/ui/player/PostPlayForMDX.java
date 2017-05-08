// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.model.leafs.PostPlayAction;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.netflix.model.leafs.PostPlayItem;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View$OnClickListener;
import android.content.res.Configuration;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import android.view.ViewGroup$MarginLayoutParams;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.app.Activity;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.ViewUtils$Visibility;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.view.View;

public final class PostPlayForMDX extends PostPlay
{
    private boolean mAutoPlayEnabled;
    protected View mAutoPlayView;
    protected View mMoreButton;
    protected View mPlayButton;
    protected View mStopButton;
    private TextView mTargetNameView;
    private int mTimer;
    protected int mTimerValue;
    protected TextView mTimerView;
    private final Runnable onEverySecond;
    
    PostPlayForMDX(final NetflixActivity netflixActivity) {
        super(netflixActivity);
        this.mAutoPlayEnabled = true;
        this.onEverySecond = new PostPlayForMDX$1(this);
        this.mTimerValue = netflixActivity.getResources().getInteger(2131492884);
        this.mOffsetMs = this.mTimerValue * 1000;
    }
    
    private Intent createIntent(final String s) {
        final ServiceManager serviceManager = this.mNetflixActivity.getServiceManager();
        if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
            return MdxAgent$Utils.createIntent((Context)this.mNetflixActivity, s, serviceManager.getMdx().getCurrentTarget());
        }
        return null;
    }
    
    private void init() {
        this.mTimerValue = this.mNetflixActivity.getResources().getInteger(2131492885);
        this.mAutoPlayEnabled = this.isAutoPlayEnabled();
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "PostPlayForMDX:: timer max value " + this.mTimerValue);
        }
        if (!this.mAutoPlayEnabled && this.mTimerView != null) {
            ViewUtils.setVisibility(this.mAutoPlayView, ViewUtils$Visibility.INVISIBLE);
        }
        this.initInfoContainer(this.mNetflixActivity);
        this.initButtons();
    }
    
    private void init(final Activity activity) {
        this.mTimerValue = activity.getResources().getInteger(2131492885);
        this.mAutoPlayEnabled = this.isAutoPlayEnabled();
        if (Log.isLoggable()) {
            Log.d("nf_postplay", "PostPlayForMDX:: timer max value " + this.mTimerValue);
        }
        if (!this.mAutoPlayEnabled && this.mTimerView != null) {
            ViewUtils.setVisibility(this.mAutoPlayView, ViewUtils$Visibility.INVISIBLE);
        }
        this.initInfoContainer(activity);
        this.initButtons();
    }
    
    private void refreshTimerText() {
        if (this.mTimerView != null) {
            this.mTimerView.setText((CharSequence)String.valueOf(this.mTimer));
        }
    }
    
    private void setMDXTargetName() {
        if (this.mTargetNameView != null) {
            final ServiceManager serviceManager = this.mNetflixActivity.getServiceManager();
            if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                this.mTargetNameView.setText((CharSequence)ServiceManagerUtils.getCurrentDeviceFriendlyName(serviceManager));
                if (this.mTargetNameView != null) {
                    this.mTargetNameView.setVisibility(0);
                }
                if (this.mBackgroundContainer != null) {
                    this.mBackgroundContainer.setVisibility(0);
                }
            }
        }
    }
    
    private void stopAllNotifications() {
        final ServiceManager serviceManager = this.mNetflixActivity.getServiceManager();
        if (serviceManager != null) {
            ((MdxAgent)serviceManager.getMdx()).stopAllNotifications();
        }
    }
    
    private void updatePostPlayUI(final boolean b) {
        if (Coppola1Utils.isCoppolaExperience((Context)this.mNetflixActivity)) {
            final TextView mTitle = this.mTitle;
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            mTitle.setVisibility(visibility);
            this.mSynopsis.setVisibility(8);
            this.mPlayButton.getLayoutParams().width = -2;
            ((ViewGroup$MarginLayoutParams)this.mPlayButton.getLayoutParams()).bottomMargin = (int)this.mNetflixActivity.getResources().getDimension(2131362268);
        }
    }
    
    @Override
    public void destroy() {
        super.destroy();
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
    }
    
    @Override
    protected void doTransitionFromPostPlay() {
        if (this.mBackgroundContainer.getVisibility() == 0) {
            this.mNetflixActivity.performUpAction();
        }
    }
    
    @Override
    protected void doTransitionToPostPlay() {
        if (this.mAutoPlayEnabled) {
            Log.d("nf_postplay", "Auto play is enabled");
            this.mTimer = this.mTimerValue;
            if (Coppola1Utils.isCoppolaContext((Context)this.mNetflixActivity)) {
                this.refreshTimerText();
            }
            this.mNetflixActivity.getHandler().postDelayed(this.onEverySecond, 1000L);
            return;
        }
        Log.d("nf_postplay", "Auto play is disabled");
    }
    
    @Override
    public void endOfPlay() {
        super.endOfPlay();
        this.setBackgroundImageVisible(true);
    }
    
    @Override
    protected void findViews() {
        this.mTargetNameView = (TextView)this.mNetflixActivity.findViewById(2131690130);
        this.mStopButton = this.mNetflixActivity.findViewById(2131690136);
        this.mPlayButton = this.mNetflixActivity.findViewById(2131690135);
        this.mMoreButton = this.mNetflixActivity.findViewById(2131690137);
    }
    
    @Override
    protected int getLengthOfAutoPlayCountdow() {
        return this.mTimerValue;
    }
    
    @Override
    protected UserActionLogging$PostPlayExperience getPostPlayExpirience() {
        return UserActionLogging$PostPlayExperience.PostPlayNextEpisode;
    }
    
    public void handleBack() {
        if (!this.mNetflixActivity.isFinishing() && this.mNetflixActivity.getServiceManager() != null) {
            this.mNetflixActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOPPOSTPALY"));
        }
    }
    
    public void handleStop() {
        if (!this.mNetflixActivity.isFinishing() && this.mNetflixActivity.getServiceManager() != null && ServiceManagerUtils.isMdxAgentAvailable(this.mNetflixActivity.getServiceManager())) {
            this.stopAllNotifications();
            this.mNetflixActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
            this.mNetflixActivity.finish();
        }
    }
    
    protected void initButtons() {
        if (this.mStopButton != null) {
            this.mStopButton.setVisibility(0);
        }
    }
    
    protected void initInfoContainer(final Activity activity) {
        if (this.mTimerView != null) {
            this.mTimerView.setVisibility(8);
        }
    }
    
    @Override
    protected boolean isAutoPlayEnabled() {
        return true;
    }
    
    @Override
    protected void onConfigurationChanged(final Configuration configuration) {
        boolean b = true;
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != 1) {
            b = false;
        }
        this.updatePostPlayUI(b);
    }
    
    @Override
    public void onPause() {
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
        super.onPause();
    }
    
    @Override
    public void onResume() {
        if (!this.isInteractivePostPlay && this.mInPostPlay && this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
            this.mNetflixActivity.getHandler().post(this.onEverySecond);
        }
        super.onResume();
    }
    
    protected void onTimerEnd() {
        this.mPlayButton.setEnabled(false);
        if (!this.mNetflixActivity.isFinishing()) {
            this.mNetflixActivity.finish();
            this.stopAllNotifications();
        }
    }
    
    @Override
    public void postPlayDismissed() {
        super.postPlayDismissed();
        if (this.mAutoPlayEnabled) {
            this.mNetflixActivity.getHandler().removeCallbacks(this.onEverySecond);
        }
    }
    
    @Override
    protected void setClickListeners() {
        super.setClickListeners();
        if (this.mStopButton != null) {
            this.mStopButton.setOnClickListener((View$OnClickListener)new PostPlayForMDX$2(this));
        }
    }
    
    @Override
    protected boolean shouldReportPostplay() {
        return false;
    }
    
    @Override
    protected void updateOnPostPlayVideosFetched() {
        Log.d("nf_postplay", "updateOnPostPlayVideosFetched start");
        if (this.mPostPlayExperience == null) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
            return;
        }
        if (this.mPostPlayExperience.getItems().size() != 1) {
            Log.e("nf_postplay", "We do not have any data! Do nothing!");
            return;
        }
        this.updateViews();
        this.setMDXTargetName();
        this.transitionToPostPlay();
    }
    
    protected void updateViews() {
        if (this.mPostPlayExperience != null && this.mPostPlayExperience.getItems() != null && this.mPostPlayExperience.getItems().size() != 0) {
            if (this.mNetflixActivity.isFinishing()) {
                Log.e("nf_postplay", "Activity for playback is already not valid! Do nothing!");
                return;
            }
            final LayoutInflater layoutInflater = this.mNetflixActivity.getLayoutInflater();
            this.mBackgroundContainer.removeAllViews();
            this.mItemsContainer.removeAllViews();
            this.mBackgroundContainer.getLayoutParams().width = DeviceUtils.getScreenWidthInPixels((Context)this.mNetflixActivity) * this.mPostPlayExperience.getItems().size();
            final PostPlayItem postPlayItem = this.mPostPlayExperience.getItems().get(0);
            final PostPlayBackground postPlayBackground = (PostPlayBackground)layoutInflater.inflate(2130903228, (ViewGroup)this.mBackgroundContainer, false);
            this.mBackgroundContainer.addView((View)postPlayBackground);
            postPlayBackground.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.MDX);
            postPlayBackground.getLayoutParams().width = DeviceUtils.getScreenWidthInPixels((Context)this.mNetflixActivity);
            postPlayBackground.startBackgroundAutoPan();
            final PostPlayCallToAction postPlayCallToAction = new PostPlayCallToAction(this.mNetflixActivity, this.mPlayerFragment, postPlayItem.getPlayAction(), PostPlayRequestContext.MDX);
            final PostPlayMetadata postPlayMetadata = (PostPlayMetadata)layoutInflater.inflate(2130903241, (ViewGroup)this.mItemsContainer, false);
            this.mItemsContainer.addView((View)postPlayMetadata);
            postPlayMetadata.updateViews(postPlayItem, this.mNetflixActivity, this.mPlayerFragment, PostPlayRequestContext.MDX);
            if (this.mPlayButton != null) {
                this.mPlayButton.setOnClickListener(postPlayCallToAction.generatePlayHandler());
            }
            final PostPlayAction moreInfoAction = postPlayItem.getMoreInfoAction();
            if (this.mMoreButton != null) {
                if (moreInfoAction != null) {
                    this.mMoreButton.setOnClickListener(new PostPlayCallToAction(this.mNetflixActivity, this.mPlayerFragment, moreInfoAction, PostPlayRequestContext.MDX).generateDisplayPageHandler());
                    this.mMoreButton.setVisibility(0);
                }
                else {
                    this.mMoreButton.setVisibility(8);
                }
            }
            if (this.mPostPlayExperience.getAutoplay() && this.mPostPlayExperience.getAutoplaySeconds() > 0) {
                this.setupAutoPlay(PostPlayRequestContext.MDX);
                postPlayMetadata.startTimer();
            }
        }
    }
}
