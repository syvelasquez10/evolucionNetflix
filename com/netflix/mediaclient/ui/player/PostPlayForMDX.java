// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.InterestingVideoDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.text.TextUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.app.Activity;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;

public final class PostPlayForMDX extends PostPlayForEpisodes
{
    private EpisodeDetails episodeDetails;
    private TextView mTargetNameView;
    
    PostPlayForMDX(final PlayerActivity playerActivity) {
        super(playerActivity);
    }
    
    private Intent createIntent(final String s) {
        if (this.mContext != null) {
            final ServiceManager serviceManager = this.mContext.getServiceManager();
            if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                return MdxAgent.Utils.createIntent(this.mContext, s, serviceManager.getMdx().getCurrentTarget());
            }
        }
        return null;
    }
    
    private void setMDXTargetName() {
        if (this.mTargetNameView != null) {
            final ServiceManager serviceManager = this.mContext.getServiceManager();
            if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                this.mTargetNameView.setText((CharSequence)ServiceManagerUtils.getCurrentDeviceFriendlyName(serviceManager));
            }
        }
    }
    
    @Override
    protected void findViews() {
        this.mTargetNameView = (TextView)this.mContext.findViewById(2131165547);
        this.mInfoTitleView = (TextView)this.mContext.findViewById(2131165545);
    }
    
    public void handleInfoButtonPress() {
        if (this.hasVideos() && this.episodeDetails != null) {
            DetailsActivity.showEpisodeDetails(this.mContext, this.episodeDetails.getParentId(), this.episodeDetails.getId(), PlayContext.NFLX_MDX_CONTEXT);
        }
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        if (this.episodeDetails != null && this.mContext != null) {
            MdxAgent.Utils.playVideo(this.mContext, Asset.create(this.episodeDetails, PlayContext.DFLT_MDX_CONTEXT, PlayerActivity.PIN_VERIFIED), true);
        }
        if (this.mContext != null) {
            this.mContext.finish();
        }
    }
    
    public void handleStop() {
        if (this.mContext != null) {
            this.mContext.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
            this.mContext.finish();
        }
    }
    
    public boolean hasVideos() {
        return this.episodeDetails != null;
    }
    
    @Override
    public void init(final String s) {
        this.mTimerValue = this.mContext.getResources().getInteger(2131427336);
        this.mOffset = this.mTimerValue * 1000;
        if (!TextUtils.isEmpty((CharSequence)s) && this.mContext != null && this.mContext.getServiceManager() != null) {
            this.mContext.getServiceManager().fetchEpisodeDetails(s, new FetchPostPlayForPlaybackCallback());
        }
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
    protected void initInfoContainer() {
        if (this.mInfoTitleView != null) {
            this.mInfoTitleView.setText(this.mContext.getResources().getText(2131493310));
        }
        if (this.mTimerView != null) {
            this.mTimerView.setVisibility(8);
        }
        if (this.mTargetNameView != null) {
            this.mTargetNameView.setVisibility(0);
        }
        if (this.mBackground != null) {
            this.mBackground.setVisibility(0);
        }
    }
    
    @Override
    protected boolean isAutoPlayEnabled() {
        return true;
    }
    
    @Override
    protected void onTimerEnd() {
        this.mPlayButton.setEnabled(false);
        if (this.mContext != null) {
            this.mContext.finish();
        }
    }
    
    @Override
    protected void setClickListeners() {
        super.setClickListeners();
        if (this.mStopButton != null) {
            this.mStopButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    PostPlayForMDX.this.handleStop();
                }
            });
        }
        if (this.mMoreButton != null) {
            this.mMoreButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    PostPlayForMDX.this.handleInfoButtonPress();
                }
            });
        }
    }
    
    private class FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
    {
        public FetchPostPlayForPlaybackCallback() {
            super("nf_postplay");
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
            super.onEpisodeDetailsFetched(episodeDetails, n);
            if (n == 0 && episodeDetails != null) {
                PostPlayForMDX.this.episodeDetails = episodeDetails;
                PostPlayForMDX.this.updateViews(episodeDetails);
                PostPlayForMDX.this.setMDXTargetName();
                PostPlayForMDX.this.transitionToPostPlay();
            }
        }
    }
}
