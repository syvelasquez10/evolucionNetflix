// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.widget.ImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class PostPlayFrag extends NetflixFrag implements NetflixRatingBar$RatingBarDataProvider
{
    public static final String MINI_PLAYER_POST_PLAY_ACTION_HIDE = "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_HIDE";
    public static final String MINI_PLAYER_POST_PLAY_ACTION_TITLE_END = "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_END";
    public static final String MINI_PLAYER_POST_PLAY_ACTION_TITLE_NEXT = "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_NEXT";
    private static final String TAG = "PostPlayFrag";
    private TextView backToBrowsingButton;
    private EpisodeDetails episodeDetails;
    private TextView episodeTitle;
    private ImageView episodesButton;
    private final BroadcastReceiver miniPlayerPostPlay;
    private Button playButton;
    private ViewGroup postPlayForNextContainer;
    private ViewGroup postPlayForTitleEndContainer;
    private NetflixRatingBar rating;
    private final BroadcastReceiver ratingsUpdateBroadcastReceiver;
    private TextView showTitle;
    private ImageView stopButton;
    private TextView synopsis;
    private TextView targetName;
    private VideoDetails video;
    
    public PostPlayFrag() {
        this.miniPlayerPostPlay = new PostPlayFrag$5(this);
        this.ratingsUpdateBroadcastReceiver = new PostPlayFrag$6(this);
    }
    
    private Intent createIntent(final String s) {
        final ServiceManager serviceManager = this.getNetflixActivity().getServiceManager();
        if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
            return MdxAgent$Utils.createIntent((Context)this.getNetflixActivity(), s, serviceManager.getMdx().getCurrentTarget());
        }
        return null;
    }
    
    private void findViews(final View view) {
        this.episodeTitle = (TextView)view.findViewById(2131690113);
        this.showTitle = (TextView)view.findViewById(2131690107);
        this.targetName = (TextView)view.findViewById(2131690112);
        this.synopsis = (TextView)view.findViewById(2131690114);
        this.playButton = (Button)view.findViewById(2131690115);
        this.stopButton = (ImageView)view.findViewById(2131690116);
        this.episodesButton = (ImageView)view.findViewById(2131690117);
        this.backToBrowsingButton = (TextView)view.findViewById(2131690109);
        this.postPlayForTitleEndContainer = (ViewGroup)view.findViewById(2131690106);
        this.postPlayForNextContainer = (ViewGroup)view.findViewById(2131690110);
        this.rating = (NetflixRatingBar)view.findViewById(2131690108);
    }
    
    private void handlePlayNow() {
        if (this.episodeDetails != null) {
            MdxAgent$Utils.playVideo(this.getNetflixActivity(), Asset.create(this.episodeDetails.getPlayable(), PlayContext.DFLT_MDX_CONTEXT, true), true);
        }
    }
    
    private void hidePostPlayViews() {
        if (this.postPlayForTitleEndContainer != null) {
            this.postPlayForTitleEndContainer.setVisibility(4);
        }
        if (this.postPlayForNextContainer != null) {
            this.postPlayForNextContainer.setVisibility(4);
        }
    }
    
    private void init() {
        this.setMDXTargetName();
        this.setClickListeners();
        this.initReceivers();
    }
    
    private void initReceivers() {
        this.getNetflixActivity().registerReceiverLocallyWithAutoUnregister(this.miniPlayerPostPlay, "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_END");
        this.getNetflixActivity().registerReceiverLocallyWithAutoUnregister(this.miniPlayerPostPlay, "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_NEXT");
        this.getNetflixActivity().registerReceiverLocallyWithAutoUnregister(this.miniPlayerPostPlay, "com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_HIDE");
        this.getNetflixActivity().registerReceiverLocallyWithAutoUnregister(this.ratingsUpdateBroadcastReceiver, "com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE");
    }
    
    private void setClickListeners() {
        if (this.playButton != null) {
            this.playButton.setOnClickListener((View$OnClickListener)new PostPlayFrag$1(this));
        }
        if (this.stopButton != null) {
            this.stopButton.setOnClickListener((View$OnClickListener)new PostPlayFrag$2(this));
        }
        if (this.backToBrowsingButton != null) {
            this.backToBrowsingButton.setOnClickListener((View$OnClickListener)new PostPlayFrag$3(this));
        }
        if (this.episodesButton != null) {
            this.episodesButton.setOnClickListener((View$OnClickListener)new PostPlayFrag$4(this));
        }
    }
    
    private void setMDXTargetName() {
        if (this.targetName != null) {
            final ServiceManager serviceManager = this.getNetflixActivity().getServiceManager();
            if (serviceManager != null && ServiceManagerUtils.isMdxAgentAvailable(serviceManager)) {
                final String currentDeviceFriendlyName = ServiceManagerUtils.getCurrentDeviceFriendlyName(serviceManager);
                if (!TextUtils.isEmpty((CharSequence)currentDeviceFriendlyName)) {
                    this.targetName.setText((CharSequence)currentDeviceFriendlyName);
                    this.targetName.setVisibility(0);
                }
            }
        }
    }
    
    private void showPostPlayViewsForNext() {
        if (this.postPlayForNextContainer != null) {
            this.postPlayForNextContainer.setVisibility(0);
        }
    }
    
    private void showPostPlayViewsForTitleEnd() {
        if (this.postPlayForTitleEndContainer != null) {
            this.postPlayForTitleEndContainer.setVisibility(0);
        }
    }
    
    private void stopAllNotifications() {
        final ServiceManager serviceManager = this.getNetflixActivity().getServiceManager();
        if (serviceManager != null) {
            ((MdxAgent)serviceManager.getMdx()).stopAllNotifications();
        }
    }
    
    private void updateDetails(final EpisodeDetails episodeDetails) {
        if (this.episodeTitle != null) {
            this.episodeTitle.setText((CharSequence)this.getResources().getString(2131231064, new Object[] { this.episodeDetails.getSeasonAbbrSeqLabel(), this.episodeDetails.getEpisodeNumber(), this.episodeDetails.getTitle() }));
        }
        if (this.synopsis != null) {
            this.synopsis.setText((CharSequence)episodeDetails.getSynopsis());
        }
    }
    
    private void updateDetails(final VideoDetails videoDetails) {
        if (this.showTitle != null) {
            this.showTitle.setText((CharSequence)videoDetails.getTitle());
        }
        if (this.rating != null) {
            this.rating.setNumStars(5);
            this.rating.update(this, videoDetails);
        }
    }
    
    @Override
    public boolean destroyed() {
        return false;
    }
    
    public PlayContext getPlayContext() {
        return PlayContext.NFLX_MDX_CONTEXT;
    }
    
    @Override
    public String getVideoId() {
        return this.video.getId();
    }
    
    @Override
    public VideoType getVideoType() {
        return this.video.getType();
    }
    
    public void handleStop() {
        if (this.getActivity() != null && !this.getNetflixActivity().isFinishing() && this.getNetflixActivity().getServiceManager() != null && ServiceManagerUtils.isMdxAgentAvailable(this.getNetflixActivity().getServiceManager())) {
            this.stopAllNotifications();
            this.getNetflixActivity().startService(this.createIntent("com.netflix.mediaclient.intent.action.MDX_STOP"));
            this.hidePostPlayViews();
        }
    }
    
    public boolean isLoadingData() {
        return false;
    }
    
    public boolean isShowingForNext() {
        return this.postPlayForNextContainer.getVisibility() == 0;
    }
    
    public boolean isShowingTitleEnd() {
        return this.postPlayForTitleEndContainer.getVisibility() == 0;
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("PostPlayFrag", "Creating new frag view...");
        final View inflate = layoutInflater.inflate(2130903228, (ViewGroup)null, false);
        this.findViews(inflate);
        this.init();
        return inflate;
    }
    
    public void setVideo(final VideoDetails video) {
        this.video = video;
    }
}
