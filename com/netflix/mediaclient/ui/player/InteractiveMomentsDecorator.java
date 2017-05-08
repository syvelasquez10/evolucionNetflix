// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsFactory;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.InteractivePlaybackMoments;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.iko.InteractiveMomentsManager;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;

public class InteractiveMomentsDecorator extends PlayScreenDecorator
{
    private static final String TAG = "InteractiveMomentsDecorator";
    private boolean bottomPanelVisible;
    private InteractiveMomentsModel data;
    private InteractiveMomentsManager interactiveMomentsManager;
    private VideoDetails mDetails;
    private PlayerFragment mFragment;
    private ServiceManager svcManager;
    private String videoId;
    private VideoType videoType;
    
    protected InteractiveMomentsDecorator(final PlayScreen playScreen) {
        super(playScreen);
        this.init();
    }
    
    protected InteractiveMomentsDecorator(final PlayScreenDecorator playScreenDecorator) {
        super(playScreenDecorator);
        this.init();
    }
    
    private void init() {
        this.mFragment = this.getController();
        if (this.mFragment == null) {
            Log.e("InteractiveMomentsDecorator", "Player fragment is null. This should not happen");
            return;
        }
        if (this.mFragment.getCurrentAsset() != null) {
            this.videoId = this.mFragment.getCurrentAsset().getPlayableId();
            VideoType videoType;
            if (this.mFragment.getCurrentAsset().isEpisode()) {
                videoType = VideoType.EPISODE;
            }
            else {
                videoType = VideoType.MOVIE;
            }
            this.videoType = videoType;
        }
        this.svcManager = ServiceManager.getServiceManager(this.mFragment.getNetflixActivity());
    }
    
    private boolean isManagerReady() {
        return this.interactiveMomentsManager != null && this.interactiveMomentsManager.isManagerReady();
    }
    
    private void onMomentsFetched(final InteractivePlaybackMoments interactivePlaybackMoments) {
        if (interactivePlaybackMoments != null) {
            this.data = interactivePlaybackMoments.getData();
            if (this.data == null || StringUtils.isEmpty(this.data.getType())) {
                if (Log.isLoggable()) {
                    Log.d("InteractiveMomentsDecorator", "Interactive moments data is empty.");
                }
            }
            else {
                this.interactiveMomentsManager = InteractiveMomentsFactory.getManager(this.data.getType());
                if (this.interactiveMomentsManager == null) {
                    Log.e("InteractiveMomentsDecorator", "Interactive moments manager is null");
                    return;
                }
                this.interactiveMomentsManager.init(this.mFragment);
                this.interactiveMomentsManager.onMomentsFetched(interactivePlaybackMoments);
                if (this.mDetails != null) {
                    this.interactiveMomentsManager.onVideoDetailsFetched(this.mDetails);
                }
            }
        }
    }
    
    private void requestInteractiveMoments() {
        Log.d("InteractiveMomentsDecorator", "Fetching interactive moments...");
        this.svcManager.getBrowse().fetchInteractiveVideoMoments(this.videoType, this.videoId, new InteractiveMomentsDecorator$1(this));
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onDestroy();
        }
    }
    
    @Override
    public void onVideoDetailsFetched(final VideoDetails mDetails) {
        super.onVideoDetailsFetched(mDetails);
        this.mDetails = mDetails;
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.onVideoDetailsFetched(mDetails);
        }
    }
    
    @Override
    public void playerOverlayVisibility(final boolean bottomPanelVisible) {
        super.playerOverlayVisibility(bottomPanelVisible);
        this.bottomPanelVisible = bottomPanelVisible;
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.playerOverlayVisibility(bottomPanelVisible);
        }
    }
    
    @Override
    public void setTimelineMaxProgress(final int timelineMaxProgress) {
        super.setTimelineMaxProgress(timelineMaxProgress);
        this.requestInteractiveMoments();
    }
    
    @Override
    public void setTimelineProgress(final int n, final boolean b) {
        super.setTimelineProgress(n, b);
        if (this.interactiveMomentsManager != null) {
            this.interactiveMomentsManager.setTimelineProgress(n, b);
        }
    }
}
