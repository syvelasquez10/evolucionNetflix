// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
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
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

public final class PostPlayForMDX extends PostPlayForEpisodes
{
    private EpisodeDetails episodeDetails;
    private TextView mTargetNameView;
    
    PostPlayForMDX(final PlayerActivity playerActivity) {
        super(playerActivity);
        this.mTimerValue = this.mContext.getResources().getInteger(2131361800);
        this.mOffsetMs = this.mTimerValue * 1000;
    }
    
    private Intent createIntent(final String s) {
        if (this.mContext != null) {
            final ServiceManager serviceManager = this.mContext.getServiceManager();
            if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                return MdxAgent$Utils.createIntent((Context)this.mContext, s, serviceManager.getMdx().getCurrentTarget());
            }
        }
        return null;
    }
    
    private void setMDXTargetName() {
        if (this.mTargetNameView != null) {
            final ServiceManager serviceManager = this.mContext.getServiceManager();
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
        final ServiceManager serviceManager = this.mContext.getServiceManager();
        if (serviceManager != null) {
            ((MdxAgent)serviceManager.getMdx()).stopAllNotifications();
        }
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType) {
        if (!TextUtils.isEmpty((CharSequence)s) && this.mContext != null && this.mContext.getServiceManager() != null) {
            Log.d("nf_postplay", "Fetch postplay videos...");
            this.mContext.getServiceManager().getBrowse().fetchEpisodeDetails(s, new PostPlayForMDX$FetchPostPlayForPlaybackCallback(this));
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
        this.mTargetNameView = (TextView)this.mContext.findViewById(2131427736);
        this.mInfoTitleView = (TextView)this.mContext.findViewById(2131427734);
    }
    
    public void handleBack() {
        if (this.mContext != null) {
            this.mContext.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOPPOSTPALY"));
        }
    }
    
    public void handleInfoButtonPress() {
        if (this.hasVideos() && this.episodeDetails != null) {
            if (this.mContext != null) {
                this.mContext.finish();
            }
            final Intent episodeDetailsIntent = DetailsActivityLauncher.getEpisodeDetailsIntent(this.mContext, this.episodeDetails.getPlayable().getParentId(), this.episodeDetails.getId(), PlayContext.NFLX_MDX_CONTEXT);
            if (episodeDetailsIntent != null) {
                episodeDetailsIntent.addFlags(67108864);
                this.mContext.startActivity(episodeDetailsIntent);
                return;
            }
            Log.w("nf_postplay", "Can't start activity - intent is null");
        }
    }
    
    @Override
    protected void handlePlayNow(final boolean b) {
        if (this.episodeDetails != null && this.mContext != null) {
            final Asset create = Asset.create(this.episodeDetails.getPlayable(), PlayContext.DFLT_MDX_CONTEXT, PlayerActivity.PIN_VERIFIED);
            this.stopAllNotifications();
            MdxAgent$Utils.playVideo(this.mContext, create, true);
        }
        if (this.mContext != null) {
            this.mContext.setResult(-1);
            this.mContext.finish();
        }
    }
    
    public void handleStop() {
        if (this.mContext != null) {
            this.stopAllNotifications();
            this.mContext.startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
            this.mContext.setResult(-1);
            this.mContext.finish();
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
    protected void initInfoContainer() {
        if (this.mInfoTitleView != null) {
            this.mInfoTitleView.setText(this.mContext.getResources().getText(2131493283));
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
        if (this.mContext != null) {
            this.mContext.finish();
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
}
