// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.model.branches.FalkorVideo;
import java.util.HashMap;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import java.util.List;
import com.netflix.falkor.PQL;

public class FetchOfflineGeoPlayabilityTask extends CmpTask
{
    private final PQL pqlVideos;
    private final List<String> videoIdList;
    
    public FetchOfflineGeoPlayabilityTask(final CachedModelProxy cachedModelProxy, final List<String> videoIdList, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.videoIdList = videoIdList;
        this.pqlVideos = PQL.create("videos", this.videoIdList, "offlineAvailable");
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(this.pqlVideos);
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onOfflineGeoPlayabilityReceived(Collections.emptyMap(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
        for (final String s : this.videoIdList) {
            final Object value = this.modelProxy.getValue(PQL.create("videos", s, "offlineAvailable"));
            if (value != null && value instanceof FalkorVideo) {
                final FalkorVideo falkorVideo = (FalkorVideo)value;
                if (Log.isLoggable()) {
                    Log.i("CachedModelProxy", "videoId=" + s + " isAvailableOffline=" + falkorVideo.isAvailableOffline());
                }
                hashMap.put(s, falkorVideo.isAvailableOffline());
            }
            else {
                if (Log.isLoggable()) {
                    Log.e("CachedModelProxy", "videoId=" + s + " Not a falkorVideo=" + value);
                }
                hashMap.put(s, false);
            }
        }
        if (hashMap.size() == 0) {
            Log.w("CachedModelProxy", "received 0 videos inside FetchOfflineGeoPlayabilityTask");
            ErrorLoggingManager.logHandledException("received 0 videos inside FetchOfflineGeoPlayabilityTask");
        }
        browseAgentCallback.onOfflineGeoPlayabilityReceived(hashMap, CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldSkipCache() {
        return true;
    }
}
