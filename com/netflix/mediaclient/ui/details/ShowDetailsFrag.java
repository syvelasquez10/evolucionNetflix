// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ShowDetails;

public class ShowDetailsFrag extends DetailsFrag<ShowDetails>
{
    private static final String EXTRA_EPISODE_ID = "extra_episode_id";
    private static final String EXTRA_VIDEO_ID = "extra_video_id";
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
        serviceManager.fetchShowDetails(this.videoId, this.episodeId, new FetchShowDetailsCallback(this.requestId));
    }
    
    @Override
    protected VideoDetailsViewGroup.DetailsStringProvider getDetailsStringProvider(final ShowDetails showDetails) {
        return new ShowDetailsStringProvider((Context)this.getActivity(), showDetails);
    }
    
    @Override
    protected String getVideoId() {
        return this.videoId;
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
        final LinearLayout linearLayout = (LinearLayout)onCreateView.findViewById(2131165583);
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
    public void onManagerReady(final ServiceManager serviceManager, final int n) {
        super.onManagerReady(serviceManager, n);
        this.fetchShowData();
    }
    
    @Override
    protected void reloadData() {
        this.fetchShowData();
    }
    
    private class FetchShowDetailsCallback extends LoggingManagerCallback
    {
        private final long requestId;
        
        public FetchShowDetailsCallback(final long requestId) {
            super("ShowDetailsFrag");
            this.requestId = requestId;
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
            super.onShowDetailsFetched(showDetails, n);
            if (this.requestId != ShowDetailsFrag.this.requestId || ShowDetailsFrag.this.isDestroyed()) {
                Log.v("ShowDetailsFrag", "Ignoring stale callback");
                return;
            }
            ShowDetailsFrag.this.isLoading = false;
            if (n != 0) {
                Log.w("ShowDetailsFrag", "Invalid status code");
                ShowDetailsFrag.this.showErrorView();
                return;
            }
            if (showDetails == null) {
                Log.v("ShowDetailsFrag", "No details in response");
                ShowDetailsFrag.this.showErrorView();
                return;
            }
            ShowDetailsFrag.this.showDetailsView(showDetails);
        }
    }
    
    public static class ShowDetailsStringProvider implements DetailsStringProvider
    {
        private final Context context;
        private final ShowDetails details;
        
        public ShowDetailsStringProvider(final Context context, final ShowDetails details) {
            this.context = context;
            this.details = details;
        }
        
        @Override
        public CharSequence getBasicInfoString() {
            return StringUtils.getBasicInfoString(this.context, this.details);
        }
        
        @Override
        public CharSequence getCreatorsText() {
            return StringUtils.createBoldLabeledText(this.context, 2131493169, this.details.getCreators());
        }
        
        @Override
        public CharSequence getStarringText() {
            return StringUtils.createBoldLabeledText(this.context, 2131493168, this.details.getActors());
        }
    }
}
