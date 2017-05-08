// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;

public class ShowDetailsFrag extends DetailsFrag<ShowDetails>
{
    protected static final String EXTRA_EPISODE_ID = "extra_episode_id";
    protected static final String EXTRA_VIDEO_ID = "extra_video_id";
    private static final String TAG = "ShowDetailsFrag";
    private String episodeId;
    private boolean isLoading;
    private long requestId;
    private String videoId;
    
    public ShowDetailsFrag() {
        this.isLoading = true;
    }
    
    public static ShowDetailsFrag create(final String s, final String s2) {
        final ShowDetailsFrag showDetailsFrag = new ShowDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_video_id", s);
        arguments.putString("extra_episode_id", s2);
        showDetailsFrag.setArguments(arguments);
        return showDetailsFrag;
    }
    
    private void fetchShowData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null) {
            Log.w("ShowDetailsFrag", "Manager is null - can't reload data");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        Log.v("ShowDetailsFrag", "Fetching data for show ID: " + this.videoId);
        serviceManager.getBrowse().fetchShowDetails(this.videoId, this.episodeId, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new ShowDetailsFrag$FetchShowDetailsCallback(this, this.requestId));
    }
    
    private void onShowDataReady(final ShowDetails showDetails, final Status status) {
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.getNetflixActivity())) {
            Log.v("ShowDetailsFrag", "Activity state is invalid");
            return;
        }
        if (this.requestId != this.requestId || this.isDestroyed()) {
            Log.v("ShowDetailsFrag", "Ignoring stale callback");
            return;
        }
        this.isLoading = false;
        if (status != null && status.isError()) {
            Log.w("ShowDetailsFrag", "Invalid status code");
            this.showErrorView();
            return;
        }
        if (showDetails == null) {
            Log.v("ShowDetailsFrag", "No details in response");
            this.showErrorView();
            return;
        }
        this.showDetailsView(showDetails);
    }
    
    protected void dataReady(final ShowDetails showDetails) {
        this.onShowDataReady(showDetails, null);
    }
    
    @Override
    protected VideoDetailsViewGroup$DetailsStringProvider getDetailsStringProvider(final ShowDetails showDetails) {
        return new ShowDetailsFrag$ShowDetailsStringProvider((Context)this.getActivity(), showDetails);
    }
    
    @Override
    protected String getVideoId() {
        return this.videoId;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        this.detailsViewGroup = (VideoDetailsViewGroup)view.findViewById(2131821551);
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.videoId = this.getArguments().getString("extra_video_id");
        this.episodeId = this.getArguments().getString("extra_episode_id");
        super.onCreate(bundle);
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        final LinearLayout linearLayout = (LinearLayout)onCreateView.findViewById(2131821559);
        if (linearLayout != null) {
            linearLayout.setOrientation(1);
            for (int i = 0; i < linearLayout.getChildCount(); ++i) {
                final View child = linearLayout.getChildAt(i);
                final LinearLayout$LayoutParams layoutParams = (LinearLayout$LayoutParams)child.getLayoutParams();
                layoutParams.weight = 0.0f;
                child.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            }
        }
        return onCreateView;
    }
    
    @Override
    protected void reloadData() {
        this.fetchShowData();
    }
    
    public void setVideoId(final String videoId) {
        this.videoId = videoId;
    }
    
    @Override
    protected void showDetailsView(final ShowDetails copyright) {
        super.showDetailsView(copyright);
        this.detailsViewGroup.setCopyright(copyright);
    }
}
