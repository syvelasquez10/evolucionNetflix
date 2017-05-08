// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.android.volley.Request$Priority;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.ArrayList;
import com.netflix.falkor.PQL;
import java.util.Iterator;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;

public class PrefetchVideoListDetailsTask extends CmpTask
{
    private final List<? extends Video> videos;
    
    public PrefetchVideoListDetailsTask(final CachedModelProxy cachedModelProxy, final List<? extends Video> videos, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videos = videos;
    }
    
    private void handleDetailsResponse() {
        if (this.videos != null) {
            for (final Video video : this.videos) {
                if (video != null) {
                    if (Log.isLoggable()) {
                        Log.d("CachedModelProxy", "Prefetch DP response for " + video.getId() + ": " + video.getTitle());
                    }
                    DPPrefetchABTestUtils.removeFromPendingDetailsRequest(video.getId());
                    DPPrefetchABTestUtils.decrementPrefetchCounter();
                    this.modelProxy.prefetchVideoDetailsFromQueue();
                }
            }
        }
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        if (this.videos != null) {
            final ArrayList<String> list2 = new ArrayList<String>();
            final ArrayList<String> list3 = new ArrayList<String>();
            for (final Video video : this.videos) {
                if (video != null) {
                    final String id = video.getId();
                    if (video.getType() == VideoType.MOVIE) {
                        list2.add(id);
                    }
                    else {
                        list3.add(id);
                    }
                }
            }
            if (!list2.isEmpty()) {
                CmpUtils.buildMovieDetailsPQLs(list, list2);
            }
            if (!list3.isEmpty()) {
                CmpUtils.buildShowDetailsPQL(list, list3, null, true, BrowseExperience.shouldLoadKubrickLeavesInDetails(), true);
            }
        }
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        this.handleDetailsResponse();
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        this.handleDetailsResponse();
    }
    
    @Override
    protected Request$Priority getPriorityOverride() {
        return Request$Priority.LOW;
    }
    
    @Override
    protected void onTaskStarted() {
        super.onTaskStarted();
        for (final Video video : this.videos) {
            if (video != null) {
                DPPrefetchABTestUtils.addToPendingDetailsRequest(video.getId());
            }
        }
    }
}
