// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.configuration.persistent.PersistentConfigurable;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.service.configuration.persistent.CoppolaTwo;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.DiscoverySummary;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.servicemgr.DiscoveryRecord;
import java.util.ArrayList;
import com.netflix.falkor.CachedModelProxy$GetResult;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.falkor.PQL;

public class FetchDiscoveryVideosTask extends CmpTask
{
    private final int fromVideo;
    private final String listId;
    private PQL pqlDiscovery;
    private PQL pqlVideos;
    private final int toVideo;
    private final boolean useCacheOnly;
    
    public FetchDiscoveryVideosTask(final CachedModelProxy cachedModelProxy, final String listId, final int fromVideo, final int toVideo, final boolean useCacheOnly, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.listId = listId;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.useCacheOnly = useCacheOnly;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        this.pqlVideos = PQL.create("lolomo", "cwDiscovery", "videoEvidence", PQL.range(this.fromVideo, this.toVideo), PQL.array("summary", "detail", "bookmark", "offlineAvailable", "vertStoryArt"));
        this.pqlDiscovery = PQL.create("lolomo", "cwDiscovery", "discoveryEvidence", PQL.range(this.fromVideo, this.toVideo), PQL.range(0, 2), "summary");
        list.add(this.pqlVideos);
        list.add(this.pqlDiscovery);
        list.add(CmpUtils.CW_CURR_EPISODE_PQL.prepend(PQL.create("lolomo", "cwDiscovery", "videoEvidence", PQL.range(this.fromVideo, this.toVideo))));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onBBVideosFetched(Collections.emptyList(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final List<FalkorVideo> itemsAsList = (List<FalkorVideo>)this.modelProxy.getItemsAsList(this.pqlVideos);
        final List<DiscoverySummary> allItemsAsList = (List<DiscoverySummary>)this.modelProxy.getAllItemsAsList(this.pqlDiscovery);
        final ArrayList<DiscoveryRecord> list = new ArrayList<DiscoveryRecord>();
        for (int i = 0; i < itemsAsList.size(); ++i) {
            final int n = i * 2 + 1;
            if (n < allItemsAsList.size()) {
                list.add(new DiscoveryRecord(itemsAsList.get(i), allItemsAsList.get(n - 1), allItemsAsList.get(n)));
            }
            else {
                final String string = "SPY-8068 - FetchDiscoveryVideosTask didn't get data for pivot #" + (n + 1) + " even though we received " + itemsAsList.size() + " videos";
                if (Log.isLoggable()) {
                    Log.w("CachedModelProxy", string);
                }
                else {
                    ErrorLoggingManager.logHandledException(string);
                }
            }
        }
        if (Log.isLoggable()) {
            Log.i("CachedModelProxy", "Got discovery videos size: " + itemsAsList.size() + " ; and discovery collections list size: " + allItemsAsList.size());
        }
        if (itemsAsList.size() == 0) {
            final String string2 = "SPY-10074 - Coppola2 user cell " + PersistentConfig.getCellIdForTest(CoppolaTwo.class, this.modelProxy.getContext()) + " received 0 videos inside cwDiscovery row";
            Log.w("CachedModelProxy", string2);
            ErrorLoggingManager.logHandledException(string2);
        }
        browseAgentCallback.onDiscoveryVideosFetched((List<Discovery>)list, CommonStatus.OK);
    }
    
    @Override
    protected boolean shouldUseCacheOnly() {
        return this.useCacheOnly;
    }
}
