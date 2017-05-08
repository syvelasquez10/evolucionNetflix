// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.UserActionLogging$PostPlayExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

public final class PostPlayForMDX extends PostPlayForEpisodes
{
    private EpisodeDetails episodeDetails;
    private TextView mTargetNameView;
    
    PostPlayForMDX(final NetflixActivity netflixActivity) {
        super(netflixActivity);
        this.mTimerValue = netflixActivity.getResources().getInteger(2131427344);
        this.mOffsetMs = this.mTimerValue * 1000;
    }
    
    private Intent createIntent(final String s) {
        final ServiceManager serviceManager = this.mNetflixActivity.getServiceManager();
        if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
            return MdxAgent$Utils.createIntent((Context)this.mNetflixActivity, s, serviceManager.getMdx().getCurrentTarget());
        }
        return null;
    }
    
    private void finishMDXActivityIfNeeded() {
        if (!this.mNetflixActivity.isFinishing()) {
            this.mNetflixActivity.finish();
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
                if (this.mInfoTitleView != null) {
                    this.mInfoTitleView.setVisibility(0);
                }
                if (this.mBackground != null) {
                    this.mBackground.setVisibility(0);
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
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType) {
        if (!TextUtils.isEmpty((CharSequence)s) && this.mNetflixActivity.getServiceManager() != null) {
            Log.d("nf_postplay", "Fetch postplay videos...");
            this.mNetflixActivity.getServiceManager().getBrowse().fetchEpisodeDetails(s, null, new PostPlayForMDX$FetchPostPlayForPlaybackCallback(this));
            return;
        }
        Log.e("nf_postplay", "Unable to fetch postplay videos!");
    }
    
    @Override
    public void fetchPostPlayVideosIfNeeded(final String s, final VideoType videoType) {
        this.fetchPostPlayVideos(s, videoType);
    }
    
    @Override
    protected void findViews() {
        this.mTargetNameView = (TextView)this.mNetflixActivity.findViewById(2131624521);
        this.mInfoTitleView = (TextView)this.mNetflixActivity.findViewById(2131624519);
    }
    
    @Override
    protected int getLengthOfAutoPlayCountdow() {
        return this.mTimerValue;
    }
    
    @Override
    protected UserActionLogging$PostPlayExperience getPostPlayExpirience() {
        return UserActionLogging$PostPlayExperience.PostPlaySuggestions;
    }
    
    public void handleBack() {
        if (!this.mNetflixActivity.isFinishing() && this.mNetflixActivity.getServiceManager() != null) {
            this.mNetflixActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOPPOSTPALY"));
        }
    }
    
    public void handleInfoButtonPress() {
        if (this.hasVideos() && this.episodeDetails != null) {
            this.finishMDXActivityIfNeeded();
            final Intent episodeDetailsIntent = DetailsActivityLauncher.getEpisodeDetailsIntent(this.mNetflixActivity, this.episodeDetails.getPlayable().getParentId(), this.episodeDetails.getId(), PlayContext.NFLX_MDX_CONTEXT);
            if (episodeDetailsIntent != null) {
                episodeDetailsIntent.addFlags(67108864);
                this.mNetflixActivity.startActivity(episodeDetailsIntent);
                return;
            }
            Log.w("nf_postplay", "Can't start activity - intent is null");
        }
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        if (this.episodeDetails != null) {
            final Asset create = Asset.create(this.episodeDetails.getPlayable(), PlayContext.DFLT_MDX_CONTEXT, true);
            this.stopAllNotifications();
            MdxAgent$Utils.playVideo(this.mNetflixActivity, create, true);
        }
        this.finishMDXActivityIfNeeded();
    }
    
    public void handleStop() {
        if (!this.mNetflixActivity.isFinishing() && this.mNetflixActivity.getServiceManager() != null && ServiceManagerUtils.isMdxAgentAvailable(this.mNetflixActivity.getServiceManager())) {
            this.stopAllNotifications();
            this.mNetflixActivity.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
            this.mNetflixActivity.finish();
        }
    }
    
    public boolean hasVideos() {
        return this.episodeDetails != null;
    }
    
    public void init(final EpisodeDetails episodeDetails) {
        this.updateViews(this.episodeDetails = episodeDetails);
        this.setMDXTargetName();
        this.transitionToPostPlay();
    }
    
    @Override
    protected void initButtons() {
        if (this.mPlayButton != null) {
            this.mPlayButton.setVisibility(0);
        }
        if (this.mStopButton != null) {
            this.mStopButton.setVisibility(0);
        }
        if (this.mMoreButton != null) {
            this.mMoreButton.setVisibility(0);
        }
    }
    
    @Override
    protected void initInfoContainer(final Activity activity) {
        if (this.mInfoTitleView != null) {
            this.mInfoTitleView.setText(activity.getText(2131165607));
            this.mInfoTitleView.setVisibility(4);
        }
        if (this.mTimerView != null) {
            this.mTimerView.setVisibility(8);
        }
    }
    
    @Override
    protected boolean isAutoPlayEnabled() {
        return true;
    }
    
    @Override
    protected void onTimerEnd() {
        this.mPlayButton.setEnabled(false);
        if (!this.mNetflixActivity.isFinishing()) {
            this.mNetflixActivity.finish();
            this.stopAllNotifications();
        }
    }
    
    @Override
    protected void setClickListeners() {
        super.setClickListeners();
        if (this.mStopButton != null) {
            this.mStopButton.setOnClickListener((View$OnClickListener)new PostPlayForMDX$1(this));
        }
        if (this.mMoreButton != null) {
            this.mMoreButton.setOnClickListener((View$OnClickListener)new PostPlayForMDX$2(this));
        }
    }
    
    @Override
    protected boolean shouldReportPostplay() {
        return false;
    }
}
